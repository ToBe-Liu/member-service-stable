package com.wkyc.member.core.dao;

import com.wkyc.member.api.dao.MemberSaveMoneyDao;
import com.wkyc.member.api.domain.MemberSaveMoney;
import com.wkyc.member.core.mapper.MemberSaveMoneyMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 会员累计节省资金表
 *@author LiuXingHai
 *@date 2018/09/21
 */
@Repository("memberSaveMoneyDao")
public class MemberSaveMoneyDaoImpl implements MemberSaveMoneyDao {

    @Resource
    private SqlSession sqlSession;

    @Override
    public int insert(MemberSaveMoney record) {
        return sqlSession.getMapper(MemberSaveMoneyMapper.class).insertSelective(record);
    }

    @Override
    public int update(MemberSaveMoney record) {
        return  sqlSession.getMapper(MemberSaveMoneyMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public MemberSaveMoney selectByUserID(Long userId) {
        return sqlSession.getMapper(MemberSaveMoneyMapper.class).selectByUserID(userId);
    }

    @Override
    public int updateByUserID(MemberSaveMoney record) {
        return sqlSession.getMapper(MemberSaveMoneyMapper.class).updateByUserID(record);
    }
}