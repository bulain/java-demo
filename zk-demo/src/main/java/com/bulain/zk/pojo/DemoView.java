package com.bulain.zk.pojo;

import java.io.Serializable;

import com.bulain.common.model.Reference;
import com.bulain.common.pojo.Item;

public class DemoView extends Reference implements Serializable {
    private static final long serialVersionUID = 2173207144498374452L;

    private Item nameItem = new Item();
    private Item langItem = new Item();
    private Item catagoryItem = new Item();

    public DemoView() {
    }

    public DemoView(final Reference model) {
        setId(model.getId());
        setName(model.getName());
        setCode(model.getCode());
        setText(model.getText());
        setLang(model.getLang());
        setCatagory(model.getCatagory());
        setCreatedBy(model.getCreatedBy());
        setCreatedAt(model.getCreatedAt());
        setUpdatedBy(model.getUpdatedBy());
        setUpdatedAt(model.getUpdatedAt());
    }

    public Item getNameItem() {
        return nameItem;
    }
    public void setNameItem(Item nameItem) {
        this.nameItem = nameItem;
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
