package com.bulain.jbpm4order.integration.crud;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jwebunit.junit.WebTestCase;

public class OrderCRUDTest extends WebTestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(OrderCRUDTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        setBaseUrl("http://localhost:8080/jbpm4order");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCrud() {
        // get new
        beginAt("/order/new");

        assertTextFieldEquals("order.name", "");
        assertTextFieldEquals("order.note", "");
        assertSubmitButtonPresent();
        assertLinkPresentWithExactText("Back");

        setTextField("order.name", "testname");
        setTextField("order.note", "testnote");

        // post create
        submit();

        assertTablePresent("list");
        assertTableRowCountEquals("list", 2);
        DateFormat df = new SimpleDateFormat("M/dd/yy");
        String date = df.format(new Date());
        String[][] tables = new String[][]{
                {"Name", "Note", "Created By", "Created At", "Updated By", "Updated At", "Action"},
                {"testname", "testnote", "createdBy", date, "createdBy", date, "Show |Edit |Destroy"},};
        assertTableEquals("list", tables);

        // get show
        clickLinkWithExactText("Show");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        // get edit
        clickLinkWithExactText("Edit");
        assertTextFieldEquals("order.name", "testname");
        assertTextFieldEquals("order.note", "testnote");

        setTextField("order.name", "testname1");
        setTextField("order.note", "testnote1");

        // post update
        submit();
        assertTablePresent("list");
        tables = new String[][]{{"Name", "Note", "Created By", "Created At", "Updated By", "Updated At", "Action"},
                {"testname1", "testnote1", "createdBy", date, "updatedBy", date, "Show |Edit |Destroy"},};
        assertTableEquals("list", tables);

        // get edit
        clickLinkWithExactText("Edit");
        assertTextFieldEquals("order.name", "testname1");
        assertTextFieldEquals("order.note", "testnote1");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        setTextField("search.name", "testname1");

        // post list
        submit();
        assertTablePresent("list");

        // post destroy
        clickLinkWithExactText("Destroy");
        assertTablePresent("list");
        tables = new String[][]{{"Name", "Note", "Created By", "Created At", "Updated By", "Updated At", "Action"}};
        assertTableEquals("list", tables);

    }
}
