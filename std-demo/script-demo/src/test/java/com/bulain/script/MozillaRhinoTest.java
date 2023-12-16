package com.bulain.script;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MozillaRhinoTest {
    @Test
    public void testRhino() {
        try {
            Context cx = Context.enter();
            Scriptable parent = cx.initStandardObjects();
            Scriptable scope1 = cx.initStandardObjects();
            Scriptable scope2 = cx.initStandardObjects();
            Scriptable scope3 = cx.initStandardObjects();
            
            scope1.setParentScope(parent);
            scope2.setParentScope(parent);
            scope3.setParentScope(scope1);
            
            parent.put("a", scope1, new BigDecimal("2.0"));
            parent.put("b", scope2, new BigDecimal("3.8"));
            parent.put("c", scope1, new BigDecimal("2.3"));
            parent.put("d", parent, new BigDecimal("1.1"));
            
            Object result = cx.evaluateString(scope3, "a + c", "<eval>", 1, null);
            System.out.println(result);
            
            result = cx.evaluateString(scope2, "b + d", "<eval>", 1, null);
            System.out.println(result);
            
        } finally {
            Context.exit();
        }
    }
    
    
}
