package com.wkyc.member.api.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员卡购买记录表
 *@author LiuXingHai
 *@date 2018/09/14
 */
public class MemberPurchaseRecord implements Serializable {
    /**  */
    private Long id;

    /** 所属会员卡id */
    private Long memberCardId;

    /** 会员卡名称 */
    private String memberCardName;

    /** 会员id */
    private Long userId;

    /** 会员昵称 */
    private String userName;

    /** 会员手机号 */
    private String userPhone;

    /** 交易流水号 */
    private String transactionSerialNumber;

    /** 支付状态：1银商已下单2银商受理中3支付成功4支付失败 */
    private Integer payStatus;

    /** 交易状态：1 成功 2 失败 */
    private Integer tradeStatus;

    /** 修改时间 */
    private Date updateTime;

    /** 创建时间 */
    private Date createTime;

    /** 推荐人id */
    private Long recommendUserId;

    /** 推荐人名 */
    private String recommendUserName;

    /** 交易状态：成功 */
    public static final Integer TRADE_STATUS_SUCCESS = 1;
    /** 交易状态：失败 */
    public static final Integer TRADE_STATUS_FAIL = 2;

    /** 支付状态：已下单 */
    public static final Integer PAY_STATUS_ORDERED = 1;
    /** 支付状态：受理中 */
    public static final Integer PAY_STATUS_DEALING = 2;
    /** 支付状态：成功 */
    public static final Integer PAY_STATUS_SUCCESS = 3;
    /** 支付状态：失败 */
    public static final Integer PAY_STATUS_FAIL = 4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberCardName() {
        return memberCardName;
    }

    public void setMemberCardName(String memberCardName) {
        this.memberCardName = memberCardName == null ? null : memberCardName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getTransactionSerialNumber() {
        return transactionSerialNumber;
    }

    public void setTransactionSerialNumber(String transactionSerialNumber) {
        this.transactionSerialNumber = transactionSerialNumber;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Long getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(Long memberCardId) {
        this.memberCardId = memberCardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecommendUserId() {
        return recommendUserId;
    }

    public void setRecommendUserId(Long recommendUserId) {
        this.recommendUserId = recommendUserId;
    }

    public String getRecommendUserName() {
        return recommendUserName;
    }

    public void setRecommendUserName(String recommendUserName) {
        this.recommendUserName = recommendUserName;
    }
}