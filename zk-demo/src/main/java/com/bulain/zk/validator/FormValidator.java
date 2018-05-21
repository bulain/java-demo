package com.bulain.zk.validator;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class FormValidator extends AbstractValidator {
    public void validate(ValidationContext ctx) {
        String id = (String) ctx.getValidatorArg("id");
        if (id == null) {
            throw new RuntimeException("validation id is required");
        }

        System.out.println("================================");
        Object base = ctx.getProperty().getBase();
        Map<String, Property> beanProps = ctx.getProperties(base);
        for (Map.Entry<String, Property> entry : beanProps.entrySet()) {
            Property value = entry.getValue();
            if (value != null) {
                System.out.println(entry.getKey() + ": " + value.getValue());
            }
        }

        String command = ctx.getCommand();
        ValidatorManager validatorManager = new DefaultValidatorManager();
        validatorManager.validate(id, command, ctx);

    }
}
