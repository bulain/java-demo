package com.bulain.jbpm4order.mail;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {
    void send(SimpleMailMessage simpleMessage);
    void send(SimpleMailMessage[] simpleMessages);
}
