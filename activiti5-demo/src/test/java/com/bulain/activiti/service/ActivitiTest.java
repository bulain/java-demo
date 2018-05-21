package com.bulain.activiti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.test.ServiceTestCase;

public class ActivitiTest extends ServiceTestCase {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    private String deploymentId;

    @Before
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment().addClasspathResource("diagrams/order.bpmn20.xml").deploy()
                .getId();
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeployment(deploymentId, true);
    }

    @Test
    public void testOrderApprove() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "johndoe");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("order", variables);
        String pid = processInstance.getId();

        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("johndoe").list();
        assertEquals(1, taskList.size());
        Task task = taskList.get(0);
        assertEquals("request", task.getName());

        taskService.claim(task.getId(), "johndoe");
        task = taskService.createTaskQuery().taskAssignee("johndoe").singleResult();
        assertEquals("request", task.getName());
        assertEquals("johndoe", task.getAssignee());

        taskService.complete(task.getId());

        taskList = taskService.createTaskQuery().taskCandidateUser("johndoe").list();
        assertEquals(0, taskList.size());

        taskList = taskService.createTaskQuery().taskCandidateUser("bulain").list();
        assertEquals(1, taskList.size());

        task = taskList.get(0);
        assertEquals("approve", task.getName());
        taskService.claim(task.getId(), "bulain");

        task = taskService.createTaskQuery().taskAssignee("bulain").singleResult();
        assertEquals("approve", task.getName());
        assertEquals("bulain", task.getAssignee());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action", "approve");
        taskService.complete(task.getId(), params);

        processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
        assertNull(processInstance);
    }

    @Test
    public void testOrderReject() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "johndoe");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("order", variables);
        String pid = processInstance.getId();

        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("johndoe").list();
        assertEquals(1, taskList.size());
        Task task = taskList.get(0);
        assertEquals("request", task.getName());

        taskService.claim(task.getId(), "johndoe");
        task = taskService.createTaskQuery().taskAssignee("johndoe").singleResult();
        assertEquals("request", task.getName());
        assertEquals("johndoe", task.getAssignee());

        taskService.complete(task.getId());

        taskList = taskService.createTaskQuery().taskCandidateUser("johndoe").list();
        assertEquals(0, taskList.size());

        taskList = taskService.createTaskQuery().taskCandidateUser("bulain").list();
        assertEquals(1, taskList.size());

        task = taskList.get(0);
        assertEquals("approve", task.getName());

        taskService.claim(task.getId(), "bulain");
        task = taskService.createTaskQuery().taskAssignee("bulain").singleResult();
        assertEquals("approve", task.getName());
        assertEquals("bulain", task.getAssignee());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action", "reject");
        taskService.complete(task.getId(), params);

        taskList = taskService.createTaskQuery().taskCandidateUser("johndoe").list();
        assertEquals(1, taskList.size());
        task = taskList.get(0);
        assertEquals("request", task.getName());

        taskService.claim(task.getId(), "johndoe");
        task = taskService.createTaskQuery().taskAssignee("johndoe").singleResult();
        assertEquals("request", task.getName());
        assertEquals("johndoe", task.getAssignee());

        taskService.complete(task.getId());

        taskList = taskService.createTaskQuery().taskCandidateUser("johndoe").list();
        assertEquals(0, taskList.size());

        taskList = taskService.createTaskQuery().taskCandidateUser("bulain").list();
        assertEquals(1, taskList.size());

        task = taskList.get(0);
        assertEquals("approve", task.getName());

        taskService.claim(task.getId(), "bulain");
        task = taskService.createTaskQuery().taskAssignee("bulain").singleResult();
        assertEquals("approve", task.getName());
        assertEquals("bulain", task.getAssignee());

        params.clear();
        params.put("action", "approve");
        taskService.complete(task.getId(), params);

        processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
        assertNull(processInstance);
    }
}
