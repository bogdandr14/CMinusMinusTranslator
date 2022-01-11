package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.BoolType;
import sem.ast.type.Type;

public class FalseNode extends ExpNode {
	public FalseNode(int lineNum, int charNum) {
		myLineNum = lineNum;
		myCharNum = charNum;
	}

	public int lineNum() {
		return myLineNum;
	}

	public int charNum() {
		return myCharNum;
	}

	public Type typeCheck() {
		return new BoolType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("false");
	}

	private int myLineNum;
	private int myCharNum;
}
