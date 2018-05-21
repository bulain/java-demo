package com.bulain.jbpm4order.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import com.bulain.common.dataset.DataSetTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class, DataSetTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-resource.xml",
        "classpath*:spring/applicationContext-mail.xml"})
public class MailServiceImplTest {
    @Autowired
    private MailService mailService;

    private Wiser wiser = new Wiser();

    @Before
    public void setUp() throws Exception {
        wiser.setPort(2525);
        wiser.start();
    }

    @After
    public void tearDown() throws Exception {
        wiser.stop();
    }

    @Test
    public void testSendSimpleMailMessage() throws MessagingException, IOException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("test-to@wiser.com");
        mail.setSubject("Test email from wiser.com");
        mail.setText("send from bulain to test-to");
        mailService.send(mail);

        List<WiserMessage> wisMessages = wiser.getMessages();
        assertEquals(1, wisMessages.size());

        for (WiserMessage wisMessage : wisMessages) {
            Message message = wisMessage.getMimeMessage();

            Address[] from = message.getFrom();
            assertEquals(1, from.length);
            assertEquals("noreply@wiser.com", from[0].toString());

            Address[] expectedTo = InternetAddress.parse("test-to@wiser.com");
            Address[] to = message.getRecipients(RecipientType.TO);
            assertTrue(Arrays.equals(expectedTo, to));

            assertEquals("Test email from wiser.com", message.getSubject());

            assertEquals("send from bulain to test-to", (String) message.getContent());
        }
    }

    @Test
    public void testSendArraySimpleMailMessage() throws MessagingException, IOException {
        SimpleMailMessage mail1 = new SimpleMailMessage();
        mail1.setTo("test-to@wiser.com");
        mail1.setSubject("Test email from wiser.com");
        mail1.setText("send from bulain to test-to");

        SimpleMailMessage mail2 = new SimpleMailMessage();
        mail2.setTo("test-to@wiser.com");
        mail2.setSubject("Test email from wiser.com");
        mail2.setText("send from bulain to test-to");

        mailService.send(new SimpleMailMessage[]{mail1, mail2});

        List<WiserMessage> wisMessages = wiser.getMessages();
        assertEquals(3, wisMessages.size());

        for (WiserMessage wisMessage : wisMessages) {
            Message message = wisMessage.getMimeMessage();

            Address[] from = message.getFrom();
            assertEquals(1, from.length);
            assertEquals("noreply@wiser.com", from[0].toString());

            Address[] expectedTo = InternetAddress.parse("test-to@wiser.com");
            Address[] to = message.getRecipients(RecipientType.TO);
            assertTrue(Arrays.equals(expectedTo, to));

            assertEquals("Test email from wiser.com", message.getSubject());

            assertEquals("send from bulain to test-to", (String) message.getContent());
        }
    }

}
