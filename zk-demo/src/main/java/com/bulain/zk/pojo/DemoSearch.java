package com.bulain.zk.pojo;

import com.bulain.common.pojo.Item;

public class DemoSearch extends PagedSearch {
    private static final long serialVersionUID = 2719986067154051669L;

    private Item nameItem = new Item();
    private String code;
    private Item langItem = new Item();
    private Item catagoryItem = new Item();

    public Item getNameItem() {
        return nameItem;
    }
    public void setNameItem(Item nameItem) {
        this.nameItem = nameItem;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Item getLangItem() {
        return langItem;
    }
    public void setLangItem(Item langItem) {
        this.langItem = langItem;
    }
    public Item getCatagoryItem() {
        return catagoryItem;
    }
    public void setCatagoryItem(Item catagoryItem) {
        this.catagoryItem = catagoryItem;
    }
}
