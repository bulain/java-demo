package com.bulain.jbpm4order.jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class P2pClient {
    private JmsTemplate jmsTemplateA;
    private JmsTemplate jmsTemplateB;

    public String sendMessage(final String body) throws JMSException {
        final List<Message> listMessage = new ArrayList<Message>();
        jmsTemplateA.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(body);
                listMessage.add(textMessage);
                return textMessage;
            }
        });

        Message message = listMessage.get(0);
        return message.getJMSMessageID();
    }

    public String receiveMessage(String correlationId) {
        return (String) jmsTemplateB.receiveSelectedAndConvert("JMSCorrelationID='" + correlationId + "'");
    }

    public void setJmsTemplateA(JmsTemplate jmsTemplateA) {
        this.jmsTemplateA = jmsTemplateA;
    }
    public void setJmsTemplateB(JmsTemplate jmsTemplateB) {
        this.jmsTemplateB = jmsTemplateB;
    }

}
