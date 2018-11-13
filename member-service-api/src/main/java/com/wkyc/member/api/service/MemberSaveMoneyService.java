package com.wkyc.member.api.service;

import com.wkyc.member.api.domain.MemberSaveMoney;

/**
 * 会员累计节省资金表
 *@author LiuXingHai
 *@date 2018/09/21
 */
public interface MemberSaveMoneyService {

    /**
     *  新增
     *@author LiuXingHai
     *@date 2018/9/21
     */
    Boolean insert(MemberSaveMoney record);

    /**
     *  更新
     *@author LiuXingHai
     *@date 2018/9/21
     */
    Boolean update(MemberSaveMoney record);

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
    Boolean updateByUserID(MemberSaveMoney record);

    /**
     *  通过userId更新累计节省资金
     *@author LiuXingHai
     *@date 2018/9/21
     */
    Boolean updateTotalMoney(Long userId,Long money);

}