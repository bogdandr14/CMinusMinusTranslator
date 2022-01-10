package cup;
import java.io.*;
import java_cup.runtime.*;
class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int line, int ch) {
        linenum = line;
        charnum = ch;
    }
    
    public int getLine() {
    	return linenum;
    }
    
    public int getCol() {
    	return charnum;
    }
    public Object getVal(){
    	return null;
    }
}

class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int line, int ch, int val) {
        super(line, ch);
        intVal = val;
    }
    public Object getVal(){
    	return intVal;
    }
}

class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int line, int ch, String val) {
        super(line, ch);
    idVal = val;
    }
    
    public Object getVal(){
    	return idVal;
    }
}

class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int line, int ch, String val) {
        super(line, ch);
        strVal = val;
    }
    
    public Object getVal(){
    	return strVal;
    }
}

// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
    static int num=1;
}
%%

%public
%unicode
%class Lexer
%cup
%implements sym

%line
%column

%{
   StringBuffer stringBuffer = new StringBuffer();
	  
	private Symbol symbol(int sym) {
		if(sym == ID){
			return new Symbol(ID, new IdTokenVal(yyline + 1, yycolumn + 1, yytext()));
		}
		if(sym == INTLITERAL){
			return new Symbol(INTLITERAL, new IntLitTokenVal(yyline + 1, yycolumn + 1, Integer.parseInt(yytext())));
		}
		if(sym == STRINGLITERAL){
			return new Symbol(STRINGLITERAL, new StrLitTokenVal(yyline + 1, yycolumn + 1, yytext()));
		}
		return new Symbol(sym, new TokenVal(yyline + 1, yycolumn + 1));
	}

	private void error(String message) {
	   System.out.println("Error at line " + (yyline + 1) + ", column " + (yycolumn + 1) + " : "+ message);
	}
%} 

LineEnd = \r | \n | \r\n
Character = [^\r\n]
WhiteSpace = {LineEnd} | [ \t\v\f]
 
LineComment = "//" {Character}* {LineEnd}
LineCommentHash = "#" {Character}* {LineEnd}
CStyleComment = "/*" ~"*/" 
Comment = {LineComment} | {LineCommentHash} | {CStyleComment}


Identifier = [_a-zA-Z][_a-zA-Z0-9]*

Number =  [0] | [1-9][0-9]*

StringCharacter = [^\n\r\"\\]

String = "\""({StringCharacter}|(\\\')|(\\\")|(\\t)|(\\n)|(\\\\))*"\""
ErrorFormat = ({Number} {Identifier}) | ({Number} {String})

 
%%
<YYINITIAL> {
   
   {Comment} { /* Ingnore */ }
   {ErrorFormat}	{ System.out.println("ERROR");error(yytext()); }
   
   /* Arithmetic Operations */
   "+"   { return symbol(PLUS); }
   "-"   { return symbol(MINUS); }
   "*"   { return symbol(MULTIPLY); }
   "/"   { return symbol(DIVIDE); }
   "<<"  { return symbol(WRITE); }
   ">>"  { return symbol(READ); }

   /* Logical operations */
   "<"   { return symbol(LESS); }
   ">"   { return symbol(GREATER); }
   "<="  { return symbol(LESSEQ); }
   ">="  { return symbol(GREATEREQ); }
   "&&"  { return symbol(AND); }
   "||"  { return symbol(OR); }
   "=="  { return symbol(EQUAL); }
   "!="  { return symbol(NOTEQ); }

   "="   { return symbol(ASSIGN); }
   "!"   { return symbol(NOT); }
   ","   { return symbol(COMMA); }
   ";"   { return symbol(SEMICOLON); }

   /* Parenthesis */
   "("   { return symbol(LPAREN); }
   ")"   { return symbol(RPAREN); }
   "["   { return symbol(LBRACKET); }
   "]"   { return symbol(RBRACKET); }
   "{"   { return symbol(LCURLY); }
   "}"   { return symbol(RCURLY); }
   
   /* Keywords */
   "bool"      { return symbol(BOOL); }
   "int"       { return symbol(INT); }
   "void"      { return symbol(VOID); }
   "true"      { return symbol(TRUE); }
   "false"     { return symbol(FALSE); }
   "if"        { return symbol(IF); }
   "else"      { return symbol(ELSE); }
   "while"     { return symbol(WHILE); }
   "return"    { return symbol(RETURN); }
   "cin"	   { return symbol (CIN); }
   "cout"      { return symbol (COUT); }
   
   {String}			 { return symbol(STRINGLITERAL); }  
   {Identifier}      { return symbol(ID); }
   {Number} 		 { return symbol(INTLITERAL); }
   {WhiteSpace}      { /* Ignore */ }
   <<EOF>>     	   	 { return symbol(EOF); } 
   .|\n              { System.out.println("ERROR");error(yytext()); }
 }

 