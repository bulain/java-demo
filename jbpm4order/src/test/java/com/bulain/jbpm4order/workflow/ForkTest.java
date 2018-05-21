package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class ForkTest extends JbpmTestCase {
    String deploymentId;

    @Before
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/fork.jpdl.xml").deploy();
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);
    }

    @Test
    public void testFork() {
        ProcessInstance processInstance = executionService.startProcessInstanceByKey("fork");
        String pid = processInstance.getId();

        Execution state1 = processInstance.findActiveExecutionIn("state1");
        assertNotNull(state1);
        processInstance = executionService.signalExecutionById(pid);

        String state2 = processInstance.findActiveExecutionIn("state2").getId();
        String state3 = processInstance.findActiveExecutionIn("state3").getId();
        assertNotNull(state2);
        assertNotNull(state3);
        processInstance = executionService.signalExecutionById(state2);
        processInstance = executionService.signalExecutionById(state3);

        Execution state4 = processInstance.findActiveExecutionIn("state4");
        assertNotNull(state4);
        processInstance = executionService.signalExecutionById(pid);

        assertEquals(true, processInstance.isEnded());
    }
}
