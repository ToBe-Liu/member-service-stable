package com.wkyc.member.core.dao;

import com.wkyc.member.api.dao.MemberBenefitsDao;
import com.wkyc.member.api.domain.MemberBenefits;
import com.wkyc.member.core.mapper.MemberBenefitsMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员权益表
 *@author LiuXingHai
 *@date 2018/09/14
 */
@Repository("memberBenefitsDao")
public class MemberBenefitsDaoImpl implements MemberBenefitsDao {
    @Resource
    private SqlSession sqlSession;


    @Override
    public int insert(MemberBenefits memberBenefits) {
        return sqlSession.getMapper(MemberBenefitsMapper.class).insertSelective(memberBenefits);
    }

    @Override
    public int update(MemberBenefits memberBenefits) {
        return sqlSession.getMapper(MemberBenefitsMapper.class).updateByPrimaryKeySelective(memberBenefits);
    }

    @Override
    public int delete(Long memberBenefitId) {
        return sqlSession.getMapper(MemberBenefitsMapper.class).deleteByPrimaryKey(memberBenefitId);
    }

    @Override
    public List<MemberBenefits> selectByMemberCardId(Long memberCardId) {
        return  sqlSession.getMapper(MemberBenefitsMapper.class).selectByMemberCardId(memberCardId);
    }
}