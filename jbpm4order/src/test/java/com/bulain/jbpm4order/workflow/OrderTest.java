package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class OrderTest extends JbpmTestCase {
    private String deploymentId;

    @Before
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/order.jpdl.xml").deploy();
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);
    }

    @Test
    public void testOrderApprove() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "johndoe");
        ProcessInstance processInstance = executionService.startProcessInstanceByKey("order", variables);
        String pid = processInstance.getId();

        List<Task> taskList = taskService.findPersonalTasks("johndoe");
        assertEquals(1, taskList.size());
        Task task = taskList.get(0);
        assertEquals("request", task.getName());
        assertEquals("johndoe", task.getAssignee());

        taskService.completeTask(task.getId());

        taskList = taskService.findPersonalTasks("johndoe");
        assertEquals(0, taskList.size());

        taskList = taskService.findPersonalTasks("bulain");
        assertEquals(1, taskList.size());

        task = taskList.get(0);
        assertEquals("approval", task.getName());
        assertEquals("bulain", task.getAssignee());

        taskService.completeTask(task.getId(), "approve");

        processInstance = executionService.findProcessInstanceById(pid);
        assertNull(processInstance);
    }

    @Test
    public void testOrderReject() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "johndoe");
        ProcessInstance processInstance = executionService.startProcessInstanceByKey("order", variables);
        String pid = processInstance.getId();

        List<Task> taskList = taskService.findPersonalTasks("johndoe");
        assertEquals(1, taskList.size());
        Task task = taskList.get(0);
        assertEquals("request", task.getName());
        assertEquals("johndoe", task.getAssignee());

        taskService.completeTask(task.getId());

        taskList = taskService.findPersonalTasks("johndoe");
        assertEquals(0, taskList.size());

        taskList = taskService.findPersonalTasks("bulain");
        assertEquals(1, taskList.size());

        task = taskList.get(0);
        assertEquals("approval", task.getName());
        assertEquals("bulain", task.getAssignee());

        taskService.completeTask(task.getId(), "reject");

        taskList = taskService.findPersonalTasks("johndoe");
        assertEquals(1, taskList.size());
        task = taskList.get(0);
        assertEquals("request", task.getName());
        assertEquals("johndoe", task.getAssignee());

        taskService.completeTask(task.getId());

        taskList = taskService.findPersonalTasks("johndoe");
        assertEquals(0, taskList.size());

        taskList = taskService.findPersonalTasks("bulain");
        assertEquals(1, taskList.size());

        task = taskList.get(0);
        assertEquals("approval", task.getName());
        assertEquals("bulain", task.getAssignee());

        taskService.completeTask(task.getId(), "approve");

        processInstance = executionService.findProcessInstanceById(pid);
        assertNull(processInstance);
    }
}
