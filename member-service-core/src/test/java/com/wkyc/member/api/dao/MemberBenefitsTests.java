package com.wkyc.member.api.dao;

import com.wkyc.member.api.domain.MemberBenefits;
import com.wkyc.member.api.domain.MemberCard;
import com.wkyc.member.core.Application;
import com.zjlp.face.jredis.jredis.cluster.JedisClusterInitialize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MemberBenefitsTests {

	@Autowired
	private MemberBenefitsDao memberBenefitsDao;

	static {
		try {
			JedisClusterInitialize.getInstance().initialized();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
	}
	@Test
	public void selectByCondition() {
	}
	@Test
	public void testInsert() {
		MemberBenefits memberBenefits = new MemberBenefits();
		memberBenefits.setName("积分");
		memberBenefits.setMemberCardId(1L);
		memberBenefits.setRemark("积分");
		memberBenefits.setValue(200);
		memberBenefits.setCreateTime(new Date());
		memberBenefits.setUpdateTime(new Date());
		memberBenefitsDao.insert(memberBenefits);
	}

}
