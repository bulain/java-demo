package com.bulain.common.model;

import java.io.Serializable;

public class ReferenceBean implements Serializable {
    private static final long serialVersionUID = -8155557171385135453L;

    private String name;
    private String code;
    private String textEN;
    private String textCN;
    private String catagory;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTextEN() {
        return textEN;
    }
    public void setTextEN(String textEN) {
        this.textEN = textEN;
    }
    public String getTextCN() {
        return textCN;
    }
    public void setTextCN(String textCN) {
        this.textCN = textCN;
    }
    public String getCatagory() {
        return catagory;
    }
    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}