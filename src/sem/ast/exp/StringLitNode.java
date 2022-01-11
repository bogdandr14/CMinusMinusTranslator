package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.StringType;
import sem.ast.type.Type;

public class StringLitNode extends ExpNode {
	public StringLitNode(int lineNum, int charNum, String strVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myStrVal = strVal;
	}

	public int lineNum() {
		return myLineNum;
	}

	public int charNum() {
		return myCharNum;
	}

	public Type typeCheck() {
		return new StringType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myStrVal);
	}

	private int myLineNum;
	private int myCharNum;
	private String myStrVal;
}