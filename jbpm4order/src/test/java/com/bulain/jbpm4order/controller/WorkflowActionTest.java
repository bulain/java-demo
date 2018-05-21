package com.bulain.jbpm4order.controller;

import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class WorkflowActionTest extends ActionTestCase {
    @Before
    public void setUp() throws Exception {
        super.setUp();
        super.setUpAction("admin", "admin");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDownAction();
        super.tearDown();
    }

    @Test
    public void testWorkflow() throws Exception {
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/workflow/deploy");
        WorkflowAction workflowAction = (WorkflowAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/workflow/list");
        workflowAction = (WorkflowAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        List<ProcessDefinition> listProcessDefinition = workflowAction.getListProcessDefinition();
        List<ProcessInstance> listProcessInstance = workflowAction.getListProcessInstance();
        List<Task> listTask = workflowAction.getListPersonTask();

        assertEquals(1, listProcessDefinition.size());
        assertEquals(0, listProcessInstance.size());
        assertEquals(0, listTask.size());

        String processDefinitionId = listProcessDefinition.get(0).getId();

        initServletMockObjects();
        request.setParameter("processDefinitionId", processDefinitionId);
        proxy = getActionProxy("/workflow/start");
        workflowAction = (WorkflowAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/workflow/list");
        workflowAction = (WorkflowAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        assertEquals(1, workflowAction.getListProcessInstance().size());
        String executionId = workflowAction.getListProcessInstance().get(0).getId();

        initServletMockObjects();
        request.setParameter("executionId", executionId);
        proxy = getActionProxy("/workflow/view");
        workflowAction = (WorkflowAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/workflow/list");
        workflowAction = (WorkflowAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        listProcessDefinition = workflowAction.getListProcessDefinition();
        listProcessInstance = workflowAction.getListProcessInstance();
        listTask = workflowAction.getListPersonTask();

        assertEquals(1, listProcessDefinition.size());
        assertEquals(1, listProcessInstance.size());
        assertEquals(1, listTask.size());

        String deploymentId = listProcessDefinition.get(0).getDeploymentId();

        initServletMockObjects();
        request.setParameter("deploymentId", deploymentId);
        proxy = getActionProxy("/workflow/destroy");
        workflowAction = (WorkflowAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/workflow/list");
        workflowAction = (WorkflowAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        listProcessDefinition = workflowAction.getListProcessDefinition();
        listProcessInstance = workflowAction.getListProcessInstance();
        listTask = workflowAction.getListPersonTask();

        assertEquals(0, listProcessDefinition.size());
        assertEquals(0, listProcessInstance.size());
        assertEquals(0, listTask.size());
    }
}
