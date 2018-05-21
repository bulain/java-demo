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
atom: INT | LPAREN! expr RPAREN! ;

class ExprTreeParser extends TreeParser;
options{
    importVocab=ExprParser;
}
expr returns [int r=0]{int a,b;}
    : #(PLUS a=expr b=expr) {r = a+b;}
    | #(MINUS a=expr b=expr) {r = a-b;}
    | #(STAR a=expr b=expr) {r = a*b;}
    | #(DIV a=expr b=expr) {r = a/b;}
    | i:INT {r = (int)Integer.parseInt(i.getText());}
    ;

class ExprLexer extends Lexer;
options{
    k=2;
    charVocabulary='\u0000'..'\u007F';
}
LPAREN:'(';
RPAREN:')';
PLUS  :'+';
MINUS :'-';
STAR  :'*';
DIV   :'/';
INT   :('0'..'9')+;
WS    :(' '| '\r''\n' | '\n' | '\t'){$setType(Token.SKIP);};
