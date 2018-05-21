package com.bulain.zk.validator;

import org.zkoss.bind.Validator;

public interface ValidatorFactory {
    Validator getValidator(ValidatorConfig cfg);
    void registerValidator(String name, String className);
    String lookupRegisteredValidatorType(String name);
}
