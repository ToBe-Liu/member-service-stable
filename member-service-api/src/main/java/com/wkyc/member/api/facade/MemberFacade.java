package com.wkyc.member.api.facade;

import com.wkyc.member.api.domain.MemberBenefits;
import com.wkyc.member.api.domain.MemberCard;
import com.wkyc.member.api.domain.MemberPurchaseRecord;
import com.wkyc.member.api.domain.MemberSaveMoney;
import com.wkyc.member.api.domain.vo.MemberCardVo;
import com.zjlp.face.util.page.Pagination;

import java.util.List;

/**
 * 会员服务 facade
 *@author LiuXingHai
 *@date 2018/09/14
 */
public interface MemberFacade {
    /**
     *  购买会员下单
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean purchaseMember(Long userId, Long memberCardId,String transactionSerialNumber,Long recommendUserId);

    /**
     *  购买会员支付成功
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean paySuccess(String transactionSerialNumber);

    /**
     *  新增会员卡
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean addMemberCard(MemberCardVo memberCardVo);

    /**
     *  更新会员卡
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean updateMemberCard(MemberCardVo memberCardVo);

    /**
     *  通过会员卡id查询会员卡
     *@author LiuXingHai
     *@date 2018/9/14
     */
    MemberCard selectMemberCard(Long memberCardId);

    /**
     *  查询所有非删除的会员卡
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCardVo> selectNoneDeleteMemberCard();

    /**
     *  查询所有非删除非禁用的会员卡
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCardVo> selectValidMemberCard();

    /**
     *  查询非删除非禁用的会员卡
     *@author LiuXingHai
     *@date 2018/9/14
     */
    MemberCardVo selectValidMemberCard(Long memberCardId);

    /**
     *  新增会员权益
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean insertMemberBenefits(MemberBenefits memberBenefits);

    /**
     *  更新会员权益
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean updateMemberBenefits(MemberBenefits memberBenefits);

    /**
     *  通过交易流水号查询
     *@author LiuXingHai
     *@date 2018/9/17
     */
    MemberPurchaseRecord selectMemberPurchaseRecordByTSN (String transactionSerialNumber);

    /**
     *  通过交易流水号更新
     *@author LiuXingHai
     *@date 2018/9/19
     */
    Boolean updateMemberPurchaseRecordByTSN(MemberPurchaseRecord record);

    /**
     *  分页查询会员卡购买记录
     *@author LiuXingHai
     *@date 2018/9/17
     */
    Pagination<MemberPurchaseRecord> paginationMemberPurchaseRecord (MemberPurchaseRecord memberPurchaseRecord,Pagination<MemberPurchaseRecord> pagination);

    /**
     *  当前会员卡是否有未到期的会员
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean hasValidMember(Long memberCardId);

    /**
     *  是否有相同的会员卡名字
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean hasSameName(String name);

    /**
     *  通过userId更新累计节省资金
     *@author LiuXingHai
     *@date 2018/9/21
     */
    Boolean updateTotalMoney(Long userId,Long money);

    /**
     *  通过userId获取累计节省资金
     *@author LiuXingHai
     *@date 2018/9/21
     */
    MemberSaveMoney getTotalSaveMoney(Long userId);

    /**
     *  通过会员卡id查询折扣权益
     *@author LiuXingHai
     *@date 2018/9/14
     */
    MemberBenefits selectDiscountBenefitsByMemberCardId(Long memberCardId);

    /**
     *  通过商品id 获取会员卡
     *@author LiuXingHai
     *@date 2018/9/14
     */
    MemberCard selectMemberCardByGoodId(Long goodId);

}