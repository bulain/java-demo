class ExprParser extends Parser;
expr returns [int value=0]{int x;}
    : value=mexpr
    ( PLUS x=mexpr {value += x;}
    | MINSU x=mexpr {value -= x;}
    )*
;
mexpr returns [int value=0]{int x;}
    : value=atom 
    ( STAR x=atom {value *= x;}
    )*
    ;
atom returns [int value=0]
    : i:INT {value=Integer.parseInt(i.getText());}
    | LPAREN value=expr RPAREN
    ;


class ExprLexer extends Lexer;
options{
    charVocabulary='\u0000'..'\u007F';
}
LPAREN:'(';
RPAREN:')';
PLUS  :'+';
MINUS :'-';
STAR  :'*';
INT   :('0'..'9')+;
WS    :(' '| '\r''\n' | '\n' | '\t'){$setType(Token.SKIP);};