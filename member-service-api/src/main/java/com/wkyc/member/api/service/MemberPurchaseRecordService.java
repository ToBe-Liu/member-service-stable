package com.wkyc.member.api.service;

import com.wkyc.member.api.domain.MemberPurchaseRecord;
import com.zjlp.face.util.page.Pagination;

/**
 * 会员卡购买记录表
 *@author LiuXingHai
 *@date 2018/09/14
 */
public interface MemberPurchaseRecordService {
    /**
     *  新增
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean insert(MemberPurchaseRecord memberPurchaseRecord);


    /**
     *  更新
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean update(MemberPurchaseRecord memberPurchaseRecord);

    /**
     *  通过交易流水号更新
     *@author LiuXingHai
     *@date 2018/9/19
     */
    Boolean updateByTSN(MemberPurchaseRecord record);

    /**
     *  通过交易流水号查询
     *@author LiuXingHai
     *@date 2018/9/17
     */
    MemberPurchaseRecord selectByTSN (String transactionSerialNumber);

    /**
     *  分页查询
     *@author LiuXingHai
     *@date 2018/9/17
     */
    Pagination<MemberPurchaseRecord> selectByPagination (MemberPurchaseRecord memberPurchaseRecord,Pagination<MemberPurchaseRecord> pagination);
}