package com.bulain.zk.validator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;

public class DefaultValidatorManager implements ValidatorManager {

    protected static final String VALIDATION_CONFIG_SUFFIX = "-validation.xml";

    private final Map<String, List<Validator>> validatorCache = Collections
            .synchronizedMap(new HashMap<String, List<Validator>>());

    private final Map<String, Map<String, List<ValidatorConfig>>> validatorCacheFile = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<ValidatorConfig>>>());

    private ValidatorFileParser validatorFileParser = new DefaultValidatorFileParser();
    private ValidatorFactory validatorFactory = new DefaultValidatorFactory(validatorFileParser);
    
    private boolean reload = true;

    public synchronized List<Validator> getValidators(String id, String command) {

        String validatorKey = buildValidatorKey(id, command);

        if (!reload && validatorCache.containsKey(validatorKey)) {
            return validatorCache.get(validatorKey);
        }

        if (validatorCacheFile.containsKey(id)) {
            if (reload) {
                validatorCacheFile.put(id, buildValidatorConfigFile(id));
            }
        } else {
            validatorCacheFile.put(id, buildValidatorConfigFile(id));
        }

        Map<String, List<ValidatorConfig>> validatorConfigFile = validatorCacheFile.get(id);

        List<ValidatorConfig> cfgs = validatorConfigFile.get(command);
        ArrayList<Validator> validators = new ArrayList<Validator>();
        if (cfgs != null) {
            for (ValidatorConfig cfg : cfgs) {
                Validator validator = validatorFactory.getValidator(cfg);
                validators.add(validator);
            }
        }
        validatorCache.put(validatorKey, validators);

        return validators;
    }

    public void validate(String id, String command, ValidationContext ctx) {
        List<Validator> validators = getValidators(id, command);
        for(Validator validator : validators){
            validator.validate(ctx);
        }
    }
    
    private String buildValidatorKey(String id, String command) {
        return id + "." + command;
    }

    private Map<String, List<ValidatorConfig>> buildValidatorConfigFile(String id) {
        String path = id.replaceAll("\\.", "/") + VALIDATION_CONFIG_SUFFIX;

        ClassPathResource resource = new ClassPathResource(path);
        InputStream is;
        try {
            is = resource.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return validatorFileParser.parseValidatorConfigs(validatorFactory, is, path);
    }

}
