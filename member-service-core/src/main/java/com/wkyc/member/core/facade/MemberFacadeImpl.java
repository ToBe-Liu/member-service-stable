package com.wkyc.member.core.facade;

import com.alibaba.fastjson.JSON;
import com.wkyc.member.api.domain.MemberBenefits;
import com.wkyc.member.api.domain.MemberCard;
import com.wkyc.member.api.domain.MemberPurchaseRecord;
import com.wkyc.member.api.domain.MemberSaveMoney;
import com.wkyc.member.api.domain.vo.MemberCardVo;
import com.wkyc.member.api.facade.MemberFacade;
import com.wkyc.member.api.service.*;
import com.zjlp.face.slcoin.facade.SlcoinFacade;
import com.zjlp.face.util.mapper.BeanMapperUtil;
import com.zjlp.face.util.page.Pagination;
import com.zjlp.face.web.facade.UserServiceFacade;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员服务 facade
 *@author LiuXingHai
 *@date 2018/09/14
 */
@Service("memberFacade")
public class MemberFacadeImpl implements MemberFacade {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberPurchaseRecordService memberPurchaseRecordService;
    @Autowired
    private MemberCardService memberCardService;
    @Autowired
    private MemberBenefitsService memberBenefitsService;
    @Resource
    private UserServiceFacade userServiceFacade;
    @Resource
    private MemberSaveMoneyService memberSaveMoneyService;

    private static final Logger logger = LoggerFactory.getLogger(MemberFacadeImpl.class);


    @Override
    public Boolean purchaseMember(Long userId, Long memberCardId,String transactionSerialNumber,Long recommendUserId) {
        return memberService.purchaseMember(userId,memberCardId,transactionSerialNumber,recommendUserId);
    }

    @Override
    public Boolean paySuccess(String transactionSerialNumber) {
        return memberService.paySuccess(transactionSerialNumber);
    }

    @Override
    @Transactional
    public Boolean addMemberCard(MemberCardVo memberCardVo) {
        //参数校验
        if (memberCardVo == null){
            logger.info("【新增】【会员卡】失败！memberCardVo 参数为空！");
            return false;
        }
        List<MemberBenefits> memberBenefits = memberCardVo.getMemberBenefits();
        if (CollectionUtils.isEmpty(memberBenefits)){
            logger.info("【新增】【会员卡】失败！会员卡权益信息为空！");
            return false;
        }
        //插入会员卡基础信息
        MemberCard memberCard = BeanMapperUtil.map(memberCardVo, MemberCard.class);
        memberCardService.insert(memberCard);

        //插入会员卡权益信息
        for (MemberBenefits memberBenefit : memberBenefits) {
            memberBenefit.setMemberCardId(memberCard.getId());
            memberBenefitsService.insert(memberBenefit);
        }

        return true;
    }

    @Override
    public MemberCard selectMemberCard(Long memberCardId) {
        return memberCardService.selectById(memberCardId);
    }

    @Override
    @Transactional
    public Boolean updateMemberCard(MemberCardVo memberCardVo) {
        //参数校验
        if (memberCardVo == null){
            logger.info("【更新】【会员卡】失败！memberCardVo 参数为空！");
            return false;
        }

        //更新会员卡基础信息
        MemberCard memberCard = BeanMapperUtil.map(memberCardVo, MemberCard.class);
        memberCardService.update(memberCard);

        //如果是禁用或者是删除则不需要更新会员卡权益
        if ((memberCard.getIsDelete() != null && memberCard.getIsDelete() == 1)
                || memberCard.getIsDisable() != null){
            return true;
        }

        //更新会员卡权益信息
        List<MemberBenefits> memberBenefits = memberCardVo.getMemberBenefits();
        if (CollectionUtils.isEmpty(memberBenefits)){
            logger.info("【更新】【会员卡】失败！会员卡权益信息为空！");
            return false;
        }
        //删除老的会员权益
        List<MemberBenefits> oldMemberBenefits = memberBenefitsService.selectByMemberCardId(memberCard.getId());
        for (MemberBenefits oldMemberBenefit : oldMemberBenefits) {
            memberBenefitsService.delete(oldMemberBenefit.getId());
        }
        //新增新的会员权益
        for (MemberBenefits memberBenefit : memberBenefits) {
            memberBenefit.setMemberCardId(memberCard.getId());
            memberBenefit.setId(null);
            memberBenefitsService.insert(memberBenefit);
        }
        return true;
    }

    @Override
    public List<MemberCardVo> selectNoneDeleteMemberCard() {
        return memberService.selectNoneDelete();
    }

    @Override
    public List<MemberCardVo> selectValidMemberCard() {
        return memberService.selectValid();
    }

    @Override
    public MemberCardVo selectValidMemberCard(Long memberCardId) {
        return memberService.selectValidMemberCard(memberCardId);
    }

    @Override
    public Boolean insertMemberBenefits(MemberBenefits memberBenefits) {
        return memberBenefitsService.insert(memberBenefits);
    }

    @Override
    public Boolean updateMemberBenefits(MemberBenefits memberBenefits) {
        return memberBenefitsService.update(memberBenefits);
    }

    @Override
    public MemberPurchaseRecord selectMemberPurchaseRecordByTSN(String transactionSerialNumber) {
        return memberPurchaseRecordService.selectByTSN(transactionSerialNumber);
    }

    @Override
    public Boolean updateMemberPurchaseRecordByTSN(MemberPurchaseRecord record) {
        return memberPurchaseRecordService.updateByTSN(record);
    }

    @Override
    public Pagination<MemberPurchaseRecord> paginationMemberPurchaseRecord(MemberPurchaseRecord memberPurchaseRecord,Pagination<MemberPurchaseRecord> pagination) {
        logger.info("【查询】【会员卡购买记录】memberCardVo 参数：{}", JSON.toJSONString(memberPurchaseRecord));
        return memberPurchaseRecordService.selectByPagination(memberPurchaseRecord,pagination);
    }

    @Override
    public Boolean hasValidMember(Long memberCardId) {
        return userServiceFacade.countMemberNum(memberCardId) > 0;
    }

    @Override
    public Boolean hasSameName(String name) {
        return memberCardService.hasSameName(name);
    }

    @Override
    public Boolean updateTotalMoney(Long userId, Long money) {
        return memberSaveMoneyService.updateTotalMoney(userId,money);
    }

    @Override
    public MemberSaveMoney getTotalSaveMoney(Long userId) {
        return memberSaveMoneyService.selectByUserID(userId);
    }

    @Override
    public MemberBenefits selectDiscountBenefitsByMemberCardId(Long memberCardId) {
        return memberService.selectDiscountBenefitsByMemberCardId(memberCardId);
    }

    @Override
    public MemberCard selectMemberCardByGoodId(Long goodId) {
        return memberService.selectByGoodId(goodId);
    }


}