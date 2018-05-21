package com.bulain.zk.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zkoss.zats.mimic.DefaultZatsEnvironment;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zats.mimic.ZatsEnvironment;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;

public class DemoVmTest {
    private static ZatsEnvironment env;
    private Locale defaultLocale;

    @BeforeClass
    public static void setUpClass() {
        env = new DefaultZatsEnvironment("src/main/webapp/WEB-INF");
        env.init("src/main/webapp");
    }

    @AfterClass
    public static void tearDownClass() {
        env.destroy();
    }

    @Before
    public void setUp() {
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.CHINESE);
    }

    @After
    public void tearDown() {
        Locale.setDefault(defaultLocale);
        env.cleanup();
    }

    @Test
    public void testList() {
        //load
        DesktopAgent desktop = env.newClient().connect("/demo/list.zul");

        Combobox cmbName = desktop.query("#cmbName").as(Combobox.class);
        Textbox txtCode = desktop.query("#txtCode").as(Textbox.class);
        Combobox cmbLang = desktop.query("#cmbLang").as(Combobox.class);
        Combobox cmbCatagory = desktop.query("#cmbCatagory").as(Combobox.class);
        assertEquals(7, cmbName.getItemCount());
        assertTrue(txtCode != null);
        assertEquals(39, cmbLang.getItemCount());
        assertEquals(2, cmbCatagory.getItemCount());

        Listbox lstReference = desktop.query("#lstReference").as(Listbox.class);
        Paging pageDemo = desktop.query("#pageDemo").as(Paging.class);
        assertEquals(10, lstReference.getItemCount());
        assertEquals(10, pageDemo.getPageSize());
        assertEquals(414, pageDemo.getTotalSize());

        //click search
        desktop.query("#txtCode").type("Yes");
        //desktop.query("#cmbName").type("Boolean");
        //desktop.queryAll("#cmbName > comboitem").get(1).as(SelectAgent.class).select();

        desktop.query("#btnSearch").click();

        lstReference = desktop.query("#lstReference").as(Listbox.class);
        pageDemo = desktop.query("#pageDemo").as(Paging.class);
        assertEquals(2, lstReference.getItemCount());
        assertEquals(10, pageDemo.getPageSize());
        assertEquals(2, pageDemo.getTotalSize());

    }

}
