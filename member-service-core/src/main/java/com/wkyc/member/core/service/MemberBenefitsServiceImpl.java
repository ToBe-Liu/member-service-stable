package com.wkyc.member.core.service;

import com.wkyc.member.api.dao.MemberBenefitsDao;
import com.wkyc.member.api.domain.MemberBenefits;
import com.wkyc.member.api.service.MemberBenefitsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 会员权益表
 *@author LiuXingHai
 *@date 2018/09/14
 */
@Service("memberBenefitsService")
public class MemberBenefitsServiceImpl implements MemberBenefitsService {

    @Autowired
    private MemberBenefitsDao memberBenefitsDao;
    private static final Logger logger = LoggerFactory.getLogger(MemberBenefitsServiceImpl.class);
    @Override
    public Boolean insert(MemberBenefits memberBenefits) {
        //参数检验
        if (memberBenefits == null){
            logger.info("【新增】【会员权益】失败！记录为空！");
            return false;
        }
        memberBenefits.setCreateTime(new Date());
        memberBenefits.setUpdateTime(new Date());
        return memberBenefitsDao.insert(memberBenefits) > 0;
    }

    @Override
    public Boolean update(MemberBenefits memberBenefits) {
        //参数检验
        if (memberBenefits == null){
            logger.info("【新增】【会员权益】失败！记录为空！");
            return false;
        }
        memberBenefits.setUpdateTime(memberBenefits.getUpdateTime() == null ? new Date() : memberBenefits.getUpdateTime());
        memberBenefits.setCreateTime(null);
        return memberBenefitsDao.update(memberBenefits) > 0;
    }

    @Override
    public Boolean delete(Long memberBenefitId) {
        //参数检验
        if (memberBenefitId == null){
            logger.info("【删除】【会员权益】失败！memberBenefitId为空！");
            return false;
        }
        return memberBenefitsDao.delete(memberBenefitId) > 0;
    }

    @Override
    public List<MemberBenefits> selectByMemberCardId(Long memberCardId) {
        //参数检验
        if (memberCardId == null){
            logger.info("【查询】【会员权益】失败！memberCardId为空！");
            return null;
        }
        return memberBenefitsDao.selectByMemberCardId(memberCardId);
    }
}