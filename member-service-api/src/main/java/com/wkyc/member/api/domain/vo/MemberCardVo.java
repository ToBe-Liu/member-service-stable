package com.wkyc.member.api.domain.vo;

import com.wkyc.member.api.domain.MemberBenefits;
import com.wkyc.member.api.domain.MemberCard;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 会员卡表
 *@author LiuXingHai
 *@date 2018/09/14
 */
public class MemberCardVo extends MemberCard{

    /** 简化，去重后的权益集合 */
    private List<String> benefits;
    /** 权益集合 */
    private List<MemberBenefits> memberBenefits;

    public MemberCardVo() {
    }

    public List<String> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<String> benefits) {
        this.benefits = benefits;
    }

    public List<MemberBenefits> getMemberBenefits() {
        return memberBenefits;
    }

    public void setMemberBenefits(List<MemberBenefits> memberBenefits) {
        this.memberBenefits = memberBenefits;
    }
}