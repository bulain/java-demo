package com.bulain.activiti.pojo;

import com.bulain.common.page.Search;

public class ProcessDefinitionSearch extends Search {
    private static final long serialVersionUID = 5662432196989404632L;

    private String processDefinitionCategory;
    private String processDefinitionName;
    private String processDefinitionKey;
    private String processDefinitionResourceName;
    private String status; // suspended,active

    public String getProcessDefinitionCategory() {
        return processDefinitionCategory;
    }
    public void setProcessDefinitionCategory(String processDefinitionCategory) {
        this.processDefinitionCategory = processDefinitionCategory;
    }
    public String getProcessDefinitionName() {
        return processDefinitionName;
    }
    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }
    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }
    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }
    public String getProcessDefinitionResourceName() {
        return processDefinitionResourceName;
    }
    public void setProcessDefinitionResourceName(String processDefinitionResourceName) {
        this.processDefinitionResourceName = processDefinitionResourceName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
