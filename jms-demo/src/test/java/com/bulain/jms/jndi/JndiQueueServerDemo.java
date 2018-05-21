package com.bulain.jms.jndi;

import static org.junit.Assert.assertNotNull;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
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
