package com.bulain.script;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class MozillaTest {

    private Context ctx;
    private Scriptable scope;

    @BeforeEach
    void setUp() {
        ctx = Context.enter();
        scope = ctx.initStandardObjects();
    }

    @AfterEach
    void tearDown() {
        Context.exit();
    }

    @Test
    void testRhino() {

        // test
        Object eval = ctx.evaluateString(scope, "3+2*4", null, 0, null);

        // assert
        assertEquals(11, eval);

    }

    @Test
    void testCallJsMethod() {

        // test
        ctx.evaluateString(scope, "function test(){page=9; return page;}", null, 0, null);
        Function func = (Function) scope.get("test", scope);
        Object call = func.call(ctx, scope, scope, new Object[]{});

        // assert
        assertEquals(9, call);
    }

    @Test
    void testGlobal2VarScope() {

        //global
        Context gctx = Context.enter();
        ScriptableObject gscope = gctx.initStandardObjects();
        gctx.evaluateString(gscope, "var greeting='ScriptEngine scope';", null, 0, null);
        Context.exit();

        //param
        Context lctx = null;
        Scriptable lscope = null;
        Object eval = null;

        //test global
        lctx = Context.enter();
        lscope = lctx.newObject(gscope);
        eval = lctx.evaluateString(lscope, "greeting", null, 0, null);
        assertEquals("ScriptEngine scope", eval);
        Context.exit();

        //test local
        lctx = Context.enter();
        lscope = lctx.newObject(gscope);
        lctx.evaluateString(lscope, "var greeting='Script scope';", null, 0, null);
        eval = lctx.evaluateString(lscope, "greeting", null, 0, null);
        assertEquals("Script scope", eval);
        Context.exit();

        //test global
        lctx = Context.enter();
        lscope = lctx.newObject(gscope);
        eval = lctx.evaluateString(lscope, "greeting", null, 0, null);
        assertEquals("ScriptEngine scope", eval);
        Context.exit();

    }

    @Test
    void testCallJavaStaticMethod1() {
        // prepare
        String script = "var JavaMethod = Packages.com.bulain.script.MozillaTest.JavaMethod;JavaMethod.testStatic('i love javascript.')";

        // test
        Object eval = ctx.evaluateString(scope, script, null, 0, null);

        // assert
        NativeJavaObject njo = (NativeJavaObject) eval;
        assertEquals("I LOVE JAVASCRIPT.", njo.unwrap());
    }

    @Test
    void testCallJavaStaticMethod2() {

        // prepare
        String script = "com.bulain.script.MozillaTest.JavaMethod.testStatic('i love javascript.')";

        // test
        Object eval = ctx.evaluateString(scope, script, null, 0, null);

        // assert
        NativeJavaObject njo = (NativeJavaObject) eval;
        assertEquals("I LOVE JAVASCRIPT.", njo.unwrap());

    }

    @Test
    void testCallJavaMethod1() {
        // prepare
        String script = "var methods = new com.bulain.script.MozillaTest.JavaMethod(); methods.test('i love javascript.')";

        // test
        Object eval = ctx.evaluateString(scope, script, null, 0, null);

        // assert
        NativeJavaObject njo = (NativeJavaObject) eval;
        assertEquals("I LOVE JAVASCRIPT.", njo.unwrap());
    }

    @Test
    void testCallJavaMethod2() {

        JavaMethod callback = new JavaMethod();
        Scriptable obj = Context.toObject(callback, scope);
        scope.put("ret", scope, obj);

        // prepare
        String script = "ret.test('i love javascript.')";

        // test
        Object eval = ctx.evaluateString(scope, script, null, 0, null);

        // assert
        NativeJavaObject njo = (NativeJavaObject) eval;
        assertEquals("I LOVE JAVASCRIPT.", njo.unwrap());
    }

    @Test
    void testCallJavaMethod3() {

        JavaMethod callback = new JavaMethod();
        Scriptable obj = Context.toObject(callback, scope);
        scope.put("ret", scope, obj);

        Sync thread = new Sync(scope);
        Scriptable ot = Context.toObject(thread, scope);
        scope.put("http", scope, ot);

        Ui ui = new Ui();
        Scriptable uis = Context.toObject(ui, scope);
        scope.put("ui", scope, uis);

        // prepare
        String script = "function cjm(data, cb) {http.remote(data, function(data){var json = ret.test(data); cb(json)});}";
        ctx.evaluateString(scope, script, null, 0, null);

        // test
        script = "cjm('i love javascript.', function(data){ui.call(data)});";
        ctx.evaluateString(scope, script, null, 0, null);

        try {
            thread.join();
        } catch (InterruptedException e) {
        }

    }

    public static class Ui {

        public void call(String data) {
            assertEquals("I LOVE JAVASCRIPT.", data);
        }

    }

    public static class Sync extends Thread {
        private String str;
        private Object script;
        private Scriptable scope;

        public Sync(Scriptable scope) {
            this.scope = scope;
        }

        public void remote(String data, Object script) {
            this.str = data;
            this.script = script;

            this.start();
        }

        @Override
        public void run() {
            //耗时远程调用
            if (script instanceof Function) {
                try {
                    Context ctx = Context.enter();
                    Function func = (Function) script;
                    func.call(ctx, scope, scope, new Object[]{str});
                } finally {
                    Context.exit();
                }
            }
        }
    }

    public static class JavaMethod {
        public static String testStatic(final String str) {
            return str.toUpperCase();
        }

        public String test(final String str) {
            return str.toUpperCase();
        }

    }

}
