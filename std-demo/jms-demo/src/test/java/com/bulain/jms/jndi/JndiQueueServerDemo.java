package com.bulain.jms.jndi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.jms.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-resource.xml",
        "classpath:spring/applicationContext-jndi.xml"})
public class JndiQueueServerDemo {

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
    public void testQueueConsumer() throws JMSException, InterruptedException {
        QueueConnectionFactory cf = (QueueConnectionFactory) connectionFactory;
        QueueConnection conn = cf.createQueueConnection();
        conn.start();

        final QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        QueueReceiver receiver = session.createReceiver((Queue) destinationA);
        receiver.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                logger.debug("Queue Receive: {}", msg);

                try {
                    String jmsMessageID = msg.getJMSMessageID();
                    Destination replyTo = msg.getJMSReplyTo();

                    QueueSender sender = session.createSender((Queue) replyTo);
                    TextMessage respMessage = session.createTextMessage();
                    respMessage.setJMSCorrelationID(jmsMessageID);
                    respMessage.setText("Queue Response Message");
                    sender.send(respMessage);

                    sender.close();
                    
                    logger.debug("Queue Send: {}", respMessage);
                } catch (JMSException e) {
                    logger.error("onMessage()-", e);
                }
            }
        });

        Thread.sleep(5 * 1000);

        receiver.close();
        session.close();
        conn.close();
    }

}
