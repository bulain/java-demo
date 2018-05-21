package com.bulain.zk.validator.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

public class StringLengthFieldValidator extends FieldValidator {

    private boolean trim = true;
    private int maxLength = -1;
    private int minLength = -1;

    public void validate(ValidationContext ctx) {
        String val = (String) getFieldValue(ctx);

        if (val == null || val.length() <= 0) {
            return;
        }
        if (trim) {
            val = val.trim();
            if (val.length() <= 0) {
                return;
            }
        }

        if ((minLength > -1) && (val.length() < minLength)) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key), maxLength, minLength});
            addInvalidMessage(ctx, getFieldName(), message);
        } else if ((maxLength > -1) && (val.length() > maxLength)) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key), maxLength, minLength});
            addInvalidMessage(ctx, getFieldName(), message);
        }
    }
    
    protected String getDefaultMessageKey() {
        return "validate.stringlength";
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    public int getMaxLength() {
        return maxLength;
    }
    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }
    public int getMinLength() {
        return minLength;
    }
    public void setTrim(boolean trim) {
        this.trim = trim;
    }
    public boolean getTrim() {
        return trim;
    }

}