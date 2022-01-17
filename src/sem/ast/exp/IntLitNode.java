package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.IntType;
import sem.ast.type.Type;

public class IntLitNode extends ExpNode {
	private int myLineNum;
	private int myCharNum;
	private int myIntVal;
	
	public IntLitNode(int lineNum, int charNum, int intVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myIntVal = intVal;
	}

	public int lineNum() {
		return myLineNum;
	}

	public int charNum() {
		return myCharNum;
	}
	
	public int getIntVal() {
		return myIntVal;
	}

	public Type typeCheck() {
		return new IntType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myIntVal);
	}
}
