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

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-resource.xml",
        "classpath:spring/applicationContext-jndi.xml"})
class JndiTopicSubscriberTest {

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
    void testTopicConsumer() throws JMSException, InterruptedException {

        int nThreads = 2;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(nThreads * 2);

        for (int i = 0; i < nThreads; i++) {
            executorService.submit(new JndiTopicConsumer((TopicConnectionFactory) connectionFactory,
                    (Topic) destinationC, latch));
            executorService.submit(new JndiTopicDurableSubscriber((TopicConnectionFactory) connectionFactory,
                    (Topic) destinationC, "client-" + i, latch));
        }
        Assertions.assertTrue(true);

        latch.await();
        executorService.shutdown();

    }

}

class JndiTopicConsumer implements Callable<Integer> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private TopicConnectionFactory factory;
    private Topic topic;
    private CountDownLatch latch;

    public JndiTopicConsumer(TopicConnectionFactory factory, Topic topic, CountDownLatch latch) {
        this.factory = factory;
        this.topic = topic;
        this.latch = latch;
    }

    @Override
    public Integer call() throws Exception {
        TopicConnection conn = factory.createTopicConnection();
        conn.start();

        TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = session.createSubscriber(topic);
        subscriber.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                logger.debug("Topic Message: {}", msg);
            }
        });

        Thread.sleep(60 * 1000);

        subscriber.close();
        session.close();
        conn.close();

        latch.countDown();

        return 0;
    }

}

class JndiTopicDurableSubscriber implements Callable<Integer> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private TopicConnectionFactory factory;
    private Topic topic;
    private String clientId;
    private CountDownLatch latch;

    public JndiTopicDurableSubscriber(TopicConnectionFactory factory, Topic topic, String clientId, CountDownLatch latch) {
        this.factory = factory;
        this.topic = topic;
        this.clientId = clientId;
        this.latch = latch;
    }

    @Override
    public Integer call() throws Exception {
        String name = Thread.currentThread().getName();

        TopicConnection conn = factory.createTopicConnection();
        conn.setClientID(clientId);
        conn.start();

        TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, name);
        subscriber.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                logger.debug("Topic Message: {}", msg);
            }
        });

        Thread.sleep(60 * 1000);

        subscriber.close();
        session.close();
        conn.close();

        latch.countDown();

        return 0;
    }

}
