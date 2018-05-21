package com.bulain.jencks.it.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public final class ActiveMqQueueIT {
	private static final Logger logger = Logger.getLogger(ActiveMqQueueIT.class);

	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;

	private boolean transacted = false;

	@Before
	public void setUp() throws JMSException {
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "vm://localhost:61616");
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection
				.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue("Demo.A");
	}

	@After
	public void tearDown() throws JMSException {
		connection.stop();
		connection.close();
	}

	@Test
	public void testQueue() throws Exception {
		createConsumerAndReceiveAMessage();
		createProducerAndSendAMessage();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	private void createProducerAndSendAMessage() throws JMSException {
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		TextMessage message = session.createTextMessage("Hello World!");
		logger.debug("createProducerAndSendAMessage() - Sending message: "
				+ message.getText());
		producer.send(message);
	}

	private void createConsumerAndReceiveAMessage() throws JMSException {
		MessageConsumer consumer = session.createConsumer(destination);
		MyConsumer myConsumer = new MyConsumer();
		connection.setExceptionListener(myConsumer);
		consumer.setMessageListener(myConsumer);
	}

	private static class MyConsumer implements MessageListener,
			ExceptionListener {
		private static final Logger logger = Logger.getLogger(MyConsumer.class);

		public synchronized void onException(JMSException ex) {
			logger.debug("onException(JMSException) - JMS Exception occured.  Shutting down client.");
		}

		public void onMessage(Message message) {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				try {
					logger.debug("onMessage(Message) - Received message: "
							+ textMessage.getText());
				} catch (JMSException ex) {
					logger.debug("onMessage(Message) - Error reading message: "
							+ ex);
				}
			} else {
				logger.debug("onMessage(Message) - Received: " + message);
			}
		}
	}
}
