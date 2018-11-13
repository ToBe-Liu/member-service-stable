package com.wkyc.member.api.domain;

import java.io.Serializable;
import java.util.Date;
/**
 *  会员累计节省资金表
 *@author LiuXingHai
 *@date 2018/9/21
 */
public class MemberSaveMoney implements Serializable{
    /**  */
    private Long id;

    /** 用户id */
    private Long userId;

    /** 累计节省资金 */
    private Long money;

    /** 修改时间 */
    private Date updateTime;

    /** 创建时间 */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}