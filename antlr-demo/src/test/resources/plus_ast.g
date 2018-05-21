class PlusParser extends Parser;
options {
    buildAST=true;
}
expr : INT PLUS^ INT;

class PlusTreeParser extends TreeParser;
expr returns [int value = 0] 
   : #(PLUS a : INT b : INT) {
       int aValue = Integer.parseInt(a.getText());
       int bValue = Integer.parseInt(b.getText());
       value = aValue + bValue;
   };

class PlusLexer extends Lexer;
PLUS : '+' ;
INT  : ('0'..'9')+ ;