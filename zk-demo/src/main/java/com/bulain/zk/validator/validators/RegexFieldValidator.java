package com.bulain.zk.validator.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

public class RegexFieldValidator extends FieldValidator {

    private String expression;
    private boolean caseSensitive = true;
    private boolean trim = true;

    public void validate(ValidationContext ctx) {
        Object value = this.getFieldValue(ctx);

        if (value == null || expression == null) {
            return;
        }

        if (!(value instanceof String)) {
            return;
        }

        String str = ((String) value).trim();
        if (str.length() == 0) {
            return;
        }

        Pattern pattern;
        if (isCaseSensitive()) {
            pattern = Pattern.compile(expression);
        } else {
            pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        }

        String compare = (String) value;
        if (trim) {
            compare = compare.trim();
        }
        Matcher matcher = pattern.matcher(compare);

        if (!matcher.matches()) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key)});
            addInvalidMessage(ctx, getFieldName(), message);
        }
    }
    
    protected String getDefaultMessageKey() {
        return "validate.regexField";
    }
    
    public String getExpression() {
        return expression;
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public boolean isCaseSensitive() {
        return caseSensitive;
    }
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }
    public boolean isTrim() {
        return trim;
    }
    public void setTrim(boolean trim) {
        this.trim = trim;
    }

}
