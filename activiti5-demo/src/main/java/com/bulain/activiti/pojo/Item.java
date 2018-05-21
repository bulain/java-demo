package com.bulain.activiti.pojo;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 3422066697070725260L;

    public static final Item DEFUALT_ITEM = new Item("", "");

    private String key;
    private String value;

    public Item() {
    }

    public Item(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
