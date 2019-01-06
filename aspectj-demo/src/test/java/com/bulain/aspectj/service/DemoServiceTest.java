package com.bulain.aspectj.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bulain.aspectj.pojo.DemoReq;
import com.bulain.aspectj.pojo.DemoResp;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class DemoServiceTest {

	@Autowired
	private DemoService demoService;

	@Test
	public void testPage() {
		DemoReq req = new DemoReq();
		req.setName("bulain");
		req.setTxType("sign");

		DemoResp resp = demoService.page(req);

		assertNotNull(resp);
	}

}
