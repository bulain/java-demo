package com.bulain.jbpm4order.integration.workflow;

import net.sourceforge.jwebunit.junit.WebTestCase;

public class WorkflowTest extends WebTestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(WorkflowTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        setBaseUrl("http://localhost:8080/jbpm4order");

        // list
        beginAt("/workflow/list");

        assertLinkPresentWithExactText("Deploy");

        // deploy
        clickLinkWithExactText("Deploy");
        assertLinkPresentWithExactText("Deploy");
        assertLinkPresentWithExactText("Start");
    }

    protected void tearDown() throws Exception {
        // Destroy
        clickLinkWithExactText("Destroy");
        assertLinkNotPresentWithExactText("Start");
        assertLinkNotPresentWithExactText("Destroy");

        super.tearDown();
    }

    public void testReject() {
        // start
        clickLinkWithExactText("Start");
        assertLinkPresentWithExactText("Deploy");
        assertLinkPresentWithExactText("Start");
        assertLinkPresentWithExactText("Active");

        // request
        clickLinkWithExactText("Active");
        assertButtonPresentWithText("Request");
        assertTextFieldEquals("order.name", "");
        assertTextFieldEquals("order.note", "");

        setTextField("order.name", "testname");
        setTextField("order.note", "testnote");

        // submit request
        clickButtonWithText("Request");
        assertLinkPresentWithExactText("Deploy");
        assertLinkPresentWithExactText("Start");
        assertLinkPresentWithExactText("Active");

        // approval
        clickLinkWithExactText("Active");
        assertButtonPresentWithText("Approve");
        assertButtonPresentWithText("Reject");

        // approve
        clickButtonWithText("Reject");
        assertLinkPresentWithExactText("Deploy");
        assertLinkPresentWithExactText("Start");
        assertLinkPresentWithExactText("Active");
    }

    public void testApprove() {
        // start
        clickLinkWithExactText("Start");
        assertLinkPresentWithExactText("Deploy");
        assertLinkPresentWithExactText("Start");
        assertLinkPresentWithExactText("Active");

        // request
        clickLinkWithExactText("Active");
        assertButtonPresentWithText("Request");
        assertTextFieldEquals("order.name", "");
        assertTextFieldEquals("order.note", "");

        setTextField("order.name", "testname");
        setTextField("order.note", "testnote");

        // submit request
        clickButtonWithText("Request");
        assertLinkPresentWithExactText("Deploy");
        assertLinkPresentWithExactText("Start");
        assertLinkPresentWithExactText("Active");

        // approval
        clickLinkWithExactText("Active");
        assertButtonPresentWithText("Approve");
        assertButtonPresentWithText("Reject");

        // approve
        clickButtonWithText("Approve");
        assertLinkPresentWithExactText("Deploy");
        assertLinkPresentWithExactText("Start");
        assertLinkNotPresentWithExactText("Active");
    }
}
