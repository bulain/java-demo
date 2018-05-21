package com.bulain.jbpm4order.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailServiceImpl implements MailService {
    private MailSender mailSender;
    private String from;

    public void send(SimpleMailMessage simpleMessage) {
        simpleMessage.setFrom(from);
        mailSender.send(simpleMessage);
    }

    public void send(SimpleMailMessage[] simpleMessages) {
        for (SimpleMailMessage simpleMessage : simpleMessages) {
            simpleMessage.setFrom(from);
        }
        mailSender.send(simpleMessages);
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
