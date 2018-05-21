package com.bulain.zk.validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.zkoss.bind.Validator;

import com.bulain.zk.util.ObjectFactory;
import com.bulain.zk.validator.validators.FieldValidator;

public class DefaultValidatorFactory implements ValidatorFactory {

    protected Map<String, String> validators = new HashMap<String, String>();
    protected ValidatorFileParser validatorFileParser;

    public DefaultValidatorFactory(ValidatorFileParser validatorFileParser) {
        this.validatorFileParser = validatorFileParser;
        parseValidators();
    }

    public Validator getValidator(ValidatorConfig cfg) {
        String className = lookupRegisteredValidatorType(cfg.getType());

        FieldValidator validator;

        try {
            validator = ObjectFactory.buildValidator(className, cfg.getParams());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        validator.setMessageKey(cfg.getMessageKey());

        return validator;
    }

    public void registerValidator(String name, String className) {
        validators.put(name, className);
    }

    public String lookupRegisteredValidatorType(String name) {
        String className = validators.get(name);
        if (className == null) {
            throw new IllegalArgumentException("There is no validator class mapped to the name " + name);
        }
        return className;
    }
    
    private void parseValidators() {
        String resourceName = "com/bulain/zk/validator/default.xml";
        retrieveValidatorConfiguration(resourceName);
    }

    private void retrieveValidatorConfiguration(String resourceName) {
        ClassPathResource resource = new ClassPathResource(resourceName);
        try {
            InputStream is = resource.getInputStream();
            validatorFileParser.parseValidatorDefinitions(validators, is, resourceName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
