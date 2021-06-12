package com.bulain.script;

import org.junit.Test;
import org.mvel2.templates.TemplateRuntime;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static org.junit.Assert.assertEquals;

public class Mvel2Test {

    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("mvel");

    @Test
    public void testEvelMap() throws ScriptException {
        Bindings bindings = engine.createBindings();
        bindings.put("x", new Integer(5));
        bindings.put("y", new Integer(10));

        Integer result = (Integer) engine.eval("x * y", bindings);
        assertEquals(Integer.valueOf(50), result);
    }

    @Test
    public void testEvelObject() throws ScriptException {
        
        Person person = new Person();
        person.setName("Test");

        Bindings bindings = engine.createBindings();
        bindings.put("person", person);
        
        Object result1 = engine.eval("person.name == 'Test'", bindings);
        assertEquals(true, result1);

        String result2 = (String) engine.eval("person.name", bindings);
        assertEquals("Test", result2);
    }
    
    @Test
    public void testEvalTemplate() throws ScriptException {
        String template = "Hello, my name is @{name}";
        
        Bindings bindings = engine.createBindings();
        bindings.put("name", "Michael");

        String output = (String) TemplateRuntime.eval(template, bindings);
        assertEquals("Hello, my name is Michael", output);
    }

    public static class Person {
        private String name;
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }
    
}
