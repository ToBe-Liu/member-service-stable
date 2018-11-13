package com.wkyc.member.core.dao;

import com.wkyc.member.api.dao.MemberPurchaseRecordDao;
import com.wkyc.member.api.domain.MemberPurchaseRecord;
import com.wkyc.member.core.mapper.MemberPurchaseRecordMapper;
import com.zjlp.face.util.page.Pagination;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员卡购买记录表
 *@author LiuXingHai
 *@date 2018/09/14
 */
@Repository("memberPurchaseRecordDao")
public class MemberPurchaseRecordDaoImpl implements MemberPurchaseRecordDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int insert(MemberPurchaseRecord memberPurchaseRecord) {
        return sqlSession.getMapper(MemberPurchaseRecordMapper.class).insertSelective(memberPurchaseRecord);
    }

    @Override
    public int update(MemberPurchaseRecord memberPurchaseRecord) {
        return sqlSession.getMapper(MemberPurchaseRecordMapper.class).updateByPrimaryKeySelective(memberPurchaseRecord);
    }

    @Override
    public int updateByTSN(MemberPurchaseRecord record) {
        return sqlSession.getMapper(MemberPurchaseRecordMapper.class).updateByTSN(record);
    }

    @Override
    public MemberPurchaseRecord selectByTSN(String transactionSerialNumber) {
        Map<String,Object> map = new HashMap<>(3);
        map.put("transactionSerialNumber",transactionSerialNumber);
        map.put("start",0);
        map.put("pageSize",1);
        List<MemberPurchaseRecord> memberPurchaseRecords = sqlSession.getMapper(MemberPurchaseRecordMapper.class).selectByPagination(map);
        if (memberPurchaseRecords == null){
            return null;
        }
        return memberPurchaseRecords.get(0);
    }

    @Override
    public List<MemberPurchaseRecord> selectByPagination(MemberPurchaseRecord memberPurchaseRecord,Pagination<MemberPurchaseRecord> pagination) {
        Map<String,Object> map = new HashMap<>(6);
        map.put("start",pagination.getStart());
        map.put("pageSize",pagination.getPageSize());
        map.put("memberCardName",memberPurchaseRecord == null ? null :memberPurchaseRecord.getMemberCardName());
        map.put("userPhone",memberPurchaseRecord == null ? null :memberPurchaseRecord.getUserPhone());
        map.put("tradeStatus",MemberPurchaseRecord.TRADE_STATUS_SUCCESS);
        map.put("payStatus",MemberPurchaseRecord.PAY_STATUS_SUCCESS);
        return sqlSession.getMapper(MemberPurchaseRecordMapper.class).selectByPagination(map);
    }

    @Override
    public Long countByPagination(MemberPurchaseRecord memberPurchaseRecord) {
        Map<String,Object> map = new HashMap<>(4);
        map.put("memberCardName",memberPurchaseRecord == null ? null :memberPurchaseRecord.getMemberCardName());
        map.put("userPhone",memberPurchaseRecord == null ? null :memberPurchaseRecord.getUserPhone());
        map.put("tradeStatus",MemberPurchaseRecord.TRADE_STATUS_SUCCESS);
        map.put("payStatus",MemberPurchaseRecord.PAY_STATUS_SUCCESS);
        return sqlSession.getMapper(MemberPurchaseRecordMapper.class).countByPagination(map);
    }
}