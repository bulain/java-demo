package com.bulain.activiti.controller;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.activiti.model.Order;
import com.bulain.activiti.service.OrderService;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class OrderActionWorkflowTest extends ActionTestCase {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private OrderService orderService;

    private String processDefinitionId;
    private String deploymentId;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        deploymentId = repositoryService.createDeployment().addClasspathResource("diagrams/order.bpmn20.xml").deploy()
                .getId();
        processDefinitionId = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId)
                .singleResult().getId();
        orderService = (OrderService) applicationContext.getBean("orderService");
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeployment(deploymentId, true);
        super.tearDown();
    }

    protected String start() throws Exception {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "bulain");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, variables);
        return processInstance.getId();

    }

    protected String task(String executionId) throws Exception {
        return taskService.createTaskQuery().executionId(executionId).singleResult().getId();
    }

    @Test
    public void testWorkflowApprove() throws Exception {
        String executionId = start();
        String taskId = task(executionId);

        initServletMockObjects();
        request.addParameter("taskId", taskId);
        ActionProxy proxy = getActionProxy("/order/request");
        OrderAction orderAction = (OrderAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        String wfId = orderAction.getOrder().getWfId();

        initServletMockObjects();
        request.setParameter("order.name", "name");
        request.setParameter("order.note", "note");
        request.setParameter("order.wfId", wfId);
        request.setParameter("taskId", taskId);
        request.setParameter("submit", "Request");
        proxy = getActionProxy("/order/submitRequest");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        taskId = task(executionId);

        initServletMockObjects();
        request.addParameter("taskId", taskId);
        proxy = getActionProxy("/order/approve");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Long orderId = orderAction.getOrder().getId();

        initServletMockObjects();
        request.setParameter("order.id", Long.toString(orderId));
        request.setParameter("taskId", taskId);
        request.setParameter("submit", "Approve");
        proxy = getActionProxy("/order/submitApprove");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Order order = orderService.getByWfId(executionId);
        orderService.delete(order.getId());
    }

    @Test
    public void testWorkflowReject() throws Exception {
        String executionId = start();
        String taskId = task(executionId);

        initServletMockObjects();
        request.addParameter("taskId", taskId);
        ActionProxy proxy = getActionProxy("/order/request");
        OrderAction orderAction = (OrderAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        String wfId = orderAction.getOrder().getWfId();

        initServletMockObjects();
        request.setParameter("order.name", "name");
        request.setParameter("order.note", "note");
        request.setParameter("order.wfId", wfId);
        request.setParameter("taskId", taskId);
        request.setParameter("submit", "Request");
        proxy = getActionProxy("/order/submitRequest");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        taskId = task(executionId);

        initServletMockObjects();
        request.addParameter("taskId", taskId);
        proxy = getActionProxy("/order/approve");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Long orderId = orderAction.getOrder().getId();

        initServletMockObjects();
        request.setParameter("order.id", Long.toString(orderId));
        request.setParameter("taskId", taskId);
        request.setParameter("submit", "Reject");
        proxy = getActionProxy("/order/submitApprove");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Order order = orderService.getByWfId(executionId);
        orderService.delete(order.getId());
    }
}
