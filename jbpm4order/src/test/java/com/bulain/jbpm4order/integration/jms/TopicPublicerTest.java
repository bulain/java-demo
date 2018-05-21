package com.bulain.jbpm4order.integration.jms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.bulain.common.test.ServiceTestCase;

public class TopicPublicerTest extends ServiceTestCase {
    @Autowired
    private JmsTemplate jmsTemplateC;

    @Test
    public void testPublic() {
        jmsTemplateC.convertAndSend("This is a public Message!");
    }
}
