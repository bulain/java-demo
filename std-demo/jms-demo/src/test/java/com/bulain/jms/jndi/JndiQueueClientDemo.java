package com.bulain.jms.jndi;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-resource.xml",
        "classpath:spring/applicationContext-jndi.xml"})
public class JndiQueueClientDemo {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private Destination destinationA;
    @Autowired
    private Destination destinationB;
    @Autowired
    private Destination destinationC;

    @Test
    public void testJndi() {
        assertNotNull(connectionFactory);
        assertNotNull(destinationA);
        assertNotNull(destinationB);
        assertNotNull(destinationC);
    }

    @Test
    public void testQueueSender() throws JMSException, InterruptedException {
        QueueConnectionFactory cf = (QueueConnectionFactory) connectionFactory;
        QueueConnection conn = cf.createQueueConnection();
        conn.start();

        QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        QueueSender sender = session.createSender((Queue) destinationA);
        sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        TextMessage message = session.createTextMessage();

        String body = "This is a Queue message.@" + new Date();
        message.setText(body);
        message.setJMSReplyTo(destinationB);

        sender.send(message);
        sender.close();
        
        logger.debug("Request: {}", message);

        QueueReceiver receiver = session.createReceiver((Queue) destinationB, "JMSCorrelationID='" + message.getJMSMessageID()
                + "'");
        Message replyMessage = receiver.receive();
        logger.debug("Response: {}", replyMessage);

        receiver.close();
        session.close();
        conn.close();
    }

}
