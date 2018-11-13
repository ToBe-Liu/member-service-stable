package com.wkyc.member.api.service;

import com.wkyc.member.api.domain.MemberBenefits;
import com.wkyc.member.api.domain.MemberCard;
import com.wkyc.member.api.domain.vo.MemberCardVo;

import java.util.List;

/**
 * 会员服务
 *@author LiuXingHai
 *@date 2018/09/14
 */
public interface MemberService {
    /**
     *  购买会员
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean purchaseMember(Long userId,Long memberCardId,String transactionSerialNumber,Long recommendUserId);

    /**
     *  购买会员支付成功
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean paySuccess(String transactionSerialNumber);


    /**
     *  查询所有非删除的记录
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCardVo> selectNoneDelete();

    /**
     *  查询所有非删除非禁用的记录
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCardVo> selectValid();

    /**
     *  查询非删除非禁用的会员卡
     *@author LiuXingHai
     *@date 2018/9/14
     */
    MemberCardVo selectValidMemberCard(Long memberCardId);

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
    MemberCard selectByGoodId(Long goodId);


}