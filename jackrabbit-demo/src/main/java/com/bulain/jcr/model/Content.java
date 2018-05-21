package com.bulain.jcr.model;

import java.io.Serializable;

public class Content implements Serializable{
    private static final long serialVersionUID = -3935902749147125432L;
    
    public static final String PROP_FILE_NAME = "FILE_NAME";
    public static final String PROP_CONTENT_TYPE = "CONTENT_TYPE";
    public static final String PROP_CATAGORY = "CATAGORY";
    public static final String PROP_LANG = "LANG";
    public static final String PROP_BYTES = "BYTES";
    
    private String id;
    private String fileName;
    private String contentType;
    private String catagory;
    private String lang;
    private byte[] bytes;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
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
    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
