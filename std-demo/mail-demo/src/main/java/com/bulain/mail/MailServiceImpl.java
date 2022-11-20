package com.bulain.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件服务默认实现
 * @author Bulain
 * */
public class MailServiceImpl implements MailService {
    private JavaMailSender mailSender;
    private String from;
    private String fromNick;
    private boolean active;

    @Override
    public void send(SimpleMailMessage simpleMessage) {
        send(new SimpleMailMessage[]{simpleMessage});
    }

    @Override
    public void send(SimpleMailMessage[] simpleMessages) {
        if (active) {
            for (SimpleMailMessage simpleMessage : simpleMessages) {
                simpleMessage.setFrom(from);
            }
            mailSender.send(simpleMessages);
        }
    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) {
        send(new MimeMessagePreparator[]{mimeMessagePreparator});
    }

    @Override
    public void send(MimeMessagePreparator[] mimeMessagePreparators) {
        if (active) {
            try {
                List<MimeMessage> mimeMessages = new ArrayList<MimeMessage>(mimeMessagePreparators.length);
                for (MimeMessagePreparator preparator : mimeMessagePreparators) {
                    MimeMessage mimeMessage = mailSender.createMimeMessage();
                    preparator.prepare(mimeMessage);
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                    if (StringUtils.isNotBlank(fromNick)) {
                        helper.setFrom(new InternetAddress(from, fromNick));
                    } else {
                        helper.setFrom(from);
                    }
                    mimeMessages.add(mimeMessage);
                }
                mailSender.send(mimeMessages.toArray(new MimeMessage[mimeMessages.size()]));
            } catch (MailException ex) {
                throw ex;
            } catch (MessagingException ex) {
                throw new MailParseException(ex);
            } catch (IOException ex) {
                throw new MailPreparationException(ex);
            } catch (Exception ex) {
                throw new MailPreparationException(ex);
            }
        }
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }
}
