package com.bulain.gwt.form.client;

import java.io.Serializable;

public class MbeanOper implements Serializable{
    private static final long serialVersionUID = 8813802484733645105L;
    
    private String info;
    private String name;
    private String description;
    private String type;

    private MbeanParam[] params;

    public MbeanParam[] getParams() {
        return params;
    }
    public void setParams(MbeanParam[] params) {
        this.params = params;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
