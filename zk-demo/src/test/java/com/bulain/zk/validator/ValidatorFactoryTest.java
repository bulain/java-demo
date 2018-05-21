package com.bulain.zk.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.zkoss.bind.Validator;

import com.bulain.zk.validator.ValidatorConfig.Builder;
import com.bulain.zk.validator.validators.RequiredStringValidator;

public class ValidatorFactoryTest {

    private ValidatorFileParser validatorFileParser = new DefaultValidatorFileParser();
    private ValidatorFactory validatorFactory = new DefaultValidatorFactory(validatorFileParser);

    @Test
    public void testLookupRegisteredValidatorType() {
        String[][] validators = new String[][]{
                {"required", "com.bulain.zk.validator.validators.RequiredFieldValidator"},
                {"requiredstring", "com.bulain.zk.validator.validators.RequiredStringValidator"}};

        for (String[] pair : validators) {
            String className = validatorFactory.lookupRegisteredValidatorType(pair[0]);
            assertEquals(pair[1], className);
        }
    }

    @Test
    public void testGetValidator() {
        Builder builder = new ValidatorConfig.Builder("requiredstring");
        builder.addParam("fieldName", "testFieldName").addParam("namespace", "testNamespace").addParam("trim", "true")
                .messageKey("testMessageKey");
        ValidatorConfig cfg = builder.build();
        Validator validator = validatorFactory.getValidator(cfg);

        assertTrue(validator instanceof RequiredStringValidator);

        RequiredStringValidator requiredFieldValidator = (RequiredStringValidator) validator;
        assertEquals("testFieldName", requiredFieldValidator.getFieldName());
        assertEquals("testNamespace", requiredFieldValidator.getNamespace());
        assertEquals("testMessageKey", requiredFieldValidator.getMessageKey());
        assertEquals(true, requiredFieldValidator.isTrim());
    }

}
