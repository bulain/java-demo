package com.bulain.common.pojo;

import com.bulain.common.page.Search;

public class ContentSearch extends Search {
    private static final long serialVersionUID = -1061768097751620L;

    private String fileName;
    private String contentType;
    private Long refId;
    private String refName;
    private String catagory;
    private String lang;

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public Long getRefId() {
        return refId;
    }
    public void setRefId(Long refId) {
        this.refId = refId;
    }
    public String getRefName() {
        return refName;
    }
    public void setRefName(String refName) {
        this.refName = refName;
    }
    public String getCatagory() {
        return catagory;
    }
    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
}
