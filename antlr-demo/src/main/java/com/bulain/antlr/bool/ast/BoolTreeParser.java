// $ANTLR 2.7.7 (20060906): "src/test/resources/test04.g" -> "BoolTreeParser.java"$

package com.bulain.antlr.bool.ast;

import java.math.*;
import com.bulain.antlr.util.*;


import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;


public class BoolTreeParser extends antlr.TreeParser       implements BoolTreeParserTokenTypes
 {
public BoolTreeParser() {
	tokenNames = _tokenNames;
}

	public final boolean  expression(AST _t) throws RecognitionException {
		boolean r = false;
		
		AST expression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		boolean a, b;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case AND:
			{
				AST __t48 = _t;
				AST tmp1_AST_in = (AST)_t;
				match(_t,AND);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				_t = __t48;
				_t = _t.getNextSibling();
				r = a && b;
				break;
			}
			case OR:
			{
				AST __t49 = _t;
				AST tmp2_AST_in = (AST)_t;
				match(_t,OR);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				_t = __t49;
				_t = _t.getNextSibling();
				r = a || b;
				break;
			}
			case NOT:
			{
				AST __t50 = _t;
				AST tmp3_AST_in = (AST)_t;
				match(_t,NOT);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				_t = __t50;
				_t = _t.getNextSibling();
				r = !a;
				break;
			}
			case EQ:
			case NE:
			case LT:
			case GT:
			case LE:
			case GE:
			{
				{
				a=booleanExpr(_t);
				_t = _retTree;
				}
				r=a;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return r;
	}
	
	public final boolean  booleanExpr(AST _t) throws RecognitionException {
		boolean r = false;
		
		AST booleanExpr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		BigDecimal a, b;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case EQ:
			{
				AST __t53 = _t;
				AST tmp4_AST_in = (AST)_t;
				match(_t,EQ);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t53;
				_t = _t.getNextSibling();
				r = a.compareTo(b)==0;
				break;
			}
			case NE:
			{
				AST __t54 = _t;
				AST tmp5_AST_in = (AST)_t;
				match(_t,NE);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t54;
				_t = _t.getNextSibling();
				r = a.compareTo(b)!=0;
				break;
			}
			case GT:
			{
				AST __t55 = _t;
				AST tmp6_AST_in = (AST)_t;
				match(_t,GT);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t55;
				_t = _t.getNextSibling();
				r = a.compareTo(b)>0;
				break;
			}
			case GE:
			{
				AST __t56 = _t;
				AST tmp7_AST_in = (AST)_t;
				match(_t,GE);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t56;
				_t = _t.getNextSibling();
				r = a.compareTo(b)>=0;
				break;
			}
			case LT:
			{
				AST __t57 = _t;
				AST tmp8_AST_in = (AST)_t;
				match(_t,LT);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t57;
				_t = _t.getNextSibling();
				r = a.compareTo(b)<=0;
				break;
			}
			case LE:
			{
				AST __t58 = _t;
				AST tmp9_AST_in = (AST)_t;
				match(_t,LE);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t58;
				_t = _t.getNextSibling();
				r = a.compareTo(b)<=0;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return r;
	}
	
	public final BigDecimal  simpleExpr(AST _t) throws RecognitionException {
		BigDecimal r = new BigDecimal(0);
		
		AST simpleExpr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST n = null;
		AST s = null;
		BigDecimal a,b;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PLUS:
			{
				AST __t60 = _t;
				AST tmp10_AST_in = (AST)_t;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t60;
				_t = _t.getNextSibling();
				r = a.add(b);
				break;
			}
			case MINUS:
			{
				AST __t61 = _t;
				AST tmp11_AST_in = (AST)_t;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t61;
				_t = _t.getNextSibling();
				r = a.subtract(b);
				break;
			}
			case STAR:
			{
				AST __t62 = _t;
				AST tmp12_AST_in = (AST)_t;
				match(_t,STAR);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t62;
				_t = _t.getNextSibling();
				r = a.multiply(b);
				break;
			}
			case DIV:
			{
				AST __t63 = _t;
				AST tmp13_AST_in = (AST)_t;
				match(_t,DIV);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				b=simpleExpr(_t);
				_t = _retTree;
				_t = __t63;
				_t = _t.getNextSibling();
				r = a.divide(b);
				break;
			}
			case UNARY_MINUS:
			{
				AST __t64 = _t;
				AST tmp14_AST_in = (AST)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				_t = __t64;
				_t = _t.getNextSibling();
				r = r.subtract(a);
				break;
			}
			case UNARY_PLUS:
			{
				AST __t65 = _t;
				AST tmp15_AST_in = (AST)_t;
				match(_t,UNARY_PLUS);
				_t = _t.getFirstChild();
				a=simpleExpr(_t);
				_t = _retTree;
				_t = __t65;
				_t = _t.getNextSibling();
				r = r.add(a);
				break;
			}
			case NUM:
			{
				n = (AST)_t;
				match(_t,NUM);
				_t = _t.getNextSibling();
				r = new BigDecimal(n.getText());
				break;
			}
			case ID:
			{
				s = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				r = (BigDecimal)Context.get(s.getText());
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return r;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"and\"",
		"\"between\"",
		"DOT",
		"\"false\"",
		"\"in\"",
		"\"is\"",
		"\"like\"",
		"\"not\"",
		"\"null\"",
		"\"or\"",
		"\"true\"",
		"IN_LIST",
		"IS_NOT_NULL",
		"IS_NULL",
		"NOT_BETWEEN",
		"NOT_IN",
		"NOT_LIKE",
		"UNARY_MINUS",
		"UNARY_PLUS",
		"CONSTANT",
		"EQ",
		"NE",
		"SQL_NE",
		"LT",
		"GT",
		"LE",
		"GE",
		"PLUS",
		"MINUS",
		"STAR",
		"DIV",
		"MOD",
		"OPEN",
		"CLOSE",
		"COMMA",
		"NUM",
		"QUOTED_STRING",
		"ID"
	};
	
	}
	
