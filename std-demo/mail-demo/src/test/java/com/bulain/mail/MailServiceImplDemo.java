package com.bulain.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.File;
import java.io.IOException;

/**
 * @author Bulain
 */
@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml",
        "classpath:spring/propertyConfigurer-test.xml"})
public class MailServiceImplDemo {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() throws MessagingException, IOException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("bulain@163.com");
        mail.setSubject("Test email from bulain@126.com");
        mail.setText("send from bulain@126.com to you");
        mailService.send(mail);
    }

    @Test
    public void testSendMailWithAlternative() throws MessagingException, IOException {
        mailService.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setTo("bulain@163.com");
                message.setSubject("Test email from bulain@126.com with alternative");
                message.setText("send from bulain@126.com to you");
                message.setText("plainText", "htmlText");
            }
        });
    }

    @Test
    public void testSendMailWithAttachment() throws MessagingException, IOException {
        mailService.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setTo("bulain@163.com");
                message.setSubject("Test email from bulain@126.com with attachment");
                message.setText("send from bulain@126.com to you");
                File file = getFileFromClassPath("mail.properties");
                message.addAttachment("mail.properties", file);
            }
        });
    }

    @Test
    public void testSendMailWithInline() throws MessagingException, IOException {
        mailService.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setTo("bulain@163.com");
                message.setSubject("Test email from bulain@126.com with inline");
                message.setText("send from bulain@126.com to you");
                File file = getFileFromClassPath("mail.properties");
                message.addInline("mail.properties", file);
            }
        });
    }

    private File getFileFromClassPath(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        return resource.getFile();
    }

}
