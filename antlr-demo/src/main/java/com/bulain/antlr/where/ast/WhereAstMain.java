package com.bulain.antlr.where.ast;

import java.io.StringReader;

import antlr.collections.AST;

public class WhereAstMain {
    public static void main(String[] args) throws Exception {
        //String str = "a = 1 and  (b >= 4.0 or c <> -4.8) and d != 'and' ";
        //String str = "a = 1 and  b >= 4.0 or c <> -4.8 and d != 'and' ";
        //String str = "a = 1 and  b >= 4.0 or c <> -4.8 and d != 'and' and e in (1,2)";
        //String str = "a = 1 and  b > 4.0 or not c <= -4.8 and d != 'and' and e in (1,2) and f not in ('1','2')";
        String str = "a = 1 and  b > 4.0 or not c <= -4.8 and d != 'and' and e in (1,2) and (f not in ('1','2') and g is not null)";
        StringReader reader = new StringReader(str);
        WhereLexer lexer = new WhereLexer(reader);
        WhereParser parser = new WhereParser(lexer);
        try {
            parser.expr();
            AST t = parser.getAST();
            System.out.println(t.toStringTree());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
