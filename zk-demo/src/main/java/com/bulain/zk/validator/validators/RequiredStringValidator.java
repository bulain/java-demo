package com.bulain.zk.validator.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

public class RequiredStringValidator extends FieldValidator {
    private boolean trim = true;

    public void validate(ValidationContext ctx) {
        Object value = this.getFieldValue(ctx);

        if (!(value instanceof String)) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key)});
            addInvalidMessage(ctx, getFieldName(), message);
        } else {
            String s = (String) value;

            if (trim) {
                s = s.trim();
            }

            if (s.length() == 0) {
                String key = getNamespace() + "." + getFieldName();
                String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key)});
                addInvalidMessage(ctx, getFieldName(), message);
            }
        }
    }
    
    protected String getDefaultMessageKey() {
        return "validate.requiredstring";
    }

    public boolean isTrim() {
        return trim;
    }
    public void setTrim(boolean trim) {
        this.trim = trim;
    }
}
