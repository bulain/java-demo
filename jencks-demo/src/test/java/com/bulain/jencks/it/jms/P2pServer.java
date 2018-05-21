package com.bulain.jencks.it.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath*:spring/jms-server.xml",
		"classpath*:spring/propertyConfigurer-jms-server.xml" })
public class P2pServer {
	@Test
	public void testServer() {
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
