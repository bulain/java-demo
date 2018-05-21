package com.bulain.activiti.pojo;

import java.util.Date;

import com.bulain.common.page.Search;

public class AccountSearch extends Search {
    private static final long serialVersionUID = 7919756014879888184L;

    private String iban;
    private String schemeName;
    private String identification;
    private String issuer;
    private String status;
    private String name;
    private String currency;
    private String type;
    private String statementCycle;
    private Date closingDate;
    private String purpose;
    private String restriction;
    private Date targetGoLiveDate;
    private Date targetClosingDate;
    private String urgencyFlag;

    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
    public String getSchemeName() {
        return schemeName;
    }
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }
    public String getIdentification() {
        return identification;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public String getIssuer() {
        return issuer;
    }
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getStatementCycle() {
        return statementCycle;
    }
    public void setStatementCycle(String statementCycle) {
        this.statementCycle = statementCycle;
    }
    public Date getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    public String getRestriction() {
        return restriction;
    }
    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }
    public Date getTargetGoLiveDate() {
        return targetGoLiveDate;
    }
    public void setTargetGoLiveDate(Date targetGoLiveDate) {
        this.targetGoLiveDate = targetGoLiveDate;
    }
    public Date getTargetClosingDate() {
        return targetClosingDate;
    }
    public void setTargetClosingDate(Date targetClosingDate) {
        this.targetClosingDate = targetClosingDate;
    }
    public String getUrgencyFlag() {
        return urgencyFlag;
    }
    public void setUrgencyFlag(String urgencyFlag) {
        this.urgencyFlag = urgencyFlag;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
