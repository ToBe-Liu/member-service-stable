package com.wkyc.member.core.service;

import com.wkyc.member.api.dao.MemberPurchaseRecordDao;
import com.wkyc.member.api.domain.MemberPurchaseRecord;
import com.wkyc.member.api.service.MemberPurchaseRecordService;
import com.zjlp.face.util.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 会员卡购买记录表
 *@author LiuXingHai
 *@date 2018/09/14
 */
@Service("memberPurchaseRecordService")
public class MemberPurchaseRecordServiceImpl implements MemberPurchaseRecordService {

    @Autowired
    private MemberPurchaseRecordDao memberPurchaseRecordDao;
    private static final Logger logger = LoggerFactory.getLogger(MemberPurchaseRecordServiceImpl.class);
    @Override
    public Boolean insert(MemberPurchaseRecord memberPurchaseRecord) {
        //参数检验
        if (memberPurchaseRecord == null){
            logger.info("【新增】【会员卡购买记录】失败！记录为空！");
            return false;
        }
        memberPurchaseRecord.setUpdateTime(new Date());
        memberPurchaseRecord.setCreateTime(new Date());
        return memberPurchaseRecordDao.insert(memberPurchaseRecord) > 0;
    }

    @Override
    public Boolean update(MemberPurchaseRecord memberPurchaseRecord) {
        //参数检验
        if (memberPurchaseRecord == null){
            logger.info("【更新】【会员卡购买记录】失败！记录为空！");
            return false;
        }
        if (memberPurchaseRecord.getId() == null){
            logger.info("【更新】【会员卡购买记录】失败！id为空！");
            return false;
        }
        memberPurchaseRecord.setUpdateTime(new Date());
        memberPurchaseRecord.setCreateTime(null);
        return memberPurchaseRecordDao.update(memberPurchaseRecord) > 0;
    }

    @Override
    public Boolean updateByTSN(MemberPurchaseRecord memberPurchaseRecord) {
        //参数检验
        if (memberPurchaseRecord == null){
            logger.info("【更新】【会员卡购买记录】失败！记录为空！");
            return false;
        }
        if (memberPurchaseRecord.getTransactionSerialNumber() == null){
            logger.info("【更新】【会员卡购买记录】失败！transactionSerialNumber 为空！");
            return false;
        }
        return memberPurchaseRecordDao.updateByTSN(memberPurchaseRecord) > 0;
    }

    @Override
    public MemberPurchaseRecord selectByTSN(String transactionSerialNumber) {
        //参数检验
        if (StringUtils.isBlank(transactionSerialNumber)){
            logger.info("【查询】【会员卡购买记录】失败！transactionSerialNumber为空！");
            return null;
        }
        return memberPurchaseRecordDao.selectByTSN(transactionSerialNumber);
    }

    @Override
    public Pagination<MemberPurchaseRecord> selectByPagination(MemberPurchaseRecord memberPurchaseRecord,Pagination<MemberPurchaseRecord> pagination) {
        //参数检验
        if (pagination == null){
            logger.info("【分页查询】【会员卡购买记录】失败！参数为空！");
            return null;
        }
        List<MemberPurchaseRecord> memberPurchaseRecords = memberPurchaseRecordDao.selectByPagination(memberPurchaseRecord,pagination);
        Long count = memberPurchaseRecordDao.countByPagination(memberPurchaseRecord);
        pagination.setDatas(memberPurchaseRecords);
        pagination.setTotalRow(count.intValue());
        return pagination;
    }
}