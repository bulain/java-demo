package com.bulain.gwt.form.client;

import java.io.Serializable;

public class MbeanInfo implements Serializable {
    private static final long serialVersionUID = -3375874914271306783L;
    
    private String objectName;
    private String className;
    private String description;
    private MbeanAttr[] attrs;
    private MbeanOper[] opers;

    public String getObjectName() {
        return objectName;
    }
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public MbeanAttr[] getAttrs() {
        return attrs;
    }
    public void setAttrs(MbeanAttr[] attrs) {
        this.attrs = attrs;
    }
    public MbeanOper[] getOpers() {
        return opers;
    }
    public void setOpers(MbeanOper[] opers) {
        this.opers = opers;
    }

}
