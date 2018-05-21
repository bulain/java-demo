package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.pojo.Item;
import com.bulain.jbpm4order.test.JbpmTestCase;

@DataSet(file = "data/init_references.xml")
public class SpringTest extends JbpmTestCase {
    private String deploymentId;

    @Before
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/spring.jpdl.xml").deploy();
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSpring() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("name", "name");
        variables.put("lang", "lang");
        ProcessInstance processInstance = executionService.startProcessInstanceByKey("spring", variables);
        String pid = processInstance.getId();

        List<Item> listItem = (List<Item>) executionService.getVariable(pid, "answer");
        assertEquals(1, listItem.size());

        Execution execution = executionService.findExecutionById(pid);
        assertEquals(true, execution.isActive("state1"));

        processInstance = executionService.signalExecutionById(pid);
        assertEquals(true, processInstance.isEnded());
    }
}
