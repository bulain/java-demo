package com.bulain.activiti.pojo;

import com.bulain.activiti.model.Referance;

public class ReferanceView extends Referance {
    private static final long serialVersionUID = 2278494803282758337L;

    private String nameName;
    private String langName;
    private String categoryName;
    private String createdAtName;
    private String updatedAtName;

    public ReferanceView(final Referance model) {
        setId(model.getId());
        setName(model.getName());
        setCode(model.getCode());
        setText(model.getText());
        setLang(model.getLang());
        setCategory(model.getCategory());
        setCreatedBy(model.getCreatedBy());
        setCreatedAt(model.getCreatedAt());
        setUpdatedBy(model.getUpdatedBy());
        setUpdatedAt(model.getUpdatedAt());
    }

    public String getNameName() {
        return nameName;
    }
    public void setNameName(String nameName) {
        this.nameName = nameName;
    }
    public String getLangName() {
        return langName;
    }
    public void setLangName(String langName) {
        this.langName = langName;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCreatedAtName() {
        return createdAtName;
    }
    public void setCreatedAtName(String createdAtName) {
        this.createdAtName = createdAtName;
    }
    public String getUpdatedAtName() {
        return updatedAtName;
    }
    public void setUpdatedAtName(String updatedAtName) {
        this.updatedAtName = updatedAtName;
    }
}