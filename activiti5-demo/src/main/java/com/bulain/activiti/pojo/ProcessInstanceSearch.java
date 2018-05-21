package com.bulain.activiti.pojo;

import com.bulain.common.page.Search;

public class ProcessInstanceSearch extends Search {
    private static final long serialVersionUID = 468505443014004296L;

    private String processInstanceBusinessKey;
    private String processDefinitionKey;
    private String status; // suspended,active

    public String getProcessInstanceBusinessKey() {
        return processInstanceBusinessKey;
    }
    public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
        this.processInstanceBusinessKey = processInstanceBusinessKey;
    }
    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }
    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
