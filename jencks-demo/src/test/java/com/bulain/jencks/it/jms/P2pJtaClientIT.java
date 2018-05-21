package com.bulain.jencks.it.jms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jencks.jms.P2pJtaClient;

public class P2pJtaClientIT extends ServiceTestCase {

	@Autowired
	private P2pJtaClient p2pJtaClient;

	@Test
	public void testReceiveMessage() {
		for (int i = 0; i < 20; i++) {
			String body = "This is a test message." + i;
			p2pJtaClient.sendMessage(body);
		}
	}

}
