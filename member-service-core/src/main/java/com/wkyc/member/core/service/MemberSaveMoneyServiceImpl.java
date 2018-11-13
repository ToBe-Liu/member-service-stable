package com.wkyc.member.core.service;

import com.wkyc.member.api.dao.MemberSaveMoneyDao;
import com.wkyc.member.api.domain.MemberSaveMoney;
import com.wkyc.member.api.service.MemberSaveMoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 会员累计节省资金表
 *@author LiuXingHai
 *@date 2018/09/21
 */
@Service("memberSaveMoneyService")
public class MemberSaveMoneyServiceImpl implements MemberSaveMoneyService {

    @Autowired
    private MemberSaveMoneyDao memberSaveMoneyDao;
    private static final Logger logger = LoggerFactory.getLogger(MemberSaveMoneyServiceImpl.class);

    @Override
    public Boolean insert(MemberSaveMoney record) {
        //参数检验
        if (record == null){
            logger.info("【新增】【会员累计节省资金】失败！记录为空！");
            return false;
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        return memberSaveMoneyDao.insert(record) > 0;
    }

    @Override
    public Boolean update(MemberSaveMoney record) {
        //参数检验
        if (record == null){
            logger.info("【更新】【会员累计节省资金】失败！记录为空！");
            return false;
        }
        record.setCreateTime(null);
        record.setUpdateTime(new Date());
        return memberSaveMoneyDao.update(record) > 0;
    }

    @Override
    public MemberSaveMoney selectByUserID(Long userId) {
        //参数检验
        if (userId == null){
            logger.info("【查询】【会员累计节省资金】失败！userId为空！");
            return null;
        }

        return memberSaveMoneyDao.selectByUserID(userId);
    }

    @Override
    public Boolean updateByUserID(MemberSaveMoney record) {
        //参数检验
        if (record == null){
            logger.info("【更新】【会员累计节省资金】失败！记录为空！");
            return null;
        }
        if (record.getUserId() == null){
            logger.info("【更新】【会员累计节省资金】失败！userId为空！");
            return null;
        }


        return memberSaveMoneyDao.updateByUserID(record) > 0;
    }

    @Override
    public Boolean updateTotalMoney(Long userId, Long money) {
        //参数检验
        if (userId == null || money == null || money < 0){
            logger.info("【更新】【会员累计节省资金】失败！参数为空！");
            return false;
        }

        MemberSaveMoney oldMemberSaveMoney = memberSaveMoneyDao.selectByUserID(userId);
        if (oldMemberSaveMoney == null){
            //如果没有记录则新增
            MemberSaveMoney memberSaveMoney = new MemberSaveMoney();
            memberSaveMoney.setUpdateTime(new Date());
            memberSaveMoney.setCreateTime(new Date());
            memberSaveMoney.setUserId(userId);
            memberSaveMoney.setMoney(money);
            memberSaveMoneyDao.insert(memberSaveMoney);
        } else {
            //如果有记录则更新
            oldMemberSaveMoney.setMoney(oldMemberSaveMoney.getMoney() + money);
            oldMemberSaveMoney.setUpdateTime(new Date());
            memberSaveMoneyDao.updateByUserID(oldMemberSaveMoney);
        }
        return true;
    }
}