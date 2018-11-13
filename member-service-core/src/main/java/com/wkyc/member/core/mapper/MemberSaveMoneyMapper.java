package com.wkyc.member.core.mapper;

import com.wkyc.member.api.domain.MemberSaveMoney;

public interface MemberSaveMoneyMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(MemberSaveMoney record);

    MemberSaveMoney selectByPrimaryKey(Long id);

    int updateByPrimaryKey(MemberSaveMoney record);

    /**
     *  通过userId查询
     *@author LiuXingHai
     *@date 2018/9/21
     */
    MemberSaveMoney selectByUserID(Long userId);

    /**
     *  通过userId更新
     *@author LiuXingHai
     *@date 2018/9/21
     */
    int updateByUserID(MemberSaveMoney record);
}