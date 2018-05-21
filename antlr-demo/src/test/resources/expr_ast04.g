header
{
package com.bulain.antlr.expr.ast;

import java.math.*;
}

//***************ExprParser***************************
class ExprParser extends Parser;
options{
    k=2;
    buildAST=true;
}
tokens{
	UNARY_MINUS;
	UNARY_PLUS;
}

expr : mexpr ((PLUS^ | MINUS^) mexpr)* ;
mexpr: uexpr ((STAR^ | DIV^) uexpr)* ;
uexpr: MINUS^ {#MINUS.setType(UNARY_MINUS);} uexpr
     | PLUS^ {#PLUS.setType(UNARY_PLUS);} uexpr
   	 | atom 
     ;
atom : constant | LPAREN! expr RPAREN! ;
constant
    : NUM
    | ID
    ;

//***************ExprTreeParser***************************
class ExprTreeParser extends TreeParser;
options{
    importVocab=ExprParser;
}
expr returns [BigDecimal r=new BigDecimal(0)]{BigDecimal a,b;}
    : #(PLUS a=expr b=expr) {r = a.add(b);}
    | #(MINUS a=expr b=expr) {r = a.subtract(b);}
    | #(STAR a=expr b=expr) {r = a.multiply(b);}
    | #(DIV a=expr b=expr) {r = a.divide(b);}
    | #(UNARY_MINUS a=expr) {r = r.subtract(a);}
    | #(UNARY_PLUS a=expr) {r = r.add(a);}
    | n:NUM {r = new BigDecimal(n.getText());}
    | s:ID {r = (BigDecimal)Context.get(s.getText());}
    ;

//***************ExprLexer***************************
class ExprLexer extends Lexer;
options{
    k=2;
    testLiterals = false;
    charVocabulary='\u0000'..'\u007F';
    caseSensitive = false;
    caseSensitiveLiterals = false;
}
LPAREN:'(';
RPAREN:')';
PLUS  :'+';
MINUS :'-';
STAR  :'*';
DIV   :'/';
NUM   : NUM_CHAR (NUM_CHAR)* ('.' (NUM_CHAR)*)? ;
protected
NUM_CHAR
      : '0'..'9' ;
ID options { testLiterals=true; }
      : ID_START_CHAR ( ID_CHAR )*;
protected
ID_START_CHAR
      : '_'
      | 'a'..'z'
      | '\u0080'..'\ufffe'
      ;
protected
ID_CHAR
      : ID_START_CHAR
      | '0'..'9'
      ;
WS    : (   ' '
        |   '\t'
        |   '\r' '\n' { newline(); }
        |   '\n'      { newline(); }
        |   '\r'      { newline(); }
        )
        {$setType(Token.SKIP);}
    ;
