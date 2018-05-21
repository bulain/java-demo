package com.bulain.antlr.bool.ast;

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

public class AstTest {

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
	public void testAst1() throws RecognitionException, TokenStreamException {
		assertEquals(true, runTest("2>1"));
		assertEquals(false, runTest("1>2"));
		
		assertEquals(true, runTest("1<2"));
		assertEquals(false, runTest("2<1"));
		
		assertEquals(true, runTest("1=1"));
		assertEquals(false, runTest("2=1"));
		
		assertEquals(true, runTest("1!=2"));
		assertEquals(false, runTest("1!=1"));
		
		assertEquals(true, runTest("1<=1"));
		assertEquals(false, runTest("2<=1"));
		
		assertEquals(true, runTest("1>=1"));
		assertEquals(false, runTest("1>=2"));
	}
	
	@Test
	public void testAst2() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("2>1 and 1=1"));
		assertEquals(false, runTest("2>1 and 2=1"));
		
		assertEquals(true, runTest("2>1 or 1>1"));
		assertEquals(false, runTest("2<1 or 2=1"));
	}
	
	@Test
	public void testAst3() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("2>1 and (1=1)"));
		assertEquals(false, runTest("2>1 and (2=1)"));
		
		assertEquals(true, runTest("(2>1) and 1=1"));
		assertEquals(false, runTest("(2>1) and 2=1"));
	}
	
	@Test
	public void testAst4() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("2>1 and 1=1 and 3>2"));
		assertEquals(false, runTest("2>1 and 2=1 and 3>2"));
		
		assertEquals(true, runTest("2>1 and 1=1 and 3>2 and 4>3"));
		assertEquals(false, runTest("2>1 and 2=1 and 3>2 and 4>3"));
		
		assertEquals(true, runTest("2>1 and 1=1 and 3>2 and 4>3 and 5>4"));
		assertEquals(false, runTest("2>1 and 2=1 and 3>2 and 4>3 and 5>4"));
	}
	
	@Test
	public void testAst5() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("2>1 and (1=1 and 3>2)"));
		assertEquals(false, runTest("2>1 and (2=1 and 3>2)"));
		
		assertEquals(true, runTest("2>1 and (1=1 and 3>2 and 4>3)"));
		assertEquals(false, runTest("2>1 and (2=1 and 3>2 and 4>3)"));
		
		assertEquals(true, runTest("2>1 and (1=1 and 3>2 and 4>3 and 5>4)"));
		assertEquals(false, runTest("2>1 and (2=1 and 3>2 and 4>3 and 5>4)"));
	}

	@Test
	public void testAst6() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("2>1 and 1=1 or 3>2"));
		assertEquals(true, runTest("2>1 and 2=1 or 3>2"));
		
		assertEquals(true, runTest("2>1 and 1=1 or 3>2 and 4>3"));
		assertEquals(true, runTest("2>1 and 2=1 or 3>2 and 4>3"));
		
		assertEquals(true, runTest("2>1 and 1=1 or 3>2 and 4>3 and 5>4"));
		assertEquals(true, runTest("2>1 and 2=1 or 3>2 and 4>3 and 5>4"));
	}
	
	@Test
	public void testAst7() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("2>1 and (1=1 or 3>2)"));
		assertEquals(true, runTest("2>1 and (2=1 or 3>2)"));
		
		assertEquals(true, runTest("2>1 and (1=1 or 3>2) and 4>3"));
		assertEquals(true, runTest("2>1 and (2=1 or 3>2) and 4>3"));
		
		assertEquals(false, runTest("2>1 and (1=1 or 3>2) and 4<3 and 5>4"));
		assertEquals(false, runTest("2>1 and (2=1 or 3>2) and 4<3 and 5>4"));
	}
	
	@Test
	public void testAst8() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("2+3*5>16 and 1=1"));
		assertEquals(true, runTest("2+3*5<18 and 1=1"));
		
		assertEquals(true, runTest("(2+3)*5>24 and 1=1"));
		assertEquals(true, runTest("(2+3)*5<26 and 1=1"));
	}
	
	@Test
	public void testAst9() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("(2+3)*5>24 and (1=1 or 3>2)"));
		assertEquals(true, runTest("(2+3)*5>24 and (2=1 or 3>2)"));
		
		assertEquals(true, runTest("(2+3)*5>24 and (1=1 or 3>2) and 4>3"));
		assertEquals(true, runTest("(2+3)*5>24 and (2=1 or 3>2) and 4>3"));
		
		assertEquals(false, runTest("(2+3)*5>24 and (1=1 or 3>2) and 4<3 and 5>4"));
		assertEquals(false, runTest("(2+3)*5>24 and (2=1 or 3>2) and 4<3 and 5>4"));
	}
	
	@Test
	public void testAst10() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("2.2>2.1 and 1=1"));
		assertEquals(false, runTest("2.2>2.1 and 2=1"));
		
		assertEquals(true, runTest("2.2>2.1 or 1>1"));
		assertEquals(true, runTest("2.2>2.1 or 2=1"));
	}
	
	@Test
	public void testAst11() throws RecognitionException, TokenStreamException{
		assertEquals(true, runTest("b>a and 1=1"));
		assertEquals(false, runTest("b>a and 2=1"));
		
		assertEquals(true, runTest("b>a or 1>1"));
		assertEquals(true, runTest("b>a or 2=1"));
	}
	
	private boolean runTest(String str) throws RecognitionException,
			TokenStreamException {
		StringReader reader = new StringReader(str);
		BoolLexer lexer = new BoolLexer(reader);
		BoolParser parser = new BoolParser(lexer);
		parser.expression();
		AST t = parser.getAST();
		System.out.println(t.toStringTree());
		BoolTreeParser treeParser = new BoolTreeParser();
		boolean b = treeParser.expression(t);
		System.out.println(treeParser.expression(t));
		return b;
	}
}
