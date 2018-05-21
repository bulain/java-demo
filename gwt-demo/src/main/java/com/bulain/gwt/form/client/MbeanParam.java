package com.bulain.gwt.form.client;

import java.io.Serializable;

public class MbeanParam implements Serializable {
    private static final long serialVersionUID = 2580174935960015216L;

    private String info;
    private String name;
    private String description;
    private String type;

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
