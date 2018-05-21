package com.bulain.script;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mvel2.MVEL;
import org.mvel2.templates.TemplateRuntime;

public class Mvel2Test {

    @Test
    public void testEvelMap() {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("x", new Integer(5));
        vars.put("y", new Integer(10));

        Integer result = (Integer) MVEL.eval("x * y", vars);
        assertEquals(Integer.valueOf(50), result);
    }

    @Test
    public void testEvelObject() {
        Person person = new Person();
        person.setName("Test");

        Object result1 = MVEL.eval("name == 'Test'", person);
        assertEquals(true, result1);

        String result2 = (String) MVEL.eval("name", person);
        assertEquals("Test", result2);
    }
    
    @Test
    public void testEvalTemplate(){
        String template = "Hello, my name is @{name}";
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("name", "Michael");

        String output = (String) TemplateRuntime.eval(template, vars);
        assertEquals("Hello, my name is Michael", output);
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
