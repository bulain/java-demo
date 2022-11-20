package com.bulain.jms.service;

import com.bulain.jms.P2pClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class P2pClientDemo {

    @Autowired
    private P2pClient p2pClient;

    @Test
    public void testSendMessage() {
        for (int i = 0; i < 1; i++) {
            String body = "This is a queue message." + i;
            String messageId = p2pClient.sendMessage(body);
            String message = p2pClient.receiveMessage(messageId);
            assertEquals("Server: " + body, message);
        }
    }

}
