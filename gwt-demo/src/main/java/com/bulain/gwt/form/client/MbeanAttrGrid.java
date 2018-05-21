package com.bulain.gwt.form.client;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MbeanAttrGrid extends ListGridRecord {

    public MbeanAttrGrid() {
    }

    public MbeanAttrGrid(String name, String value, String type, String description) {
        setName(name);
        setValue(value);
        setType(type);
        setDescription(description);
    }

    public String getName() {
        return getAttributeAsString("name");
    }

    public void setName(String name) {
        setAttribute("name", name);
    }

    public String getValue() {
        return getAttributeAsString("value");
    }

    public void setValue(String value) {
        setAttribute("value", value);
    }

    public String getType() {
        return getAttributeAsString("type");
    }

    public void setType(String type) {
        setAttribute("type", type);
    }
    public String getDescription() {
        return getAttributeAsString("description");
    }
    public void setDescription(String description) {
        setAttribute("description", description);
    }

}