package com.bulain.jbpm4order.integration.jms;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public final class ActiveMqJndi {
    private static final Logger logger = Logger.getLogger(ActiveMqJndi.class);

    private static final String NAME_FACTORY = "java.naming.factory.initial";
    private static final String NAME_PROVIDER = "java.naming.provider.url";
    private static final String NAME_QUEUE = "queue.Demo.A";

    private static final String FACTORY = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
    private static final String PROVIDER = "vm://localhost:61616";
    private static final String QUEUE = "Demo.A";
    private static final String CONNECTION_FACTORY = "ConnectionFactory";

    private ConnectionFactory factory;
    private Destination destination;

    private ActiveMqJndi() {
    }

    public static void main(String[] args) {
        try {
            ActiveMqJndi test = new ActiveMqJndi();
            test.setup();
            test.testQueue();
        } catch (NamingException e) {
            logger.error("main(String[])", e);
        } catch (JMSException e) {
            logger.error("main(String[])", e);
        }
    }

    private void setup() throws NamingException {
        Hashtable<String, String> map = new Hashtable<String, String>();
        map.put(NAME_FACTORY, FACTORY);
        map.put(NAME_PROVIDER, PROVIDER);
        map.put(NAME_QUEUE, QUEUE);

        Context ctx = new InitialContext(map);
        factory = (ConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
        destination = (Destination) ctx.lookup(QUEUE);
    }

    private void testQueue() throws JMSException {
        QueueConnectionFactory cf = (QueueConnectionFactory) factory;
        QueueConnection conn = cf.createQueueConnection();
        Session session = conn.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage();

        String body = "This is a test message.";
        message.setText(body);

        producer.send(message);
        logger.debug("testQueue() - Send: " + body);

        producer.close();
        session.close();
        conn.close();
    }
}
