package com.bulain.antlr;

import org.junit.Test;

public class AntlrTest {
    @Test
    public void testAntlr() {
        antlr.Tool.main(new String[]{"-o", "target/expr", "src/test/resources/expr_ast05.g"});
    }
}
