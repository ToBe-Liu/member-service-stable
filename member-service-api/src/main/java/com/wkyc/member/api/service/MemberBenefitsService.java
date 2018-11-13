package com.wkyc.member.api.service;

import com.wkyc.member.api.domain.MemberBenefits;

import java.util.List;

/**
 * 会员权益表
 *@author LiuXingHai
 *@date 2018/09/14
 */
public interface MemberBenefitsService {
    /**
     *  新增
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean insert(MemberBenefits memberBenefits);

    /**
     *  更新
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean update(MemberBenefits memberBenefits);

    /**
     *  删除
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean delete(Long memberBenefitId);

    /**
     *  通过会员卡id查询
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberBenefits> selectByMemberCardId(Long memberCardId);


}