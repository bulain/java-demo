package com.bulain.jbpm4order.mail;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bulain.common.dataset.DataSetTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class, DataSetTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-resource.xml",
        "classpath*:spring/applicationContext-mail.xml"})
public class JavaMailSenderDemo {
    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testSendMail() throws MessagingException, IOException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("bulain@126.com");
        mail.setTo("bulain@163.com");
        mail.setSubject("Test email from 126.com");
        mail.setText("send from bulain@126.com to bulain@163.com");
        mailSender.send(mail);
    }

    @Test
    public void testSendMailWithAlternative() throws MessagingException, IOException {
        mailSender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("bulain@126.com");
                message.setTo("bulain@163.com");
                message.setSubject("Test email from 126.com with alternative");
                message.setText("send from bulain@126.com to bulain@163.com");
                message.setText("plainText", "htmlText");
            }
        });
    }

    @Test
    public void testSendMailWithAttachment() throws MessagingException, IOException {
        mailSender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("bulain@126.com");
                message.setTo("bulain@163.com");
                message.setSubject("Test email from 126.com with attachment");
                message.setText("send from bulain@126.com to bulain@163.com");
                File file = getFileFromClassPath("log4j.properties");
                message.addAttachment("log4j.properties", file);
            }
        });
    }

    @Test
    public void testSendMailWithInline() throws MessagingException, IOException {
        mailSender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("bulain@126.com");
                message.setTo("bulain@163.com");
                message.setSubject("Test email from 126.com with inline");
                message.setText("send from bulain@126.com to bulain@163.com");
                File file = getFileFromClassPath("log4j.properties");
                message.addInline("log4j.properties", file);
            }
        });
    }

    private File getFileFromClassPath(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        return resource.getFile();
    }

}
