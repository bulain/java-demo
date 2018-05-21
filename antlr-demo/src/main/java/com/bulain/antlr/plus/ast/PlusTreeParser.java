package com.bulain.antlr.plus.ast;
// $ANTLR 2.7.7 (20060906): "src/test/resources/plus_ast.g" -> "PlusTreeParser.java"$

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


public class PlusTreeParser extends antlr.TreeParser       implements PlusParserTokenTypes
 {
public PlusTreeParser() {
	tokenNames = _tokenNames;
}

	public final int  expr(AST _t) throws RecognitionException {
		int value = 0;
		
		AST expr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		AST b = null;
		
		try {      // for error handling
			AST __t7 = _t;
			AST tmp4_AST_in = (AST)_t;
			match(_t,PLUS);
			_t = _t.getFirstChild();
			a = (AST)_t;
			match(_t,INT);
			_t = _t.getNextSibling();
			b = (AST)_t;
			match(_t,INT);
			_t = _t.getNextSibling();
			_t = __t7;
			_t = _t.getNextSibling();
			
			int aValue = Integer.parseInt(a.getText());
			int bValue = Integer.parseInt(b.getText());
			value = aValue + bValue;
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return value;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"INT",
		"PLUS"
	};
	
	}
	
