package com.bulain.jbpm4order.integration.crud;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jwebunit.junit.WebTestCase;

public class LoginCRUDTest extends WebTestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(LoginCRUDTest.class);
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
        beginAt("/login/new");

        assertTextFieldEquals("login.loginName", "");
        assertTextFieldEquals("login.email", "");
        assertTextFieldEquals("login.hashedPassword", "");
        assertTextFieldEquals("login.enabled", "");
        assertSubmitButtonPresent();
        assertLinkPresentWithExactText("Back");

        setTextField("login.loginName", "loginName");
        setTextField("login.email", "email@email.com");
        setTextField("login.hashedPassword", "hashedPassword");
        setTextField("login.enabled", "enabled");

        // post create
        submit();

        assertTablePresent("list");
        assertTableRowCountEquals("list", 2);
        DateFormat df = new SimpleDateFormat("M/dd/yy");
        String date = df.format(new Date());
        String[][] tables = new String[][]{
                {"Login Name", "Email", "Enabled", "Created By", "Created At", "Updated By", "Updated At", "Action"},
                {"loginName", "email@email.com", "enabled", "createdBy", date, "createdBy", date, "Show |Edit |Destroy"},};
        assertTableEquals("list", tables);

        // get show
        clickLinkWithExactText("Show");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        // get edit
        clickLinkWithExactText("Edit");
        assertTextFieldEquals("login.loginName", "loginName");
        assertTextFieldEquals("login.email", "email@email.com");
        assertTextFieldEquals("login.hashedPassword", "hashedPassword");
        assertTextFieldEquals("login.enabled", "enabled");

        setTextField("login.loginName", "loginName-updated");
        setTextField("login.email", "email-updated@email.com");
        setTextField("login.hashedPassword", "hashedPassword-updated");
        setTextField("login.enabled", "enabled-updated");

        // post update
        submit();
        assertTablePresent("list");
        tables = new String[][]{
                {"Login Name", "Email", "Enabled", "Created By", "Created At", "Updated By", "Updated At", "Action"},
                {"loginName-updated", "email-updated@email.com", "enabled-updated", "createdBy", date, "updatedBy",
                        date, "Show |Edit |Destroy"},};
        assertTableEquals("list", tables);

        // get edit
        clickLinkWithExactText("Edit");
        assertTextFieldEquals("login.loginName", "loginName-updated");
        assertTextFieldEquals("login.email", "email-updated@email.com");
        assertTextFieldEquals("login.hashedPassword", "hashedPassword-updated");
        assertTextFieldEquals("login.enabled", "enabled-updated");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        setTextField("search.loginName", "loginName-updated");

        // post list
        submit();
        assertTablePresent("list");

        // post destroy
        clickLinkWithExactText("Destroy");
        assertTablePresent("list");
        tables = new String[][]{{"Login Name", "Email", "Enabled", "Created By", "Created At", "Updated By",
                "Updated At", "Action"}};
        assertTableEquals("list", tables);

    }
}
