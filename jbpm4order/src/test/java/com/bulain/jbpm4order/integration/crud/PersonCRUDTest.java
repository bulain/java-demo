package com.bulain.jbpm4order.integration.crud;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jwebunit.junit.WebTestCase;

public class PersonCRUDTest extends WebTestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(PersonCRUDTest.class);
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
        beginAt("/person/new");

        assertTextFieldEquals("person.firstName", "");
        assertTextFieldEquals("person.lastName", "");
        assertSubmitButtonPresent();
        assertLinkPresentWithExactText("Back");

        setTextField("person.firstName", "firstName");
        setTextField("person.lastName", "lastName");

        // post create
        submit();

        assertTablePresent("list");
        assertTableRowCountEquals("list", 2);
        DateFormat df = new SimpleDateFormat("M/dd/yy");
        String date = df.format(new Date());
        String[][] tables = new String[][]{
                {"First Name", "Last Name", "Created By", "Created At", "Updated By", "Updated At", "Action"},
                {"firstName", "lastName", "createdBy", date, "createdBy", date, "Show |Edit |Destroy"},};
        assertTableEquals("list", tables);

        // get show
        clickLinkWithExactText("Show");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        // get edit
        clickLinkWithExactText("Edit");
        assertTextFieldEquals("person.firstName", "firstName");
        assertTextFieldEquals("person.lastName", "lastName");

        setTextField("person.firstName", "firstName-updated");
        setTextField("person.lastName", "lastName-updated");

        // post update
        submit();
        assertTablePresent("list");
        tables = new String[][]{
                {"First Name", "Last Name", "Created By", "Created At", "Updated By", "Updated At", "Action"},
                {"firstName-updated", "lastName-updated", "createdBy", date, "updatedBy", date, "Show |Edit |Destroy"},};
        assertTableEquals("list", tables);

        // get edit
        clickLinkWithExactText("Edit");
        assertTextFieldEquals("person.firstName", "firstName-updated");
        assertTextFieldEquals("person.lastName", "lastName-updated");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        setTextField("search.firstName", "firstName-updated");

        // post list
        submit();
        assertTablePresent("list");

        // post destroy
        clickLinkWithExactText("Destroy");
        assertTablePresent("list");
        tables = new String[][]{{"First Name", "Last Name", "Created By", "Created At", "Updated By", "Updated At",
                "Action"}};
        assertTableEquals("list", tables);

    }
}
