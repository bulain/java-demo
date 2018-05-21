package com.bulain.activiti.pojo;

import com.bulain.activiti.model.Account;

public class AccountView extends Account {
    private static final long serialVersionUID = -4148408606464209353L;
    private String statusName;
    private String currencyName;
    private String typeName;
    private String statementCycleName;
    private String urgencyFlagName;

    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public String getCurrencyName() {
        return currencyName;
    }
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getStatementCycleName() {
        return statementCycleName;
    }
    public void setStatementCycleName(String statementCycleName) {
        this.statementCycleName = statementCycleName;
    }
    public String getUrgencyFlagName() {
        return urgencyFlagName;
    }
    public void setUrgencyFlagName(String urgencyFlagName) {
        this.urgencyFlagName = urgencyFlagName;
    }
}
