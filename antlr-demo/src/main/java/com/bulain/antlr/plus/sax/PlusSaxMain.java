package com.bulain.antlr.plus.sax;

import java.io.StringReader;

public class PlusSaxMain {
    public static void main(String[] args) throws Exception {
        String str = "1+2";
        StringReader reader = new StringReader(str);
        PlusLexer lexer = new PlusLexer(reader);
        PlusParser parser = new PlusParser(lexer);
        try {
            System.out.println(parser.expr());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
