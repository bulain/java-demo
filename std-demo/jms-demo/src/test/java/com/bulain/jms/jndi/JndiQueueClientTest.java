package com.bulain.jms.jndi;


import jakarta.jms.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-resource.xml",
        "classpath:spring/applicationContext-jndi.xml"})
class JndiQueueClientTest {

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
    void testJndi() {
        assertNotNull(connectionFactory);
        assertNotNull(destinationA);
        assertNotNull(destinationB);
        assertNotNull(destinationC);
    }

    @Test
    void testQueueSender() throws JMSException, InterruptedException {
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
        Assertions.assertNotNull(replyMessage);
        logger.debug("Response: {}", replyMessage);

        receiver.close();
        session.close();
        conn.close();
    }

}
