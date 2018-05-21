package com.bulain.zk.validator;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ValidatorConfig {
    private String type;
    private Map<String, String> params;
    private String messageKey;

    protected ValidatorConfig(String validatorType) {
        this.type = validatorType;
        params = new LinkedHashMap<String, String>();
    }

    protected ValidatorConfig(ValidatorConfig orig) {
        this.type = orig.type;
        this.params = new LinkedHashMap<String, String>(orig.params);
        this.messageKey = orig.messageKey;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Map<String, String> getParams() {
        return params;
    }
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    public String getMessageKey() {
        return messageKey;
    }
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public static final class Builder {
        private ValidatorConfig target;

        public Builder(String validatorType) {
            target = new ValidatorConfig(validatorType);
        }

        public Builder(ValidatorConfig config) {
            target = new ValidatorConfig(config);
        }

        public Builder messageKey(String key) {
            if ((key != null) && (key.trim().length() > 0)) {
                target.messageKey = key;
            }
            return this;
        }

        public Builder addParam(String name, String value) {
            if (value != null && name != null) {
                target.params.put(name, value);
            }
            return this;
        }

        public Builder addParams(Map<String, String> params) {
            target.params.putAll(params);
            return this;
        }

        public ValidatorConfig build() {
            target.params = Collections.unmodifiableMap(target.params);
            ValidatorConfig result = target;
            target = new ValidatorConfig(target);
            return result;
        }

        public Builder removeParam(String key) {
            target.params.remove(key);
            return this;
        }
    }
}
