package com.bulain.zk.validator.validators;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public abstract class FieldValidator extends AbstractValidator {
    //command level
    private String namespace;
    private String fieldName;
    //validator level
    private String messageKey;

    public Object getFieldValue(ValidationContext ctx) {
        Object base = ctx.getProperty().getBase();
        Map<String, Property> beanProps = ctx.getProperties(base);
        Property property = beanProps.get(fieldName);
        return property.getValue();
    }

    public String getMessageKey() {
        return messageKey != null ? messageKey : getDefaultMessageKey();
    }
    protected abstract String getDefaultMessageKey();

    public String getNamespace() {
        return namespace;
    }
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

}
