// $ANTLR 2.7.7 (20060906): "src/test/resources/expr_ast05.g" -> "ExprTreeParser.java"$

package com.bulain.antlr.expr.ast;

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


public class ExprTreeParser extends antlr.TreeParser       implements ExprTreeParserTokenTypes
 {
public ExprTreeParser() {
	tokenNames = _tokenNames;
}

	public final BigDecimal  expr(AST _t) throws RecognitionException {
		BigDecimal r=new BigDecimal(0);
		
		AST expr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST n = null;
		AST s = null;
		BigDecimal a,b;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PLUS:
			{
				AST __t12 = _t;
				AST tmp1_AST_in = (AST)_t;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				a=expr(_t);
				_t = _retTree;
				b=expr(_t);
				_t = _retTree;
				_t = __t12;
				_t = _t.getNextSibling();
				r = a.add(b);
				break;
			}
			case MINUS:
			{
				AST __t13 = _t;
				AST tmp2_AST_in = (AST)_t;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				a=expr(_t);
				_t = _retTree;
				b=expr(_t);
				_t = _retTree;
				_t = __t13;
				_t = _t.getNextSibling();
				r = a.subtract(b);
				break;
			}
			case STAR:
			{
				AST __t14 = _t;
				AST tmp3_AST_in = (AST)_t;
				match(_t,STAR);
				_t = _t.getFirstChild();
				a=expr(_t);
				_t = _retTree;
				b=expr(_t);
				_t = _retTree;
				_t = __t14;
				_t = _t.getNextSibling();
				r = a.multiply(b);
				break;
			}
			case DIV:
			{
				AST __t15 = _t;
				AST tmp4_AST_in = (AST)_t;
				match(_t,DIV);
				_t = _t.getFirstChild();
				a=expr(_t);
				_t = _retTree;
				b=expr(_t);
				_t = _retTree;
				_t = __t15;
				_t = _t.getNextSibling();
				r = a.divide(b);
				break;
			}
			case UNARY_MINUS:
			{
				AST __t16 = _t;
				AST tmp5_AST_in = (AST)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				a=expr(_t);
				_t = _retTree;
				_t = __t16;
				_t = _t.getNextSibling();
				r = r.subtract(a);
				break;
			}
			case UNARY_PLUS:
			{
				AST __t17 = _t;
				AST tmp6_AST_in = (AST)_t;
				match(_t,UNARY_PLUS);
				_t = _t.getFirstChild();
				a=expr(_t);
				_t = _retTree;
				_t = __t17;
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
		"UNARY_MINUS",
		"UNARY_PLUS",
		"PLUS",
		"MINUS",
		"STAR",
		"DIV",
		"NUM",
		"ID",
		"LPAREN",
		"RPAREN"
	};
	
	}
	
