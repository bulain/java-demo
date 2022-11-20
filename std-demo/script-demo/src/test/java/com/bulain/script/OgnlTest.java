package com.bulain.script;


import ognl.Ognl;
import ognl.OgnlException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OgnlTest {

    @Test
    public void testEvelMap() throws OgnlException {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("x", new Integer(5));
        vars.put("y", new Integer(10));

        Object expr = Ognl.parseExpression("x * y");
        Integer result = (Integer) Ognl.getValue(expr, vars);
        assertEquals(Integer.valueOf(50), result);
    }

    @Test
    public void testEvelObject() throws OgnlException {
        Object expr;
        Object result;

        Person person = new Person();
        person.setName("Test");

        expr = Ognl.parseExpression("name == 'Test'");
        result = Ognl.getValue(expr, person);
        assertEquals(true, result);

        expr = Ognl.parseExpression("name");
        result = (String) Ognl.getValue(expr, person);
        assertEquals("Test", result);
    }

    static class Person {
        private String name;
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

}
