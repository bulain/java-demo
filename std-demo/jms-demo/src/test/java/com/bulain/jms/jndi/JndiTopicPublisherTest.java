package com.bulain.jms.jndi;

import jakarta.jms.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class JndiTopicPublisherTest {

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
    void testTopicProducer() throws JMSException {
        TopicConnectionFactory cf = (TopicConnectionFactory) connectionFactory;
        TopicConnection conn = cf.createTopicConnection();
        conn.start();

        TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicPublisher publisher = session.createPublisher((Topic) destinationC);
        //publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        TextMessage message = session.createTextMessage();

        String body = "This is a topic message.@" + new Date();
        message.setText(body);

        publisher.send(message);

        Assertions.assertTrue(true);

        publisher.close();
        session.close();
        conn.close();
    }

}
