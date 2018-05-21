package com.bulain.activiti.controller;

import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class ActivitiActionTest extends ActionTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testWorkflow() throws Exception {
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/activiti/deploy");
        ActivitiAction activitiAction = (ActivitiAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/activiti/list");
        activitiAction = (ActivitiAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        List<ProcessDefinition> listProcessDefinition = activitiAction.getListProcessDefinition();
        List<ProcessInstance> listProcessInstance = activitiAction.getListProcessInstance();
        List<Task> listTask = activitiAction.getListPersonTask();

        assertEquals(1, listProcessDefinition.size());
        assertEquals(0, listProcessInstance.size());
        assertEquals(0, listTask.size());

        String processDefinitionId = listProcessDefinition.get(0).getId();

        initServletMockObjects();
        request.setParameter("processDefinitionId", processDefinitionId);
        proxy = getActionProxy("/activiti/start");
        activitiAction = (ActivitiAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/activiti/list");
        activitiAction = (ActivitiAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        assertEquals(1, activitiAction.getListProcessInstance().size());
        String executionId = activitiAction.getListProcessInstance().get(0).getId();

        initServletMockObjects();
        request.setParameter("executionId", executionId);
        proxy = getActionProxy("/activiti/view");
        activitiAction = (ActivitiAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/activiti/list");
        activitiAction = (ActivitiAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        listProcessDefinition = activitiAction.getListProcessDefinition();
        listProcessInstance = activitiAction.getListProcessInstance();
        listTask = activitiAction.getListPersonTask();

        assertEquals(1, listProcessDefinition.size());
        assertEquals(1, listProcessInstance.size());
        assertEquals(0, listTask.size());

        String deploymentId = listProcessDefinition.get(0).getDeploymentId();

        initServletMockObjects();
        request.setParameter("deploymentId", deploymentId);
        proxy = getActionProxy("/activiti/destroy");
        activitiAction = (ActivitiAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/activiti/list");
        activitiAction = (ActivitiAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        listProcessDefinition = activitiAction.getListProcessDefinition();
        listProcessInstance = activitiAction.getListProcessInstance();
        listTask = activitiAction.getListPersonTask();

        assertEquals(0, listProcessDefinition.size());
        assertEquals(0, listProcessInstance.size());
        assertEquals(0, listTask.size());
    }
}
