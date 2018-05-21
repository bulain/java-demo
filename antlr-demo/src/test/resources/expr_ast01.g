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
atom: NUM | LPAREN! expr RPAREN! ;

class ExprTreeParser extends TreeParser;
options{
    importVocab=ExprParser;
}
expr returns [double r=0]{double a,b;}
    : #(PLUS a=expr b=expr) {r = a+b;}
    | #(MINUS a=expr b=expr) {r = a-b;}
    | #(STAR a=expr b=expr) {r = a*b;}
    | #(DIV a=expr b=expr) {r = a/b;}
    | i:NUM {r = (double)Double.parseDouble(i.getText());}
    ;

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
WS    :(' '| '\r''\n' | '\n' | '\t'){$setType(Token.SKIP);};
