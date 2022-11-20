package com.bulain.jms.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class TopicPublisherDemo {

    @Autowired
    private JmsTemplate jmsTemplateC;

    @Test
    public void testPublish() {
        for (int i = 0; i < 1; i++) {
            String body = "This is a topic message." + i;
            jmsTemplateC.convertAndSend(body);
        }
    }
    
}