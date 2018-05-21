package com.bulain.jencks.jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;

public class P2pClient {
	private JmsTemplate jmsTemplateA;
	private JmsTemplate jmsTemplateB;

	public String sendMessage(final String body) {
		final List<Message> listMessage = new ArrayList<Message>();
		jmsTemplateA.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage();
				textMessage.setText(body);
				listMessage.add(textMessage);
				return textMessage;
			}
		});

		String jmsMessageID = null;
		try {
			Message message = listMessage.get(0);
			jmsMessageID = message.getJMSMessageID();
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}

		return jmsMessageID;
	}

	public String receiveMessage(String correlationId) {
		return (String) jmsTemplateB
				.receiveSelectedAndConvert("JMSCorrelationID='" + correlationId
						+ "'");
	}

	public void setJmsTemplateA(JmsTemplate jmsTemplateA) {
		this.jmsTemplateA = jmsTemplateA;
	}

	public void setJmsTemplateB(JmsTemplate jmsTemplateB) {
		this.jmsTemplateB = jmsTemplateB;
	}

}
