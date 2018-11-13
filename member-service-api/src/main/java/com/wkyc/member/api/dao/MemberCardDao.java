package com.wkyc.member.api.dao;

import com.wkyc.member.api.domain.MemberCard;

import java.util.List;

/**
 * 会员卡表
 *@author LiuXingHai
 *@date 2018/09/14
 */
public interface MemberCardDao {
    /**
     *  新增
     *@author LiuXingHai
     *@date 2018/9/14
     */
    int insert(MemberCard memberCard);

    /**
     *  更新
     *@author LiuXingHai
     *@date 2018/9/14
     */
    int update(MemberCard memberCard);

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
     *  同过会员卡名查询
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCard> selectByName(String name);



    /**
     *  通过条件查询
     *@author LiuXingHai
     *@date 2018/9/14
     */
    List<MemberCard> selectByCondition(Integer delete , Integer disable);

}