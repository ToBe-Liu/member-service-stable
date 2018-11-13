package com.wkyc.member.api.service;

import com.wkyc.member.core.Application;
import com.zjlp.face.jredis.jredis.cluster.JedisClusterInitialize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MemberCardServiceTests {

	@Autowired
	private MemberService memberService;

	static {
		try {
			JedisClusterInitialize.getInstance().initialized();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteBatch() {
	}
	@Test
	public void testInsert() {
		memberService.paySuccess("101809201541530971667");
	}

}
