package com.bulain.activiti.pojo;

import com.bulain.activiti.model.Group;

public class GroupView extends Group {
    private static final long serialVersionUID = -3015071680531609865L;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
