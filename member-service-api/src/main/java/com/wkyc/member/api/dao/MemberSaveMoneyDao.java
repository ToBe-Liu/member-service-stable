package com.wkyc.member.api.dao;

import com.wkyc.member.api.domain.MemberSaveMoney;

/**
 * 会员累计节省资金表
 *@author LiuXingHai
 *@date 2018/09/21
 */
public interface MemberSaveMoneyDao {

    /**
     *  新增
     *@author LiuXingHai
     *@date 2018/9/21
     */
    int insert(MemberSaveMoney record);

    /**
     *  更新
     *@author LiuXingHai
     *@date 2018/9/21
     */
    int update(MemberSaveMoney record);

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