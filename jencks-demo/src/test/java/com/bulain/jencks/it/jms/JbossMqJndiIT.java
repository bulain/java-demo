package com.bulain.jencks.it.jms;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.ConnectionMetaData;
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

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JbossMqJndiIT {
	private static final Logger logger = LoggerFactory.getLogger(ActiveMqJndiIT.class);

	private static final String NAME_FACTORY = "java.naming.factory.initial";
	private static final String NAME_PROVIDER = "java.naming.provider.url";
	private static final String NAME_PKG = "java.naming.factory.url.pkgs";
	private static final String NAME_QUEUE = "queue.Demo.A";

	private static final String FACTORY = "org.jnp.interfaces.NamingContextFactory";
	private static final String PROVIDER = "jnp://localhost:1099";
	private static final String PKG = "org.jboss.naming:org.jnp.interfaces";
	private static final String QUEUE = "queue/QueueA";
	private static final String CONNECTION_FACTORY = "ConnectionFactory";

	private static final boolean transacted = false;

	private ConnectionFactory factory;
	private Destination destination;

	@Before
	public void setup() throws NamingException {
		Hashtable<String, String> map = new Hashtable<String, String>();
		map.put(NAME_FACTORY, FACTORY);
		map.put(NAME_PROVIDER, PROVIDER);
		map.put(NAME_PKG, PKG);
		map.put(NAME_QUEUE, QUEUE);

		Context ctx = new InitialContext(map);
		factory = (ConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
		destination = (Destination) ctx.lookup(QUEUE);
	}

	@Test
	public void testQueue() throws JMSException {
		QueueConnectionFactory cf = (QueueConnectionFactory) factory;
		QueueConnection conn = cf.createQueueConnection();

		ConnectionMetaData metaData = conn.getMetaData();
		System.out.printf("%d %d %s %s\n", metaData.getJMSMajorVersion(),
				metaData.getJMSMinorVersion(), metaData.getJMSProviderName(),
				metaData.getJMSVersion());

		Session session = conn.createSession(transacted,
				QueueSession.AUTO_ACKNOWLEDGE);
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
