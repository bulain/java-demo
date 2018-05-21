package com.bulain.antlr.plus.ast;

import java.io.StringReader;

import antlr.collections.AST;

public class PlusAstMain {
    public static void main(String[] args) throws Exception {
        String str = "1+2";
        StringReader reader = new StringReader(str);
        PlusLexer lexer = new PlusLexer(reader);
        PlusParser parser = new PlusParser(lexer);
        try {
            parser.expr();
            AST t = parser.getAST();
            PlusTreeParser treeParser = new PlusTreeParser();
            System.out.println(treeParser.expr(t));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
