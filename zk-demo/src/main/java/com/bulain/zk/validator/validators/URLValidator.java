package com.bulain.zk.validator.validators;

import java.net.MalformedURLException;
import java.net.URL;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

public class URLValidator extends FieldValidator {

    public void validate(ValidationContext ctx) {
        Object value = this.getFieldValue(ctx);

        if (value == null || value.toString().length() == 0) {
            return;
        }

        if (!(value.getClass().equals(String.class)) || !verifyUrl((String) value)) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key)});
            addInvalidMessage(ctx, getFieldName(), message);
        }
    }
    
    protected String getDefaultMessageKey() {
        return "validate.url";
    }

    private boolean verifyUrl(String url) {
        if (url == null) {
            return false;
        }

        if (url.startsWith("https://")) {
            url = "http://" + url.substring(8);
        }

        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
