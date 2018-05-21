package com.bulain.zk.validator.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

import com.bulain.common.pojo.Item;

public class RequiredItemValidator extends FieldValidator {
    private boolean trim = true;

    public void validate(ValidationContext ctx) {
        Object value = this.getFieldValue(ctx);

        if (!(value instanceof Item)) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key)});
            addInvalidMessage(ctx, getFieldName(), message);
        } else {
            Item item = (Item) value;

            String s = item.getKey();
            if (s == null) {
                String key = getNamespace() + "." + getFieldName();
                String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key)});
                addInvalidMessage(ctx, getFieldName(), message);
            } else {
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
    }

    protected String getDefaultMessageKey() {
        return "validate.requireditem";
    }

    public boolean isTrim() {
        return trim;
    }
    public void setTrim(boolean trim) {
        this.trim = trim;
    }

}
