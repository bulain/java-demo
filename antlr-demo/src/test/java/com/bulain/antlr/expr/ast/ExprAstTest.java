package com.bulain.antlr.expr.ast;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;

import com.bulain.antlr.util.Context;

public class ExprAstTest {

    @Before
    public void setUp() throws Exception {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        map.put("a", new BigDecimal(1));
        map.put("b", new BigDecimal(2));
        map.put("c", new BigDecimal(3));
        map.put("d", new BigDecimal(4));
        Context.set(map);
    }

    @Test
    public void testExpr1() throws RecognitionException, TokenStreamException{
        assertEquals(BigDecimal.valueOf(3),runTest("1+2"));
        assertEquals(BigDecimal.valueOf(6),runTest("1+2+3"));
        assertEquals(BigDecimal.valueOf(0),runTest("1+2-3"));
        assertEquals(BigDecimal.valueOf(9),runTest("1+2*4"));
        assertEquals(BigDecimal.valueOf(2),runTest("1+2/2"));
        assertEquals(BigDecimal.valueOf(2.1),runTest("1+2.2/2"));
        assertEquals(BigDecimal.valueOf(26),runTest("1+(2+3)*4+5"));
        assertEquals(BigDecimal.valueOf(45d),runTest("1+(2+3*4/6-0.5)*4+5*(3+3)"));
        assertEquals(BigDecimal.valueOf(25.2),runTest("a + (b + c) * d + 1 + 2.1 * ( -3 + 4) + 3.3 / 3"));
    }
    

    private BigDecimal runTest(String str) throws RecognitionException, TokenStreamException {
        StringReader reader = new StringReader(str);
        ExprLexer lexer = new ExprLexer(reader);
        ExprParser parser = new ExprParser(lexer);
        
        parser.expr();
        AST t = parser.getAST();
        System.out.println(t.toStringTree());
        
        ExprTreeParser treeParser = new ExprTreeParser();
        BigDecimal expr = treeParser.expr(t);
        System.out.println(expr);
        
        return expr;
    }
}
