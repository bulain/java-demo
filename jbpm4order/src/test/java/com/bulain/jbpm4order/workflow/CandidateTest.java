package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class CandidateTest extends JbpmTestCase {
    private String deploymentId;
    private String groupId;

    @Before
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/candidate.jpdl.xml").deploy();

        groupId = identityService.createGroup("group1");

        identityService.createUser("user1", "user1", "user1");
        identityService.createUser("user2", "user2", "user2");
        identityService.createUser("user3", "user3", "user3");
        identityService.createUser("user4", "user4", "user4");

        identityService.createMembership("user3", groupId);
        identityService.createMembership("user4", groupId);
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);

        identityService.deleteGroup(groupId);

        identityService.deleteUser("user1");
        identityService.deleteUser("user2");
        identityService.deleteUser("user3");
        identityService.deleteUser("user4");

    }

    @Test
    public void testCandidate() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("users", "user1,user2");
        variables.put("groups", groupId);
        executionService.startProcessInstanceByKey("candidate", variables);

        List<Task> findGroupTasks1 = taskService.findGroupTasks("user1");
        List<Task> findGroupTasks2 = taskService.findGroupTasks("user2");
        List<Task> findGroupTasks3 = taskService.findGroupTasks("user3");
        List<Task> findGroupTasks4 = taskService.findGroupTasks("user4");

        assertEquals(1, findGroupTasks1.size());
        assertEquals(1, findGroupTasks2.size());
        assertEquals(1, findGroupTasks3.size());
        assertEquals(1, findGroupTasks4.size());
    }

}
