package com.bulain.common.pojo;

import com.bulain.common.model.Content;

public class ContentView extends Content {
    private static final long serialVersionUID = -3050453600570572725L;

    private String langName;
    private String createdAtName;
    private String updatedAtName;

    public ContentView(final Content content) {
        setId(content.getId());
        setFileName(content.getFileName());
        setContentType(content.getContentType());
        setRefId(content.getRefId());
        setRefName(content.getRefName());
        setCatagory(content.getCatagory());
        setLang(content.getLang());
        setCreatedBy(content.getCreatedBy());
        setCreatedAt(content.getCreatedAt());
        setUpdatedBy(content.getUpdatedBy());
        setUpdatedAt(content.getUpdatedAt());
    }

    public String getLangName() {
        return langName;
    }
    public void setLangName(String langName) {
        this.langName = langName;
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