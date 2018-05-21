package com.bulain.zk.model;

import java.util.Date;
import java.util.List;

public class CutmField {
    private Long id;

    private Long corpId;

    private Long cutmId;

    private String moduleCode;

    private String uiType;

    private String labelEn;

    private String labelCn;

    private String dataType;

    private Long min;

    private Long max;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

    private List<CutmFieldItem> items;
    private String value;

    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public List<CutmFieldItem> getItems() {
        return items;
    }
    public void setItems(List<CutmFieldItem> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCorpId() {
        return corpId;
    }

    public void setCorpId(Long corpId) {
        this.corpId = corpId;
    }

    public Long getCutmId() {
        return cutmId;
    }

    public void setCutmId(Long cutmId) {
        this.cutmId = cutmId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    public String getUiType() {
        return uiType;
    }

    public void setUiType(String uiType) {
        this.uiType = uiType == null ? null : uiType.trim();
    }

    public String getLabelEn() {
        return labelEn;
    }

    public void setLabelEn(String labelEn) {
        this.labelEn = labelEn == null ? null : labelEn.trim();
    }

    public String getLabelCn() {
        return labelCn;
    }

    public void setLabelCn(String labelCn) {
        this.labelCn = labelCn == null ? null : labelCn.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
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