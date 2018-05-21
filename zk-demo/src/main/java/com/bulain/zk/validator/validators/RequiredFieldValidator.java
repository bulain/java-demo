package com.bulain.zk.validator.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

public class RequiredFieldValidator extends FieldValidator {
    public void validate(ValidationContext ctx) {
        Object value = this.getFieldValue(ctx);

        if (value == null) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key)});
            addInvalidMessage(ctx, getFieldName(), message);
        }
    }
    
    protected String getDefaultMessageKey() {
        return "validate.required";
    }
    
}
