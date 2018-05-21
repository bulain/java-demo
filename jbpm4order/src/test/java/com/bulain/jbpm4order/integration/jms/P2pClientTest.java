package com.bulain.jbpm4order.integration.jms;

import static org.junit.Assert.assertEquals;

import javax.jms.JMSException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.jms.P2pClient;

public class P2pClientTest extends ServiceTestCase {
    @Autowired
    private P2pClient p2pClient;

    @Test
    public void testSendMessage() throws JmsException, JMSException {
        for (int i = 0; i < 20; i++) {
            String body = "This is a test message." + i;
            String messageId = p2pClient.sendMessage(body);
            String message = p2pClient.receiveMessage(messageId);
            assertEquals("Server: " + body, message);
        }
    }

}
