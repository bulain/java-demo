package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class JavaTest extends JbpmTestCase {
    private String deploymentId;

    @Before
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/java.jpdl.xml").deploy();
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);
    }

    @Test
    public void testJava2State1() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("action", "state1");
        ProcessInstance processInstance = executionService.startProcessInstanceByKey("java", variables);
        String pid = processInstance.getId();

        String answer = (String) executionService.getVariable(pid, "answer");
        assertEquals("to state1", answer);

        Execution execution = executionService.findExecutionById(pid);
        assertEquals(true, execution.isActive("state1"));

        processInstance = executionService.signalExecutionById(pid);
        assertEquals(true, processInstance.isEnded());
    }

    @Test
    public void testJava2State2() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("action", "state2");
        ProcessInstance processInstance = executionService.startProcessInstanceByKey("java", variables);
        String pid = processInstance.getId();

        String answer = (String) executionService.getVariable(pid, "answer");
        assertEquals("to state2", answer);

        Execution execution = executionService.findExecutionById(pid);
        assertEquals(true, execution.isActive("state2"));

        processInstance = executionService.signalExecutionById(pid);
        assertEquals(true, processInstance.isEnded());
    }
}
