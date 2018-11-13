package com.wkyc.member.core.service;

import com.wkyc.member.api.constants.Constants;
import com.wkyc.member.api.domain.MemberBenefits;
import com.wkyc.member.api.domain.MemberCard;
import com.wkyc.member.api.domain.MemberPurchaseRecord;
import com.wkyc.member.api.domain.vo.MemberCardVo;
import com.wkyc.member.api.service.MemberBenefitsService;
import com.wkyc.member.api.service.MemberCardService;
import com.wkyc.member.api.service.MemberPurchaseRecordService;
import com.wkyc.member.api.service.MemberService;
import com.zjlp.face.slcoin.domain.SlcoinAccount;
import com.zjlp.face.slcoin.domain.SlcoinRecord;
import com.zjlp.face.slcoin.facade.SlcoinFacade;
import com.zjlp.face.util.date.DateUtil;
import com.zjlp.face.util.mapper.BeanMapperUtil;
import com.zjlp.face.util.result.ResultBase;
import com.zjlp.face.web.facade.UserServiceFacade;
import com.zjlp.face.web.server.user.user.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 会员卡表
 *@author LiuXingHai
 *@date 2018/09/14
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberCardService memberCardService;
    @Autowired
    private MemberPurchaseRecordService memberPurchaseRecordService;
    @Autowired
    private MemberBenefitsService memberBenefitsService;
    @Resource
    private UserServiceFacade userServiceFacade;
    @Resource
    private SlcoinFacade slcoinFacade;
    @Resource
    private RedissonClient clusterRedis;

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);


    @Override
    public Boolean purchaseMember(Long userId, Long memberCardId,String transactionSerialNumber,Long recommendUserId) {

        logger.info("【购买会员】开始！userId {},memberCardId {}",userId,memberCardId);
        //校验会员卡，用户是否存在
        MemberCard memberCard = memberCardService.selectById(memberCardId);
        if (memberCard == null) {
            logger.info("【购买会员】失败！会员卡不存在");
            return false;
        }
        User user = userServiceFacade.getUserById(userId);
        if (user == null) {
            logger.info("【购买会员】失败！用户不存在");
            return false;
        }
        //推荐人
        User recommendUser = userServiceFacade.getUserById(recommendUserId);
        //新增会员购买记录
        MemberPurchaseRecord memberPurchaseRecord = new MemberPurchaseRecord();
        memberPurchaseRecord.setUserId(user.getId());
        memberPurchaseRecord.setUserName(user.getNickname());
        memberPurchaseRecord.setUserPhone(user.getCell());
        memberPurchaseRecord.setMemberCardId(memberCard.getId());
        memberPurchaseRecord.setMemberCardName(memberCard.getName());
        memberPurchaseRecord.setTransactionSerialNumber(transactionSerialNumber);
        memberPurchaseRecord.setUpdateTime(new Date());
        memberPurchaseRecord.setCreateTime(new Date());
        if (recommendUser != null){
            memberPurchaseRecord.setRecommendUserId(recommendUserId);
            memberPurchaseRecord.setRecommendUserName(recommendUser.getNickname());
        }
        memberPurchaseRecordService.insert(memberPurchaseRecord);

        return true;
    }

    @Override
    @Transactional
    public Boolean paySuccess(String transactionSerialNumber) {
        RLock lock = clusterRedis.getLock(Constants.LOCK_PURCHASE_MEMBER_CALLBACK + transactionSerialNumber);
        boolean isLocked = false;
        try {
            if (isLocked = lock.tryLock()) {
                //参数检验
                if (transactionSerialNumber == null){
                    logger.info("【购买会员】【支付成功回调】失败！transactionSerialNumber 为空！");
                    return false;
                }
                MemberPurchaseRecord memberPurchaseRecord = memberPurchaseRecordService.selectByTSN(transactionSerialNumber);
                if (memberPurchaseRecord == null){
                    logger.info("【购买会员】【支付成功回调】失败！购买记录 为空！");
                    return false;
                }

                Long userId = memberPurchaseRecord.getUserId();
                Long memberCardId = memberPurchaseRecord.getMemberCardId();

                User user = userServiceFacade.getUserById(userId);
                if (user == null) {
                    logger.info("【购买会员】【支付成功回调】失败！用户不存在");
                    return false;
                }
                MemberCard memberCard = memberCardService.selectById(memberCardId);
                if (memberCard == null) {
                    logger.info("【购买会员】支付成功回调】失败！会员卡不存在");
                    return false;
                }
                //增加会员标志
                user.setMemberIndate(calculateMemberIndate(user,memberCard));
                user.setMember(memberCardId);
                userServiceFacade.edit(user);
                //赠送会员权益
                List<MemberBenefits> memberBenefits = memberBenefitsService.selectByMemberCardId(memberCardId);
                if (CollectionUtils.isNotEmpty(memberBenefits)) {

                    for (MemberBenefits memberBenefit : memberBenefits) {
                        switch (memberBenefit.getRemark()){
                            case MemberBenefits.MEMBER_BENEFITS_DISCOUNT:
                                //会员权益 折扣
                                break;
                            case MemberBenefits.MEMBER_BENEFITS_INTEGRATION:
                                //会员权益 积分
                                String remark = "购买" + memberCard.getName()+ "获得";
                                presentIntegration(userId,memberBenefit.getValue(),remark);
                                break;
                            default:
                                break;
                        }
                    }
                }
                //更新交易状态
                memberPurchaseRecord.setTradeStatus(MemberPurchaseRecord.TRADE_STATUS_SUCCESS);
                memberPurchaseRecord.setUpdateTime(new Date());
                memberPurchaseRecordService.updateByTSN(memberPurchaseRecord);
                return true;
           }
        } catch (Exception e) {
            logger.error("【购买会员支付成功回调】异常，transactionSerialNumber：{}，原因：{}",transactionSerialNumber, e);
            throw new RuntimeException(e);
        }finally {
            if(isLocked){
                lock.unlock();
            }
        }
       return false;
    }


    @Override
    public List<MemberCardVo> selectNoneDelete() {
        List<MemberCard> memberCards = memberCardService.selectNoneDelete();
        if (memberCards == null) {
            return null;
        }
        List<MemberCardVo> result = new ArrayList<>(memberCards.size());
        for (MemberCard memberCard : memberCards) {
            result.add(populateMemberCardVo(memberCard));
        }
        return result;
    }



    @Override
    public List<MemberCardVo> selectValid() {
        List<MemberCard> memberCards = memberCardService.selectValid();
        if (memberCards == null) {
            return null;
        }
        List<MemberCardVo> result = new ArrayList<>(memberCards.size());
        for (MemberCard memberCard : memberCards) {
            result.add(populateMemberCardVo(memberCard));
        }
        return result;
    }

    @Override
    public MemberCardVo selectValidMemberCard(Long memberCardId) {
        MemberCard memberCard = memberCardService.selectById(memberCardId);
        if (memberCard == null) {
            return null;
        }
        return populateMemberCardVo(memberCard);
    }

    @Override
    public MemberBenefits selectDiscountBenefitsByMemberCardId(Long memberCardId) {
        if (memberCardId == null){
            return null;
        }
        List<MemberBenefits> memberBenefits = memberBenefitsService.selectByMemberCardId(memberCardId);
        for (MemberBenefits memberBenefit : memberBenefits) {
            if (MemberBenefits.MEMBER_BENEFITS_DISCOUNT.equals(memberBenefit.getRemark())){
                return memberBenefit;
            }
        }
        return null;
    }

    @Override
    public MemberCard selectByGoodId(Long goodId) {
        if (goodId == null){
            logger.info("参数 goodsId 为空!");
            return null;
        }
        return memberCardService.selectByGoodId(goodId);
    }

    /**
     *  填充会员权益
     *@author LiuXingHai
     *@date 2018/9/17
     */
    private MemberCardVo populateMemberCardVo(MemberCard memberCard) {
        if (memberCard == null){
            return null;
        }
        MemberCardVo memberCardVo = BeanMapperUtil.map(memberCard, MemberCardVo.class);
        List<MemberBenefits> memberBenefits = memberBenefitsService.selectByMemberCardId(memberCard.getId());
        List<String> benefits = extractMemberBenefits(memberBenefits);
        memberCardVo.setBenefits(benefits);
        memberCardVo.setMemberBenefits(memberBenefits);
        return memberCardVo;
    }

    /**
     *  提取简化 去重后的会员权益
     *@author LiuXingHai
     *@date 2018/9/17
     */
    private List<String> extractMemberBenefits(List<MemberBenefits> memberBenefits) {
        if (memberBenefits == null) {
            return null;
        }
        Set<String> tempResult = new HashSet<>(memberBenefits.size());
        List<String> result = new ArrayList<>(memberBenefits.size());
        for (MemberBenefits memberBenefit : memberBenefits) {
            //优惠券有重复的 所以需要去重
            tempResult.add(memberBenefit.getRemark());
        }
        result.addAll(tempResult);
        return result;
    }


    /**
     *  赠送积分
     *@author LiuXingHai
     *@date 2018/9/17
     */
    private void presentIntegration(Long userId, Integer value,String remark) {
        ResultBase<SlcoinAccount> slcoinAccount = slcoinFacade.getSlcoinAccountByUserId(userId);
        if (slcoinAccount == null || !slcoinAccount.isSuccess() || slcoinAccount.getBusinessObj() == null){
            //新增积分账户
            ResultBase<Boolean> resultBase = slcoinFacade.addSlcoinAccount(userId, userId.toString());
            if (resultBase == null || !resultBase.isSuccess()){
                logger.error("【赠送积分】失败！新增积分账户失败！");
                return;
            }
        }
        //赠送积分
        SlcoinRecord slcoinRecord = new SlcoinRecord();
        slcoinRecord.setAmount(value);
        slcoinRecord.setRemark(remark);
        slcoinRecord.setStatus(SlcoinRecord.STATUS_VALID);
        slcoinRecord.setUserId(userId);
        slcoinRecord.setType(SlcoinRecord.TYPE_PURCHASE_MEMBER);
        slcoinFacade.increaseSlcoin(slcoinRecord);
    }

    /**
     *  计算会员有效期
     *@author LiuXingHai
     *@date 2018/9/17
     */
    private Date calculateMemberIndate(User user,MemberCard memberCard) {
        if (user == null || memberCard == null){
            return null;
        }

        Date memberIndate;
        Date now = new Date();
        int indate = memberCard.getIndate().intValue();

        if (memberCard.getId().equals(user.getMember())){
            //同一种会员之间的有效期才叠加
            Date oldMemberIndate = user.getMemberIndate();
            if (oldMemberIndate.after(now)) {
                memberIndate = DateUtil.addDay(oldMemberIndate, indate);
            } else {
                memberIndate = DateUtil.addDay(now, indate);
            }
        } else {
            //非同种会员直接覆盖有效期
            memberIndate = DateUtil.addDay(now, indate);
        }

        return memberIndate;
    }


}