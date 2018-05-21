package com.bulain.zk.validator.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

public class FieldExpressionValidator extends FieldValidator {
    private String expression;

    public void validate(ValidationContext ctx) {
        Boolean answer = Boolean.FALSE;
        Object obj = null;

        try {
            obj = getFieldValue(ctx, expression);
        } catch (Exception e) {
            //warn
        }

        if ((obj != null) && (obj instanceof Boolean)) {
            answer = (Boolean) obj;
        } else {
            //TODO warn
        }

        if (!answer.booleanValue()) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key)});
            addInvalidMessage(ctx, getFieldName(), message);
        }
    }
    
    protected String getDefaultMessageKey() {
        return "validate.fieldExpression";
    }
    
    private Object getFieldValue(ValidationContext ctx, String expr) {
        //TODO
        return null;
    }
    
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public String getExpression() {
        return expression;
    }

}
