package com.bulain.gwt.form.client;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MbeanOperGrid extends ListGridRecord {
    public MbeanOperGrid() {
    }
    public MbeanOperGrid(String info, String name, String description, String type) {
        setInfo(info);
        setName(name);
        setDescription(description);
        setType(type);
    }

    public String getInfo() {
        return getAttributeAsString("info");
    }
    public void setInfo(String info) {
        setAttribute("info", info);
    }
    public String getName() {
        return getAttributeAsString("name");
    }
    public void setName(String name) {
        setAttribute("name", name);
    }
    public String getDescription() {
        return getAttributeAsString("description");
    }
    public void setDescription(String description) {
        setAttribute("description", description);
    }
    public String getType() {
        return getAttributeAsString("type");
    }
    public void setType(String type) {
        setAttribute("type", type);
    }

}
