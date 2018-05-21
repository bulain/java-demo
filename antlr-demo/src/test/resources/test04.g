header
{
package com.bulain.antlr.bool.ast;

import java.math.*;
import com.bulain.antlr.util.*;

}

// **** PARSER ******************************************************************
class BoolParser extends Parser;

options{
    buildAST=true;
    k=3;    // For 'not like', 'not in', etc.
}


tokens{
    // -- HQL Keyword tokens --
    AND="and";
    BETWEEN="between";
    DOT;
    FALSE="false";
    IN="in";
    IS="is";
    LIKE="like";
    NOT="not";
    NULL="null";
    OR="or";
    TRUE="true";

    // -- Synthetic token types --
    IN_LIST;
    IS_NOT_NULL;
    IS_NULL;            // Unary 'is null' operator.
    NOT_BETWEEN;
    NOT_IN;
    NOT_LIKE;
    UNARY_MINUS;
    UNARY_PLUS;

    // Literal tokens.
    CONSTANT;
}

{
    /**
     * Returns the negated equivalent of the expression.
     * @param x The expression to negate.
     */
    public AST negateNode(AST x) {
        // Just create a 'not' parent for the default behavior.
        return ASTUtil.createParent(astFactory, NOT, "not", x);
    }

}

// expressions
// Note that most of these expressions follow the pattern
//   thisLevelExpression :
//       nextHigherPrecedenceExpression
//           (OPERATOR nextHigherPrecedenceExpression)*
// which is a standard recursive definition for a parsing an expression.
//
// Operator precedence in HQL
// lowest  --> ( 7)  OR
//             ( 6)  AND, NOT
//             ( 5)  equality: ==, <>, !=, is
//             ( 4)  relational: <, <=, >, >=,
//                   LIKE, NOT LIKE, BETWEEN, NOT BETWEEN, IN, NOT IN
//             ( 3)  addition and subtraction: +(binary) -(binary)
//             ( 2)  multiplication: * / %, concatenate: ||
// highest --> ( 1)  +(unary) -(unary)
//
// Note that the above precedence levels map to the rules below...
// Once you have a precedence chart, writing the appropriate rules as below
// is usually very straightfoward

logicalExpression
    : expression
    ;
    
// Main expression rule
expression
    : logicalOrExpression
    ;
    
// level 7 - OR
logicalOrExpression
    : logicalAndExpression ( OR^ logicalAndExpression )*
    ;
    

// level 6 - AND, NOT
logicalAndExpression
    : negatedExpression ( AND^ negatedExpression )*
    ;
    
// NOT nodes aren't generated.  Instead, the operator in the sub-tree will be
// negated, if possible.   Expressions without a NOT parent are passed through.
negatedExpression!
    : NOT^ x:negatedExpression { #negatedExpression = negateNode(#x); }
    | y:equalityExpression { #negatedExpression = #y; }
    ;
    

//## OP: EQ | LT | GT | LE | GE | NE | SQL_NE | LIKE;

// level 5 - EQ, NE
equalityExpression
    : x:relationalExpression (
        ( EQ^
        | is:IS^    { #is.setType(EQ); } (NOT! { #is.setType(NE); } )?
        | NE^
        | ne:SQL_NE^    { #ne.setType(NE); }
        ) y:relationalExpression)* 
    ;
    

// level 4 - LT, GT, LE, GE, LIKE, NOT LIKE, BETWEEN, NOT BETWEEN
// NOTE: The NOT prefix for LIKE and BETWEEN will be represented in the
// token type.  When traversing the AST, use the token type, and not the
// token text to interpret the semantics of these nodes.
relationalExpression
    : concatenation (
        ( ( ( LT^ | GT^ | LE^ | GE^ ) additiveExpression )* )
        // Disable node production for the optional 'not'.
        | (n:NOT!)? (
            // Represent the optional NOT prefix using the token type by
            // testing 'n' and setting the token type accordingly.
            (i:IN^ {
                    #i.setType( (n == null) ? IN : NOT_IN);
                    #i.setText( (n == null) ? "in" : "not in");
                }
                inList)
            | (b:BETWEEN^ {
                    #b.setType( (n == null) ? BETWEEN : NOT_BETWEEN);
                    #b.setText( (n == null) ? "between" : "not between");
                }
                betweenList )
            | (l:LIKE^ {
                    #l.setType( (n == null) ? LIKE : NOT_LIKE);
                    #l.setText( (n == null) ? "like" : "not like");
                }
                concatenation)
             )
        )
    ;

inList
    : x:compoundExpr
    { #inList = #([IN_LIST,"inList"], #inList); }
    ;

betweenList
    : concatenation AND! concatenation
    ;

//level 4 - string concatenation
concatenation
    : additiveExpression 
    ;

// level 3 - binary plus and minus
additiveExpression
    : multiplyExpression ( ( PLUS^ | MINUS^ ) multiplyExpression )*
    ;

// level 2 - binary multiply and divide
multiplyExpression
    : unaryExpression ( ( STAR^ | DIV^ | MOD^ ) unaryExpression )*
    ;

// level 1 - unary minus, unary plus, not
unaryExpression
    : MINUS^ {#MINUS.setType(UNARY_MINUS);} unaryExpression
    | PLUS^ {#PLUS.setType(UNARY_PLUS);} unaryExpression
    | atom
    ;


// level 0 - expression atom
atom
    :   identifier 
    |   constant
    | OPEN! expression CLOSE!
    ;

// compoundExpr (x,y,z)
compoundExpr
    : (OPEN!  (constant (COMMA! constant)*)  CLOSE!)
    ;

constant
    : NUM
    | QUOTED_STRING
    | NULL
    | TRUE
    | FALSE
    ;


//## path: identifier
path
    : identifier
    ;

// Wraps the ID token from the lexer, in order to provide
// 'keyword as identifier' trickery.
identifier
    : ID ;

//***************TreeParser***************************
class BoolTreeParser extends TreeParser;
options{
    importVocab=BoolParser;
}

expression returns [boolean r = false] {boolean a, b;}
	: #(AND a=expression b=expression) {r = a && b;}
	| #(OR a=expression b=expression) {r = a || b;}
	| #(NOT a=expression) {r = !a;}
	| (a=booleanExpr) {r=a;}
	;

booleanExpr returns [boolean r = false] {BigDecimal a, b;}
    : #(EQ a=simpleExpr b=simpleExpr) {r = a.compareTo(b)==0;}
	| #(NE a=simpleExpr b=simpleExpr) {r = a.compareTo(b)!=0;}
	| #(GT a=simpleExpr b=simpleExpr) {r = a.compareTo(b)>0;}
	| #(GE a=simpleExpr b=simpleExpr) {r = a.compareTo(b)>=0;}
	| #(LT a=simpleExpr b=simpleExpr) {r = a.compareTo(b)<=0;}
	| #(LE a=simpleExpr b=simpleExpr) {r = a.compareTo(b)<=0;}
    ;

simpleExpr returns [BigDecimal r = new BigDecimal(0)]{BigDecimal a,b;}
    : #(PLUS a=simpleExpr b=simpleExpr) {r = a.add(b);}
    | #(MINUS a=simpleExpr b=simpleExpr) {r = a.subtract(b);}
    | #(STAR a=simpleExpr b=simpleExpr) {r = a.multiply(b);}
    | #(DIV a=simpleExpr b=simpleExpr) {r = a.divide(b);}
    | #(UNARY_MINUS a=simpleExpr) {r = r.subtract(a);}
    | #(UNARY_PLUS a=simpleExpr) {r = r.add(a);}
    | n:NUM {r = new BigDecimal(n.getText());}
    | s:ID {r = (BigDecimal)Context.get(s.getText());}
    ;

// **** LEXER ******************************************************************
class BoolLexer extends Lexer;

options {
    testLiterals = false;
    k=2; // needed for newline, and to distinguish '>' from '>='.
    // HHH-241 : Quoted strings don't allow unicode chars - This should fix it.
    charVocabulary='\u0000'..'\uFFFE';  // Allow any char but \uFFFF (16 bit -1, ANTLR's EOF character)
    caseSensitive = false;
    caseSensitiveLiterals = false;
}

// -- Keywords --

EQ: '=';
LT: '<';
GT: '>';
SQL_NE: "<>";
NE: "!=" | "^=";
LE: "<=";
GE: ">=";

COMMA: ',';

OPEN: '(';
CLOSE: ')';

PLUS: '+';
MINUS: '-';
STAR: '*';
DIV: '/';
MOD: '%';

ID options { testLiterals=true; }
    : ID_START_LETTER ( ID_LETTER )*
    ;

protected
ID_START_LETTER
    :    '_'
    |    '$'
    |    'a'..'z'
    |    '\u0080'..'\ufffe'       // HHH-558 : Allow unicode chars in identifiers
    ;

protected
ID_LETTER
    :    ID_START_LETTER
    |    '0'..'9'
    ;

QUOTED_STRING
      : '\'' ( (ESCqs)=> ESCqs | ~'\'' )* '\''
    ;

protected
ESCqs
    :
        '\'' '\''
    ;

NUM   : NUM_CHAR (NUM_CHAR)* ('.' (NUM_CHAR)*)? ;

protected
NUM_CHAR
      : '0'..'9' ;
      
WS  :   (   ' '
        |   '\t'
        |   '\r' '\n' { newline(); }
        |   '\n'      { newline(); }
        |   '\r'      { newline(); }
        )
        {$setType(Token.SKIP);} //ignore this token
    ;

