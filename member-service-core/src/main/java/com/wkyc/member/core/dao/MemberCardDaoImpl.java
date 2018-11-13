package com.wkyc.member.core.dao;

import com.wkyc.member.api.dao.MemberCardDao;
import com.wkyc.member.api.domain.MemberCard;
import com.wkyc.member.core.mapper.MemberCardMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员卡表
 *@author LiuXingHai
 *@date 2018/09/14
 */
@Repository("memberCardDao")
public class MemberCardDaoImpl implements MemberCardDao {

    @Resource
    private SqlSession sqlSession;

    @Override
    public int insert(MemberCard memberCard) {
        return sqlSession.getMapper(MemberCardMapper.class).insertSelective(memberCard);
    }

    @Override
    public int update(MemberCard memberCard) {
        return sqlSession.getMapper(MemberCardMapper.class).updateByPrimaryKeySelective(memberCard);
    }

    @Override
    public MemberCard selectById(Long memberCardId) {
        return sqlSession.getMapper(MemberCardMapper.class).selectByPrimaryKey(memberCardId);
    }

    @Override
    public MemberCard selectByGoodId(Long goodId) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("goodId",goodId);
        List<MemberCard> memberCards = sqlSession.getMapper(MemberCardMapper.class).selectByCondition(map);
        if (CollectionUtils.isEmpty(memberCards)){
            return null;
        }
        return memberCards.get(0);
    }

    @Override
    public List<MemberCard> selectByName(String name) {
        Map<String,Object> map = new HashMap<>(2);
        map.put("name",name);
        map.put("isDelete",0);
        return sqlSession.getMapper(MemberCardMapper.class).selectByCondition(map);
    }

    @Override
    public List<MemberCard> selectByCondition(Integer delete , Integer disable) {
        Map<String,Object> map = new HashMap<>(2);
        map.put("isDelete",delete);
        map.put("isDisable",disable);
        return sqlSession.getMapper(MemberCardMapper.class).selectByCondition(map);
    }
}