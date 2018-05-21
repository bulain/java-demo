class PlusParser extends Parser;
expr returns [int value=0]
   :   a : INT PLUS b : INT {
       int aValue = Integer.parseInt(a.getText());
       int bValue = Integer.parseInt(b.getText());
       value = aValue + bValue;
   };

class PlusLexer extends Lexer;
PLUS   : '+' ;
INT    : ('0'..'9')+ ;