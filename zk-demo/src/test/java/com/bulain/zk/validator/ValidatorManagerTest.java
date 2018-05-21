package com.bulain.zk.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.zkoss.bind.Validator;

import com.bulain.zk.validator.validators.DateRangeFieldValidator;
import com.bulain.zk.validator.validators.IntRangeFieldValidator;
import com.bulain.zk.validator.validators.LongRangeFieldValidator;
import com.bulain.zk.validator.validators.RequiredFieldValidator;
import com.bulain.zk.validator.validators.RequiredItemValidator;
import com.bulain.zk.validator.validators.RequiredStringValidator;
import com.bulain.zk.validator.validators.ShortRangeFieldValidator;
import com.bulain.zk.validator.validators.StringLengthFieldValidator;

public class ValidatorManagerTest {

    private ValidatorManager validatorManager = new DefaultValidatorManager();

    @Test
    public void testGetValidators() throws ParseException {
        List<Validator> validators = validatorManager.getValidators("validator.test", "testCmd");
        assertEquals(8, validators.size());

        assertTrue(validators.get(0) instanceof RequiredFieldValidator);
        RequiredFieldValidator required = (RequiredFieldValidator) validators.get(0);
        assertEquals("testNamespace", required.getNamespace());
        assertEquals("test.required", required.getMessageKey());
        assertEquals("testField", required.getFieldName());

        assertTrue(validators.get(1) instanceof RequiredStringValidator);
        RequiredStringValidator requiredstring = (RequiredStringValidator) validators.get(1);
        assertEquals("testNamespace", requiredstring.getNamespace());
        assertEquals("test.requiredstring", requiredstring.getMessageKey());
        assertEquals("testField", requiredstring.getFieldName());
        assertEquals(true, requiredstring.isTrim());

        assertTrue(validators.get(2) instanceof RequiredItemValidator);
        RequiredItemValidator requireditem = (RequiredItemValidator) validators.get(2);
        assertEquals("testNamespace", requireditem.getNamespace());
        assertEquals("test.requireditem", requireditem.getMessageKey());
        assertEquals("testField", requireditem.getFieldName());
        assertEquals(true, requireditem.isTrim());

        assertTrue(validators.get(3) instanceof IntRangeFieldValidator);
        IntRangeFieldValidator intrange = (IntRangeFieldValidator) validators.get(3);
        assertEquals("testNamespace", intrange.getNamespace());
        assertEquals("test.int", intrange.getMessageKey());
        assertEquals("testField", intrange.getFieldName());
        assertEquals(new Integer(10), intrange.getMin());
        assertEquals(new Integer(50), intrange.getMax());

        assertTrue(validators.get(4) instanceof LongRangeFieldValidator);
        LongRangeFieldValidator longrange = (LongRangeFieldValidator) validators.get(4);
        assertEquals("testNamespace", longrange.getNamespace());
        assertEquals("test.long", longrange.getMessageKey());
        assertEquals("testField", longrange.getFieldName());
        assertEquals(new Long(10), longrange.getMin());
        assertEquals(new Long(50), longrange.getMax());

        assertTrue(validators.get(5) instanceof ShortRangeFieldValidator);
        ShortRangeFieldValidator shortrange = (ShortRangeFieldValidator) validators.get(5);
        assertEquals("testNamespace", shortrange.getNamespace());
        assertEquals("test.short", shortrange.getMessageKey());
        assertEquals("testField", shortrange.getFieldName());
        assertEquals(Short.valueOf("10"), shortrange.getMin());
        assertEquals(Short.valueOf("50"), shortrange.getMax());

        assertTrue(validators.get(6) instanceof DateRangeFieldValidator);
        DateRangeFieldValidator daterange = (DateRangeFieldValidator) validators.get(6);
        assertEquals("testNamespace", daterange.getNamespace());
        assertEquals("test.date", daterange.getMessageKey());
        assertEquals("testField", daterange.getFieldName());
        assertEquals(DateUtils.parseDate("2013/01/01", new String[]{"yyyy/MM/dd"}), daterange.getMin());
        assertEquals(DateUtils.parseDate("2013/12/31", new String[]{"yyyy/MM/dd"}), daterange.getMax());

        assertTrue(validators.get(7) instanceof StringLengthFieldValidator);
        StringLengthFieldValidator stringlength = (StringLengthFieldValidator) validators.get(7);
        assertEquals("testNamespace", stringlength.getNamespace());
        assertEquals("test.stringlength", stringlength.getMessageKey());
        assertEquals("testField", stringlength.getFieldName());
        assertEquals(new Integer(10), intrange.getMin());
        assertEquals(new Integer(50), intrange.getMax());

    }

    @Test
    public void testValidate() {
    }

}
