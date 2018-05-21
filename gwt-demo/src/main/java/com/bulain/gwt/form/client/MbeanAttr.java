package com.bulain.gwt.form.client;

import java.io.Serializable;

public class MbeanAttr implements Serializable {
    private static final long serialVersionUID = 7398963542896519500L;

    private String name;
    private String value;
    private String type;
    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
