// $ANTLR 2.7.7 (20060906): "src/test/resources/where_ast.g" -> "WhereParser.java"$

package com.bulain.antlr.where.ast;

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
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class WhereParser extends antlr.LLkParser       implements WhereParserTokenTypes
 {

protected WhereParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public WhereParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected WhereParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public WhereParser(TokenStream lexer) {
  this(lexer,2);
}

public WhereParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_AST = null;
		
		try {      // for error handling
			expr_or();
			astFactory.addASTChild(currentAST, returnAST);
			expr_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = expr_AST;
	}
	
	public final void expr_or() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_or_AST = null;
		
		try {      // for error handling
			expr_and();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop4:
			do {
				if ((LA(1)==OR)) {
					AST tmp1_AST = null;
					tmp1_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp1_AST);
					match(OR);
					expr_and();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			expr_or_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = expr_or_AST;
	}
	
	public final void expr_and() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_and_AST = null;
		
		try {      // for error handling
			expr_not();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop7:
			do {
				if ((LA(1)==AND)) {
					AST tmp2_AST = null;
					tmp2_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp2_AST);
					match(AND);
					expr_not();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop7;
				}
				
			} while (true);
			}
			expr_and_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = expr_and_AST;
	}
	
	public final void expr_not() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_not_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case OPEN:
			case NUM:
			case QUOTED_STR:
			case IDENT:
			{
				expr_bool();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case NOT:
			{
				AST tmp3_AST = null;
				tmp3_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp3_AST);
				match(NOT);
				expr_bool();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expr_not_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		returnAST = expr_not_AST;
	}
	
	public final void expr_bool() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_bool_AST = null;
		
		try {      // for error handling
			{
			if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==EQ)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp4_AST = null;
				tmp4_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp4_AST);
				match(EQ);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==LT)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp5_AST = null;
				tmp5_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp5_AST);
				match(LT);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==GT)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp6_AST = null;
				tmp6_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp6_AST);
				match(GT);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==SQL_NE)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp7_AST = null;
				tmp7_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp7_AST);
				match(SQL_NE);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==NE)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp8_AST = null;
				tmp8_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp8_AST);
				match(NE);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==LE)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp9_AST = null;
				tmp9_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp9_AST);
				match(LE);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==GE)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp10_AST = null;
				tmp10_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp10_AST);
				match(GE);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==IN||LA(2)==NOT)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case NOT:
				{
					AST tmp11_AST = null;
					tmp11_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp11_AST);
					match(NOT);
					break;
				}
				case IN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp12_AST = null;
				tmp12_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp12_AST);
				match(IN);
				atom_in();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==NUM||LA(1)==QUOTED_STR||LA(1)==IDENT) && (LA(2)==IS)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp13_AST = null;
				tmp13_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp13_AST);
				match(IS);
				{
				switch ( LA(1)) {
				case NOT:
				{
					AST tmp14_AST = null;
					tmp14_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp14_AST);
					match(NOT);
					break;
				}
				case NULL:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp15_AST = null;
				tmp15_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp15_AST);
				match(NULL);
			}
			else if ((LA(1)==OPEN)) {
				match(OPEN);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				match(CLOSE);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			expr_bool_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		returnAST = expr_bool_AST;
	}
	
	public final void atom() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST atom_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENT:
			{
				AST tmp18_AST = null;
				tmp18_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp18_AST);
				match(IDENT);
				break;
			}
			case NUM:
			{
				AST tmp19_AST = null;
				tmp19_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp19_AST);
				match(NUM);
				break;
			}
			case QUOTED_STR:
			{
				AST tmp20_AST = null;
				tmp20_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp20_AST);
				match(QUOTED_STR);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			atom_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
		returnAST = atom_AST;
	}
	
	public final void atom_in() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST atom_in_AST = null;
		
		try {      // for error handling
			match(OPEN);
			{
			switch ( LA(1)) {
			case NUM:
			{
				AST tmp22_AST = null;
				tmp22_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp22_AST);
				match(NUM);
				break;
			}
			case QUOTED_STR:
			{
				AST tmp23_AST = null;
				tmp23_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp23_AST);
				match(QUOTED_STR);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop18:
			do {
				if ((LA(1)==COMMA)) {
					AST tmp24_AST = null;
					tmp24_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp24_AST);
					match(COMMA);
					{
					switch ( LA(1)) {
					case NUM:
					{
						AST tmp25_AST = null;
						tmp25_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp25_AST);
						match(NUM);
						break;
					}
					case QUOTED_STR:
					{
						AST tmp26_AST = null;
						tmp26_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp26_AST);
						match(QUOTED_STR);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else {
					break _loop18;
				}
				
			} while (true);
			}
			match(CLOSE);
			atom_in_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		returnAST = atom_in_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"and\"",
		"\"or\"",
		"\"in\"",
		"\"not\"",
		"\"is\"",
		"\"null\"",
		"EQ",
		"LT",
		"GT",
		"SQL_NE",
		"NE",
		"LE",
		"GE",
		"OPEN",
		"CLOSE",
		"NUM",
		"QUOTED_STR",
		"COMMA",
		"IDENT",
		"ID_START_LETTER",
		"ID_LETTER",
		"ESCqs",
		"NUM_CHAR",
		"DOT",
		"WS"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 262144L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 262176L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 262192L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 392688L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
