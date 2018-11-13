package com.wkyc.member.api.service;

import com.wkyc.member.api.domain.MemberCard;

import java.util.List;

/**
 * 会员卡表
 *@author LiuXingHai
 *@date 2018/09/14
 */
public interface MemberCardService {
    /**
     *  新增
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean insert(MemberCard memberCard);

    /**
     *  更新
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean update(MemberCard memberCard);

    /**
     *  查询
     *@author LiuXingHai
     *@date 2018/9/14
     */
    MemberCard selectById(Long memberCardId);

    /**
     *  同过商品id查询
     *@author LiuXingHai
     *@date 2018/9/14
     */
    MemberCard selectByGoodId(Long goodId);

    /**
     *  是否有相同的会员卡名字
     *@author LiuXingHai
     *@date 2018/9/14
     */
    Boolean hasSameName(String name);

    /**
     *  查询所有非删除的记录
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCard> selectNoneDelete();

    /**
     *  查询所有非删除非禁用的记录
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCard> selectValid();



}