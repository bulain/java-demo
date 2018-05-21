package com.bulain.jbpm4order.integration.crud;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jwebunit.junit.WebTestCase;

public class ReferenceCRUDTest extends WebTestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ReferenceCRUDTest.class);
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
        beginAt("/reference/new");

        assertTextFieldEquals("referenceBean.name", "");
        assertTextFieldEquals("referenceBean.code", "");
        assertTextFieldEquals("referenceBean.textEN", "");
        assertTextFieldEquals("referenceBean.textCN", "");
        assertTextFieldEquals("referenceBean.catagory", "");
        assertSubmitButtonPresent();
        assertLinkPresentWithExactText("Back");

        setTextField("referenceBean.name", "name");
        setTextField("referenceBean.code", "code");
        setTextField("referenceBean.textEN", "textEN");
        setTextField("referenceBean.textCN", "textCN");
        setTextField("referenceBean.catagory", "catagory");

        // post create
        submit();

        assertTablePresent("list");
        assertTableRowCountEquals("list", 3);
        DateFormat df = new SimpleDateFormat("M/dd/yy");
        String date = df.format(new Date());
        String[][] tables = new String[][]{
                {"Name", "Code", "Text", "Lang", "Catagory", "Updated By", "Updated At", "Action"},
                {"name", "code", "textEN", "English", "catagory", "createdBy", date, "Show |Edit |Destroy"},
                {"name", "code", "textCN", "Chinese", "catagory", "createdBy", date, "Show |Edit |Destroy"}};
        assertTableEquals("list", tables);

        // get show
        clickLinkWithExactText("Show");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        // get edit
        clickLinkWithExactText("Edit", 0);
        assertTextFieldEquals("reference.name", "name");
        assertTextFieldEquals("reference.code", "code");
        assertTextFieldEquals("reference.text", "textEN");
        assertTextFieldEquals("reference.lang", "English");
        assertTextFieldEquals("reference.catagory", "catagory");

        setTextField("reference.name", "name-updated");
        setTextField("reference.code", "code-updated");
        setTextField("reference.text", "textEN-updated");
        setTextField("reference.lang", "English-updated");
        setTextField("reference.catagory", "catagory-updated");

        // post update
        submit();
        assertTablePresent("list");
        tables = new String[][]{
                {"Name", "Code", "Text", "Lang", "Catagory", "Updated By", "Updated At", "Action"},
                {"name-updated", "code-updated", "textEN-updated", "English-updated", "catagory-updated", "updatedBy",
                        date, "Show |Edit |Destroy"},
                {"name", "code", "textCN", "Chinese", "catagory", "createdBy", date, "Show |Edit |Destroy"}};
        assertTableEquals("list", tables);

        // get edit
        clickLinkWithExactText("Edit", 0);
        assertTextFieldEquals("reference.name", "name-updated");
        assertTextFieldEquals("reference.code", "code-updated");
        assertTextFieldEquals("reference.text", "textEN-updated");
        assertTextFieldEquals("reference.lang", "English-updated");
        assertTextFieldEquals("reference.catagory", "catagory-updated");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        setTextField("search.name", "name-updated");
        setTextField("search.code", "code-updated");

        // post list
        submit();
        assertTablePresent("list");

        // post destroy
        clickLinkWithExactText("Destroy");
        assertTablePresent("list");
        tables = new String[][]{{"Name", "Code", "Text", "Lang", "Catagory", "Updated By", "Updated At", "Action"}};
        assertTableEquals("list", tables);

        // post destroy
        setTextField("search.name", "");
        setTextField("search.code", "");
        submit();
        assertTablePresent("list");
        clickLinkWithExactText("Destroy");
        assertTablePresent("list");
        tables = new String[][]{{"Name", "Code", "Text", "Lang", "Catagory", "Updated By", "Updated At", "Action"}};
        assertTableEquals("list", tables);
    }
}
