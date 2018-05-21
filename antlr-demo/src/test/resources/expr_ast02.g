header
{
package com.bulain.antlr.expr.ast;
}

class ExprParser extends Parser;
options{
    k=2;
    buildAST=true;
}
expr: mexpr (PLUS^ mexpr | MINUS^ mexpr)* ;
mexpr: atom (STAR^ atom | DIV^ atom)* ;
atom: NUM | ID | LPAREN! expr RPAREN! ;

class ExprLexer extends Lexer;
options{
    k=2;
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
//-- Column --
ID : ID_START_CHAR ( ID_CHAR )*;
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
WS    :(' '| '\r''\n' | '\n' | '\t'){$setType(Token.SKIP);};
