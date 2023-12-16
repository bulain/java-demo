package com.bulain.script;


import org.junit.jupiter.api.Test;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NashornTest {

    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("nashorn");

    @Test
    public void testNashorn() throws ScriptException {
        // test
        Object eval = engine.eval("3+2*4");

        // assert
        assertEquals(11, eval);
    }

    @Test
    public void testGlobal2VarScope() throws ScriptException {
        String script = "greeting";
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
        String script = "function test(){page=9; return page;} test()+9;";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals(18.0, eval);
        assertEquals(9, bindings.get("page"));
    }

    @Test
    public void testMultiThread() throws Exception {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("cnt", 0);

        Bindings bindings1 = engine.createBindings();
        Bindings bindings2 = engine.createBindings();
        bindings1.putAll(parameter);
        bindings2.putAll(parameter);

        class ThreadTask implements Runnable {
            private String script;
            private Bindings bindings;
            public ThreadTask(String script, Bindings bindings) {
                this.script = script;
                this.bindings = bindings;
            }

            public void run() {
                try {
                    engine.eval(script, bindings);
                } catch (Exception e) {
                }
            }
        }

        Thread t1 = new Thread(new ThreadTask("for (var i = 0; i < 100000; i++) cnt++", bindings1));
        Thread t2 = new Thread(new ThreadTask("for (var i = 0; i < 100000; i++) cnt--", bindings2));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals(100000.0, bindings1.get("cnt"));
        assertEquals(-100000.0, bindings2.get("cnt"));
    }

    @Test
    public void testImportClass() throws ScriptException {
        // prepare
        Bindings bindings = engine.createBindings();
        String script = "JavaMethod = Java.type('com.bulain.script.NashornTest.JavaMethod');JavaMethod.testStatic('i love javascript.')";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals("I LOVE JAVASCRIPT.", eval);
    }

    @Test
    public void testCallJavaStaticMethod() throws ScriptException {
        // prepare
        Bindings bindings = engine.createBindings();
        String script = "com.bulain.script.NashornTest.JavaMethod.testStatic('i love javascript.')";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals("I LOVE JAVASCRIPT.", eval);
    }

    @Test
    public void testCallJavaMethodBindings() throws ScriptException {
        // prepare
        Bindings bindings = engine.createBindings();
        JavaMethod javaMethod = new JavaMethod();
        bindings.put("methods", javaMethod);
        String script = "methods.test('i love javascript.')";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals("I LOVE JAVASCRIPT.", eval);
    }

    @Test
    public void testCallJavaMethod() throws ScriptException {
        // prepare
        Bindings bindings = engine.createBindings();
        String script = "var methods = new com.bulain.script.NashornTest.JavaMethod(); methods.test('i love javascript.')";

        // test
        Object eval = engine.eval(script, bindings);

        // assert
        assertEquals("I LOVE JAVASCRIPT.", eval);
    }

    @Test
    public void testParameter() throws ScriptException {
        // prepare
        String script = "var user = {a:3, b:4}; function test(){return user.a+user.b;} test()+9;";

        // test
        Object eval = engine.eval(script);

        // assert
        assertEquals(16.0, eval);
    }

    @Test
    public void testCompilable() throws ScriptException {
        if (engine instanceof Compilable) {
            Compilable compiler = (Compilable) engine;
            compiler.compile("3+2+'xx'");
            compiler.compile("function a(){3+2+a}");
        }
    }

    @Test
    public void testInvocable() throws ScriptException, NoSuchMethodException {
        engine.eval("function test(a){return a+15;}");

        if (engine instanceof Invocable) {
            Invocable invocable = (Invocable) engine;

            Object function = invocable.invokeFunction("test", 5);

            assertEquals(20.0, function);
        }
    }

    public static class JavaMethod {
        public static String testStatic(final String str) {
            return str.toUpperCase();
        }

        public JavaMethod() {
        }

        public String test(final String str) {
            return str.toUpperCase();
        }
    }
}
