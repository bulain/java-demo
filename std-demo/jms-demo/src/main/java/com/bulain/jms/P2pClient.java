package com.bulain.jms;

import java.util.ArrayList;
import java.util.List;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
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

        Message message = listMessage.get(0);

        try {
            return message.getJMSMessageID();
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
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
