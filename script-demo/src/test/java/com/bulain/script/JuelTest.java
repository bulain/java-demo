package com.bulain.script;

import static org.junit.Assert.assertEquals;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class JuelTest {
    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("juel");

    @Test
    public void testJuel() throws ScriptException {
        // test
        Object eval = engine.eval("${3+2*4}");

        // assert
        assertEquals(11L, eval);
    }

    @Test
    public void testGlobal2VarScope() throws ScriptException {
        String script = "${greeting}";
        Object eval;

        // ScriptEngineManager scope
        manager.put("greeting", "ScriptEngineManager scope");
        eval = engine.eval(script);
        assertEquals("ScriptEngineManager scope", eval);

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
        String script = "${greeting}";
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
        bindings.put("page", 9);
        String script = "${page+9}";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals(18L, eval);
        assertEquals(9, bindings.get("page"));
    }

    @Test
    public void testCallJavaMethod() throws ScriptException {
        class JavaMethod {
            public JavaMethod() {
            }
            public String test(final String str) {
                return str.toUpperCase();
            }
        }

        // prepare
        Bindings bindings = engine.createBindings();
        JavaMethod javaMethod = new JavaMethod();
        bindings.put("methods", javaMethod);
        String script = "${methods.test('i love juel.')}";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals("I LOVE JUEL.", eval);

        javaMethod.test("xxx");
    }

    @Test
    public void testFormat() throws ScriptException {
        class JavaMethod {
            public JavaMethod() {
            }
            public String format(String format, Object... args) {
                return String.format(format, args);
            }
        }

        // prepare
        Bindings bindings = engine.createBindings();
        JavaMethod javaMethod = new JavaMethod();
        bindings.put("methods", javaMethod);
        bindings.put("fstr", "Hello %s.");
        bindings.put("vstr", "Bulain");
        String script = "${methods.format(fstr,vstr)}";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals("Hello Bulain.", eval);

        javaMethod.format("test %s", "ok");
    }

    @Test
    public void testCompilable() throws ScriptException {
        if (engine instanceof Compilable) {
            Compilable compiler = (Compilable) engine;
            compiler.compile("${3+2+'xx'}");
        }
    }
}
