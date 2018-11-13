package com.wkyc.member.api.dao;

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
public class MemberCardTests {

	@Autowired
	private MemberCardDao memberCardDao;

	static {
		try {
			JedisClusterInitialize.getInstance().initialized();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		MemberCard memberCard = memberCardDao.selectById(1L);
		memberCard.setIsDelete(1);
		memberCard.setIsDisable(1);
		memberCardDao.update(memberCard);
	}
	@Test
	public void selectByCondition() {
		List<MemberCard> memberCard = memberCardDao.selectByCondition(0,null);
		System.out.println(memberCard);
	}
	@Test
	public void testInsert() {
		MemberCard memberCard = new MemberCard();
		memberCard.setName("会员卡");
		memberCard.setImage("图片");
		memberCard.setPrice(100L);
		memberCard.setIndate(365L);
		memberCard.setRemark("使用须知");
		memberCard.setLevel(1);
		memberCard.setCreateTime(new Date());
		memberCard.setUpdateTime(new Date());
		memberCardDao.insert(memberCard);
	}

}
