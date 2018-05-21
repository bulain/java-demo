package com.bulain.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * 邮件服务，主要提供邮件发送服务
 * @author Bulain
 * */
public interface MailService {
    /**
     * 发送单封邮件
     * 
     * @param simpleMessage 单封邮件内容
     * 包括发件人，回复路径，收件人，抄送，密送，发送时间，主题，内容
     * */
    void send(SimpleMailMessage simpleMessage);

    /**
     * 批量发送多封邮件
     * 
     * @param simpleMessages 多封邮件内容
     * 包括发件人，回复路径，收件人，抄送，密送，发送时间，主题，内容
     * */
    void send(SimpleMailMessage[] simpleMessages);

    /**
     * 发送单封邮件(带附件)
     * @param mimeMessagePreparator 邮件准备接口
     */
    void send(MimeMessagePreparator mimeMessagePreparator);

    /**
     * 批量发送多封邮件(带附件)
     * @param mimeMessagePreparators 邮件准备接口
     */
    void send(MimeMessagePreparator[] mimeMessagePreparators);

}
