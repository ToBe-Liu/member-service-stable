package com.wkyc.member.api.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员权益表
 *@author LiuXingHai
 *@date 2018/09/14
 */
public class MemberBenefits implements Serializable {
    /**  */
    private Long id;

    /** 所属会员卡id */
    private Long memberCardId;

    /** 权益名称 */
    private String name;

    /** 权益值 */
    private Integer value;

    /** 备注 */
    private String remark;

    /** 修改时间 */
    private Date updateTime;

    /** 创建时间 */
    private Date createTime;

    /** 会员权益 折扣 */
    public static final String MEMBER_BENEFITS_DISCOUNT = "折扣";
    /** 会员权益 积分 */
    public static final String MEMBER_BENEFITS_INTEGRATION = "积分";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(Long memberCardId) {
        this.memberCardId = memberCardId;
    }
}