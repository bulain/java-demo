package com.bulain.activiti.bpo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bulain.activiti.pojo.TaskSearch;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;

public class ActivitiBpoTest extends ServiceTestCase {
    @Autowired
    @Qualifier("activitiBpoTx")
    private ActivitiBpo activitiBpo;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    private static String deploymentId;
    private static ActivitiBpoTest test;

    @Before
    public void setUp() throws Exception {
        if (deploymentId == null) {
            test = this;
            setUpActiviti();
        }
    }

    private void setUpActiviti() {
        deploymentId = repositoryService.createDeployment().addClasspathResource("diagrams/order.bpmn20.xml").deploy()
                .getId();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "bulain");
        for (int i = 0; i < 10; i++) {
            runtimeService.startProcessInstanceByKey("order", variables);
        }

        List<Task> listTask = taskService.createTaskQuery().taskCandidateUser("bulain").list();
        for (int i = 0; i < 5; i++) {
            Task task = listTask.get(i);
            taskService.claim(task.getId(), "bulain");
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        test.tearDownActiviti();
    }

    private void tearDownActiviti() {
        repositoryService.deleteDeployment(deploymentId, true);
    }

    @Test
    public void testPageTask4TaskAssignee() {
        TaskSearch search = new TaskSearch();
        search.setTaskName("request");
        search.setTaskDescription("");
        search.setTaskAssignee("bulain");
        search.setTaskDefinitionKey("usertask1");
        search.setProcessInstanceBusinessKey("");
        search.setProcessDefinitionKey("order");
        search.setProcessDefinitionName("order");
        Page page = new Page();
        page.setPageSize(3);
        page.setPage(2);

        List<Task> listTask = activitiBpo.pageTask(search, page);
        assertEquals(2, listTask.size());
    }

    @Test
    public void testPageTask4TaskCandidateUser() {
        TaskSearch search = new TaskSearch();
        search.setTaskName("request");
        search.setTaskDescription("");
        search.setTaskCandidateUser("bulain");
        search.setTaskDefinitionKey("usertask1");
        search.setProcessInstanceBusinessKey("");
        search.setProcessDefinitionKey("order");
        search.setProcessDefinitionName("order");
        Page page = new Page();
        page.setPageSize(3);
        page.setPage(2);

        List<Task> listTask = activitiBpo.pageTask(search, page);
        assertEquals(2, listTask.size());
    }

}
