package com.wkyc.member.core.mapper;

import com.wkyc.member.api.domain.MemberCard;

import java.util.List;
import java.util.Map;

public interface MemberCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberCard record);

    int insertSelective(MemberCard record);

    MemberCard selectByPrimaryKey(Long id);

    List<MemberCard> selectAll();

    int updateByPrimaryKey(MemberCard record);

    int updateByPrimaryKeySelective(MemberCard record);

    /**
     *  通过条件查询
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCard> selectByCondition(Map<String,Object> params);


}