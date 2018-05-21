package com.bulain.common.pojo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return StringUtils.equals(key, item.getKey());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return key + ":" + value;
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
