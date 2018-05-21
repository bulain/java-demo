package com.bulain.jbpm4order.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class P2pServer implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(P2pServer.class);

    private JmsTemplate jmsTemplate;

    public void onMessage(Message message) {
        if (!(message instanceof TextMessage)) {
            return;
        }
        TextMessage tm = (TextMessage) message;

        try {
            sendMessage(tm.getJMSMessageID(), "Server: " + tm.getText());
        } catch (JMSException e) {
            LOG.error("onMessage(Message)", e);
        }
    }

    public void sendMessage(final String correlationID, final String body) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setJMSCorrelationID(correlationID);
                textMessage.setText(body);
                return textMessage;
            }
        });
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
