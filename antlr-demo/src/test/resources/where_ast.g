header
{
package com.bulain.antlr.where.ast;
}

class WhereParser extends Parser;
options{
    k=2;
    buildAST=true;
}
tokens
{
    AND="and";
    OR="or";
    IN="in";
    NOT="not";
    IS="is";
    NULL="null";
}

expr  : expr_or ;
expr_or
      : expr_and (OR^ expr_and)* ;
expr_and
      : expr_not (AND^ expr_not)* ;
expr_not
      :(expr_bool | NOT^ expr_bool) ;
expr_bool
      :(atom EQ^ atom 
      | atom LT^ atom
      | atom GT^ atom
      | atom SQL_NE^ atom
      | atom NE^ atom
      | atom LE^ atom
      | atom GE^ atom
      | atom (NOT)? IN^ atom_in
      | atom IS^ (NOT)? NULL
      | OPEN! expr CLOSE!
      );
atom_in : OPEN! (NUM | QUOTED_STR) (COMMA^ (NUM | QUOTED_STR))* CLOSE! ;
atom : ( IDENT | NUM | QUOTED_STR ) ;

/*
op_p : op_m (PLUS^ op_m | MINUS^ op_m)* ;
op_m : atom_num (STAR^ atom_num | DIV^ atom_num | MOD^ atom_num)* ;
atom_num: ( IDENT | NUM) ;
      
op_str 
      : atom_str (CONCAT (IDENT | QUOTED_STR))*;
atom_str: (IDENT | QUOTED_STR) ;
*/

class WhereLexer extends Lexer;
options{
    k=2;
    charVocabulary='\u0000'..'\u007F';
    caseSensitive = false;
    caseSensitiveLiterals = false;
}
//-- Keywords --
EQ: '=';
LT: '<';
GT: '>';
SQL_NE: "<>";
NE: "!=";
LE: "<=";
GE: ">=";
OPEN: '(';
CLOSE: ')';

//-- Column --
IDENT : ID_START_LETTER ( ID_LETTER )*;

protected
ID_START_LETTER
      : '_'
      | 'a'..'z'
      | '\u0080'..'\ufffe'
      ;

protected
ID_LETTER
      : ID_START_LETTER
      | '0'..'9'
      ;
  
//-- Quoted string --
QUOTED_STR
      : '\'' ( (ESCqs)=> ESCqs | ~'\'' )* '\''
      ;

protected
ESCqs : '\'' '\''
      ;
    
//-- Number --
NUM   : ('+'|'-')? NUM_CHAR (NUM_CHAR)* (DOT (NUM_CHAR)*)? ;

protected
NUM_CHAR
      : '0'..'9' ;

//-- op --
/*
CONCAT: "||";
PLUS: '+';
MINUS: '-';
STAR: '*';
DIV: '/';
MOD: '%';
*/

//-- CONSTANTS --
DOT: '.';
COMMA: ',';

//-- White space --
WS    :(' '
      | '\t'
      | '\r' '\n' { newline(); }
      | '\n'      { newline(); }
      | '\r'      { newline(); }
      )
      {$setType(Token.SKIP);}
      ;
      