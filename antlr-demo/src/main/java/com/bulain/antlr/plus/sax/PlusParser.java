package com.bulain.antlr.plus.sax;
// $ANTLR 2.7.7 (20060906): "src/test/resources/plus_sax.g" -> "PlusParser.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class PlusParser extends antlr.LLkParser       implements PlusParserTokenTypes
 {

protected PlusParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public PlusParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected PlusParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public PlusParser(TokenStream lexer) {
  this(lexer,1);
}

public PlusParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final int  expr() throws RecognitionException, TokenStreamException {
		int value=0;
		
		Token  a = null;
		Token  b = null;
		
		try {      // for error handling
			a = LT(1);
			match(INT);
			match(PLUS);
			b = LT(1);
			match(INT);
			
			int aValue = Integer.parseInt(a.getText());
			int bValue = Integer.parseInt(b.getText());
			value = aValue + bValue;
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
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
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	
	}
