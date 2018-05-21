package com.bulain.zk.validator.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class ExpressionValidator extends AbstractValidator {

    private String expression;

    public void validate(ValidationContext ctx) {
        Boolean answer = Boolean.FALSE;
        Object obj = null;

        try {
            obj = getFieldValue(ctx, expression);
        } catch (Exception e) {
            // let this pass, but it will be logged right below
        }

        if ((obj != null) && (obj instanceof Boolean)) {
            answer = (Boolean) obj;
        } else {
            //TODO wran
        }

        if (!answer.booleanValue()) {
            //addActionError(object);
        }
    }

    private Object getFieldValue(ValidationContext ctx, String expr) {
        // TODO
        return null;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
    public String getExpression() {
        return expression;
    }
}
