package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class ForeachTest extends JbpmTestCase {
    String deploymentId;

    @Before
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/foreach.jpdl.xml").deploy();

        identityService.createUser("user1", "user1", "user1");
        identityService.createUser("user2", "user2", "user2");
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);
        identityService.deleteUser("user1");
        identityService.deleteUser("user2");
    }

    @Test
    public void testForeach() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("users", new String[]{"user1", "user2"});
        variables.put("number", 2);
        ProcessInstance processInstance = executionService.startProcessInstanceByKey("foreach", variables);
        String pid = processInstance.getId();

        executionService.signalExecutionById(pid);

        List<Task> listTask = taskService.findGroupTasks("user1");
        assertEquals(1, listTask.size());
        Task task = listTask.get(0);
        taskService.takeTask(task.getId(), "user1");
        taskService.completeTask(task.getId());

        listTask = taskService.findGroupTasks("user2");
        assertEquals(1, listTask.size());
        task = listTask.get(0);
        taskService.takeTask(task.getId(), "user2");
        taskService.completeTask(task.getId());

        processInstance = executionService.signalExecutionById(pid);
        assertEquals(true, processInstance.isEnded());
    }
}
