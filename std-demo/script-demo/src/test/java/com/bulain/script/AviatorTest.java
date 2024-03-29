package com.bulain.script;


import org.junit.jupiter.api.Test;

import javax.script.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AviatorTest {

    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("Aviator");

    @Test
    public void testAviator() throws ScriptException {
        // test
        Object eval = engine.eval("3+2*4");

        // assert
        assertEquals(11L, eval);
    }

    @Test
    public void testGlobal2VarScope() throws ScriptException {
        String script = "greeting";
        Object eval;

        // ScriptEngineManager scope
        manager.put("greeting", "ScriptEngineManager scope");
        eval = engine.eval(script);
        //assertEquals(null, eval);

        // ScriptEngine scope
        engine.put("greeting", "ScriptEngine scope");
        eval = engine.eval(script);
        assertEquals("ScriptEngine scope", eval);

        // Bindings scope
        Bindings bindings = engine.createBindings();
        bindings.put("greeting", "Bindings scope");
        eval = engine.eval(script, bindings);
        assertEquals("Bindings scope", eval);
    }

    @Test
    public void testVar2GlobalScope() throws ScriptException {
        String script = "greeting";
        Object eval;

        // Bindings scope
        Bindings bindings = engine.createBindings();
        bindings.put("greeting", "Bindings scope");
        eval = engine.eval(script, bindings);
        assertEquals("Bindings scope", eval);

        // ScriptEngine scope
        engine.put("greeting", "ScriptEngine scope");
        eval = engine.eval(script);
        assertEquals("ScriptEngine scope", eval);

        // ScriptEngineManager scope
        manager.put("greeting", "ScriptEngineManager scope");
        eval = engine.eval(script);
        assertEquals("ScriptEngine scope", eval);
    }

    @Test
    public void testBindings() throws ScriptException {
        // parameter
        Bindings bindings = engine.createBindings();
        bindings.put("page", 7);
        String script = "page+9";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals(16L, eval);
        assertEquals(7, bindings.get("page"));
    }

    @Test
    public void testCompilable() throws ScriptException {
        if (engine instanceof Compilable) {
            Compilable compiler = (Compilable) engine;
            compiler.compile("3+2+'xx'");
        }
    }
}
