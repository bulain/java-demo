package com.bulain.activiti.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.bulain.common.model.Trackable;

public class Account implements Trackable, Serializable {
    private static final long serialVersionUID = 5485486377260193480L;

    private Long id;

    private String iban;

    private String schemeName;

    private String identification;

    private String issuer;

    private String status;

    private String name;

    private String currency;

    private String type;

    private BigDecimal monthlyPaymentValue;

    private BigDecimal monthlyReceivedValue;

    private Integer monthlyTransactionNumber;

    private BigDecimal averageBalance;

    private BigDecimal floorNotificationAmount;

    private BigDecimal ceilingNotificationAmount;

    private String statementCycle;

    private Date closingDate;

    private String purpose;

    private String restriction;

    private Date targetGoLiveDate;

    private Date targetClosingDate;

    private String urgencyFlag;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban == null ? null : iban.trim();
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName == null ? null : schemeName.trim();
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification == null ? null : identification.trim();
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getMonthlyPaymentValue() {
        return monthlyPaymentValue;
    }

    public void setMonthlyPaymentValue(BigDecimal monthlyPaymentValue) {
        this.monthlyPaymentValue = monthlyPaymentValue;
    }

    public BigDecimal getMonthlyReceivedValue() {
        return monthlyReceivedValue;
    }

    public void setMonthlyReceivedValue(BigDecimal monthlyReceivedValue) {
        this.monthlyReceivedValue = monthlyReceivedValue;
    }

    public Integer getMonthlyTransactionNumber() {
        return monthlyTransactionNumber;
    }

    public void setMonthlyTransactionNumber(Integer monthlyTransactionNumber) {
        this.monthlyTransactionNumber = monthlyTransactionNumber;
    }

    public BigDecimal getAverageBalance() {
        return averageBalance;
    }

    public void setAverageBalance(BigDecimal averageBalance) {
        this.averageBalance = averageBalance;
    }

    public BigDecimal getFloorNotificationAmount() {
        return floorNotificationAmount;
    }

    public void setFloorNotificationAmount(BigDecimal floorNotificationAmount) {
        this.floorNotificationAmount = floorNotificationAmount;
    }

    public BigDecimal getCeilingNotificationAmount() {
        return ceilingNotificationAmount;
    }

    public void setCeilingNotificationAmount(BigDecimal ceilingNotificationAmount) {
        this.ceilingNotificationAmount = ceilingNotificationAmount;
    }

    public String getStatementCycle() {
        return statementCycle;
    }

    public void setStatementCycle(String statementCycle) {
        this.statementCycle = statementCycle == null ? null : statementCycle.trim();
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
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction == null ? null : restriction.trim();
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
        this.urgencyFlag = urgencyFlag == null ? null : urgencyFlag.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}