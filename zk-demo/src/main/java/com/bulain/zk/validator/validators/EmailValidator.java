package com.bulain.zk.validator.validators;

public class EmailValidator extends RegexFieldValidator {

    public static final String emailAddressPattern = "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

    public EmailValidator() {
        setExpression(emailAddressPattern);
        setCaseSensitive(false);
    }
    
    protected String getDefaultMessageKey() {
        return "validate.email";
    }

}
