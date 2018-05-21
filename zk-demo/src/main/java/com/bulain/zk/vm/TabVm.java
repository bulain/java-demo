package com.bulain.zk.vm;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

@VariableResolver(DelegatingVariableResolver.class)
public class TabVm {
    @Wire("#menuTabs")
    private Tabs tabs;
    @Wire("#menuTabpanels")
    private Tabpanels tabpanels;

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }

    @Init
    public void init() {
    }

    private String[][] tables = new String[][]{
        {"demo", "Demo", "/demo/list.zul"},
        {"login", "Login", "/login/list.zul"},
        {"tabnest", "Tabnest", "/tabnest/list.zul"},
        {"reference", "Reference", "/reference/list.zul"},
        {"person", "Person", "/person/list.zul"},
        {"tabdemo", "Tabdemo", "/tabdemo/list.zul"},
        {"upload", "Upload", "/upload/list.zul"},
        {"cutm", "Cutm", "/cutm/list.zul"},
    };
    private Map<String, String[]> getTabMapping(){
        Map<String, String[]> mapping = new HashMap<String, String[]>();
        for(String[] line : tables){
            mapping.put(line[0], line);
        }
        return mapping;
    }
    
    
    @GlobalCommand
    public void globalOpenTab(@BindingParam("tabId") String tabId){
        Map<String, String[]> tabMapping = getTabMapping();
        String[] line = tabMapping.get(tabId);
        showTab(line[2], line[0], line[1]);
    }
    
    private void showTab(String zulPath, String id, String label) {
        Tab checkTab = null;
        try {
            checkTab = (Tab) tabs.getFellow("tab_" + id);
            checkTab.setSelected(true);
        } catch (ComponentNotFoundException ex) {
        }

        if (checkTab == null) {
            Tab tab = new Tab();
            tab.setId("tab_" + id);
            tab.setLabel(label);
            tab.setClosable(true);
            tab.setParent(tabs);

            Tabpanel tabpanel = new Tabpanel();
            tabpanel.setVflex("1");
            tabpanel.setHflex("1");
            tabpanel.setStyle("overflow:auto;");
            tabpanel.setParent(tabpanels);

            Executions.createComponents(zulPath, tabpanel, null);
            tab.setSelected(true);
        }
    }

}
