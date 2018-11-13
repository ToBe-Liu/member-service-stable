package com.wkyc.member.core.service;

import com.wkyc.member.api.dao.MemberCardDao;
import com.wkyc.member.api.domain.MemberCard;
import com.wkyc.member.api.service.MemberCardService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 会员卡表
 *@author LiuXingHai
 *@date 2018/09/14
 */
@Service("memberCardService")
public class MemberCardServiceImpl implements MemberCardService {

    @Autowired
    private MemberCardDao memberCardDao;
    private static final Logger logger = LoggerFactory.getLogger(MemberCardServiceImpl.class);

    @Override
    public Boolean insert(MemberCard memberCard) {
        //参数检验
        if (memberCard == null){
            logger.info("【新增】【会员卡】失败！记录为空！");
            return false;
        }
        memberCard.setUpdateTime(new Date());
        memberCard.setCreateTime(new Date());
        return memberCardDao.insert(memberCard) > 0;
    }

    @Override
    public Boolean update(MemberCard memberCard) {
        //参数检验
        if (memberCard == null){
            logger.info("【更新】【会员卡】失败！记录为空！");
            return false;
        }
        memberCard.setUpdateTime(memberCard.getUpdateTime() == null ? new Date() : memberCard.getUpdateTime());
        memberCard.setCreateTime(null);
        return memberCardDao.update(memberCard) > 0;
    }

    @Override
    public MemberCard selectById(Long memberCardId) {
        //参数检验
        if (memberCardId == null){
            logger.info("【查询】【会员卡】失败！id为空！");
            return null;
        }
        return memberCardDao.selectById(memberCardId);
    }

    @Override
    public MemberCard selectByGoodId(Long goodId) {
        //参数检验
        if (goodId == null){
            logger.info("【查询】【会员卡】失败！goodId为空！");
            return null;
        }
        return memberCardDao.selectByGoodId(goodId);
    }

    @Override
    public Boolean hasSameName(String name) {
        //参数检验
        if (name == null){
            return false;
        }
        List<MemberCard> memberCards = memberCardDao.selectByName(name);
        if (CollectionUtils.isEmpty(memberCards)){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<MemberCard> selectNoneDelete() {
        return memberCardDao.selectByCondition(0,null);
    }

    @Override
    public List<MemberCard> selectValid() {
        return memberCardDao.selectByCondition(0,0);
    }
}