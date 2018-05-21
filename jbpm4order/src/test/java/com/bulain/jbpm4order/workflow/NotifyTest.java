package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class NotifyTest extends JbpmTestCase {
    private String deploymentId;
    private String groupId;
    private Wiser wiser = new Wiser();

    @Before
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/notify.jpdl.xml").deploy();

        groupId = identityService.createGroup("group1");
        identityService.createUser("user1", "user1", "user1", "user1@wiser.com");
        identityService.createUser("user2", "user2", "user2", "user2@wiser.com");
        identityService.createMembership("user1", groupId);
        identityService.createMembership("user2", groupId);

        wiser.setPort(2525);
        wiser.start();
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);

        identityService.deleteGroup(groupId);
        identityService.deleteUser("user1");
        identityService.deleteUser("user2");

        wiser.stop();
    }

    @Test
    public void testCandidate() throws MessagingException, IOException {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("groups", groupId);
        executionService.startProcessInstanceByKey("notify", variables);

        List<WiserMessage> wisMessages = wiser.getMessages();
        assertEquals(2, wisMessages.size());

        for (WiserMessage wisMessage : wisMessages) {
            Message message = wisMessage.getMimeMessage();

            Address[] from = message.getFrom();
            assertEquals(1, from.length);
            assertEquals("noreply@wiser.com", from[0].toString());

            Address[] expectedTo = InternetAddress.parse("user1@wiser.com,user2@wiser.com");
            Address[] to = message.getRecipients(RecipientType.TO);
            assertTrue(Arrays.equals(expectedTo, to));

            assertEquals("task1", message.getSubject());
            assertTrue(((String) message.getContent()).contains("task1"));
        }
    }
}
