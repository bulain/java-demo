package com.bulain.activiti.bpo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.beans.factory.annotation.Qualifier;

import com.bulain.activiti.model.Order;
import com.bulain.activiti.test.TestConst;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.ServiceTestCase;

@SeedDataSet(file = TestConst.TEST_DATA_INIT_COMMON_XML)
@DataSet(file = "test-data/init_orders.xml")
public class OrderBpoTest extends ServiceTestCase {
    @Autowired
    @Qualifier("orderBpoTx")
    private OrderBpo orderBpo;
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

        Order order = orderBpo.claim(task.getId(), "johndoe");
        order.setName("name_approve");
        order.setNote("note_approve");
        variables = new HashMap<String, Object>();
        orderBpo.complete(order, task.getId(), variables);

        taskList = taskService.createTaskQuery().taskCandidateUser("bulain").list();
        assertEquals(1, taskList.size());

        task = taskList.get(0);
        assertEquals("approve", task.getName());

        order = orderBpo.claim(task.getId(), "bulain");

        variables = new HashMap<String, Object>();
        variables.put("action", "approve");
        orderBpo.complete(order, task.getId(), variables);

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

        Order order = orderBpo.claim(task.getId(), "johndoe");
        order.setName("name_reject");
        order.setNote("note_reject");
        variables = new HashMap<String, Object>();
        orderBpo.complete(order, task.getId(), variables);

        taskList = taskService.createTaskQuery().taskCandidateUser("bulain").list();
        assertEquals(1, taskList.size());

        task = taskList.get(0);
        assertEquals("approve", task.getName());

        order = orderBpo.claim(task.getId(), "bulain");

        variables = new HashMap<String, Object>();
        variables.put("action", "reject");
        orderBpo.complete(order, task.getId(), variables);

        processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
        assertNotNull(processInstance);
    }

}
