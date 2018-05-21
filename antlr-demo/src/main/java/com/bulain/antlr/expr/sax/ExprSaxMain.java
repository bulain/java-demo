package com.bulain.antlr.expr.sax;

import java.io.StringReader;


public class ExprSaxMain {
    public static void main(String[] args) throws Exception {
        String str = "1+2*(3+4)";
        StringReader reader = new StringReader(str);
        ExprLexer lexer = new ExprLexer(reader);
        ExprParser parser = new ExprParser(lexer);
        try {
            int expr = parser.expr();
            System.out.println(expr);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
