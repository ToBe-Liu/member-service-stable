package com.wkyc.member.core.mapper;

import com.wkyc.member.api.domain.MemberBenefits;

import java.util.List;

public interface MemberBenefitsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberBenefits record);

    int insertSelective(MemberBenefits record);

    MemberBenefits selectByPrimaryKey(Long id);

    List<MemberBenefits> selectAll();

    int updateByPrimaryKey(MemberBenefits record);

    int updateByPrimaryKeySelective(MemberBenefits record);


    /**
     *  通过会员卡id查询
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberBenefits> selectByMemberCardId(Long memberCardId);

}