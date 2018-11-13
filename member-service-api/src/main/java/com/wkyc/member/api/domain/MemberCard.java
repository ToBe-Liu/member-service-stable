package com.wkyc.member.api.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员卡表
 *@author LiuXingHai
 *@date 2018/09/14
 */
public class MemberCard implements Serializable {
    /**  */
    private Long id;

    /** 名称 */
    private String name;

    /** 图片 */
    private String image;

    /** 售价 */
    private Long price;

    /** 使用须知 */
    private String remark;

    /** 有效期（单位：天） */
    private Long indate;

    /** 商品id */
    private Long goodId;

    /** 是否删除 0 未删除 1 已删除 */
    private Integer isDelete;

    /** 是否禁用 0 未禁用 1 已禁用 */
    private Integer isDisable;

    /** 修改时间 */
    private Date updateTime;

    /** 创建时间 */
    private Date createTime;
    /** 会员等级 */
    private Integer level;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getIndate() {
        return indate;
    }

    public void setIndate(Long indate) {
        this.indate = indate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }
}