package com.bulain.jencks.it.jms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.bulain.common.test.ServiceTestCase;

public class TopicPublicerIT extends ServiceTestCase {
	@Autowired
	private JmsTemplate jmsTemplateC;

	@Test
	public void testPublic() {
		jmsTemplateC.convertAndSend("This is a public Message!");
	}
}
