package jflex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java_cup.runtime.Symbol;

public class main {
	static HashMap<Integer, String> tokenClass = new HashMap<Integer, String> (); 
	
	public static void setSymbolClass(int startVal, int endVal, String className) {
		for(int s = startVal; s <= endVal; ++s) {
			tokenClass.put(s, className);
		}
	}
	
	public static void initHash()
	{
		setSymbolClass(sym.PLUS, sym.NOT, cls.OPERATOR);
		setSymbolClass(sym.COMMA, sym.SEMICOLON, cls.SPECIAL);
		setSymbolClass(sym.LPAREN, sym.RBRACKET, cls.PARANTHESIS);
		setSymbolClass(sym.INT, sym.COUT, cls.KEYWORD);

		tokenClass.put(sym.STRINGLITERAL, cls.STRING);
		tokenClass.put(sym.ID, cls.IDENTIFIER);
		tokenClass.put(sym.INTLITERAL, cls.CONSTANT);
	} 	

	public static void main (String[] args) {
		
		main.initHash();
		
		FileReader inputFile;
		try {
		    String basePath = new File("").getAbsolutePath();
			inputFile = new FileReader(basePath + "\\input.txt");
			BufferedReader br = new BufferedReader(inputFile);
			Lexer l = new Lexer (br);
			
			try {
				Symbol sCrt;
				do 
				{
					sCrt = l.next_token();
										
					if (sCrt.sym != sym.EOF)
					{
						System.out.println("Symbol value: "+ l.yytext() + " Class: " + main.tokenClass.get(sCrt.sym) + " line: " + sCrt.left + " column: " + sCrt.right);
					}
				}while(sCrt.sym != sym.EOF);
				System.out.println("EOF");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

