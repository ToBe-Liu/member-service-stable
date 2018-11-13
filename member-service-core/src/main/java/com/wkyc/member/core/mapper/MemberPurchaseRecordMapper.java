package com.wkyc.member.core.mapper;

import com.wkyc.member.api.domain.MemberPurchaseRecord;
import com.zjlp.face.util.page.Pagination;

import java.util.List;
import java.util.Map;

public interface MemberPurchaseRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberPurchaseRecord record);

    int insertSelective(MemberPurchaseRecord record);

    MemberPurchaseRecord selectByPrimaryKey(Long id);

    List<MemberPurchaseRecord> selectAll();

    int updateByPrimaryKey(MemberPurchaseRecord record);

    int updateByPrimaryKeySelective(MemberPurchaseRecord record);

    /**
     *  分页查询
     *@author LiuXingHai
     *@date 2018/9/17
     */
    List<MemberPurchaseRecord> selectByPagination (Map<String, Object> params);
    /**
     *  分页查询
     *@author LiuXingHai
     *@date 2018/9/17
     */
    Long countByPagination (Map<String, Object> params);

    /**
     *  通过交易流水号更新
     *@author LiuXingHai
     *@date 2018/9/19
     */
    int updateByTSN(MemberPurchaseRecord record);
}