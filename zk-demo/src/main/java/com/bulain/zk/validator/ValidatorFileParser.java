package com.bulain.zk.validator;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ValidatorFileParser {
    Map<String, List<ValidatorConfig>> parseValidatorConfigs(ValidatorFactory validatorFactory, InputStream is, String resourceName);
    void parseValidatorDefinitions(Map<String, String> validators, InputStream is, String resourceName);
}
