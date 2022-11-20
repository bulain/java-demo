package com.bulain.script;


import com.googlecode.jslint4java.JSLint;
import com.googlecode.jslint4java.JSLintBuilder;
import com.googlecode.jslint4java.JSLintResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Jslint4javaTest {
    @Test
    public void testJslint4java(){
        JSLintBuilder builder = new JSLintBuilder();
        JSLint jslint = builder.fromDefault();
        
        String javaScript = "a=3+2;";
        JSLintResult result = jslint.lint("test", javaScript);
        
        assertTrue(result.getIssues().isEmpty());
    }
}
