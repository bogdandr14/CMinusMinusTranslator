package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.BoolType;
import sem.ast.type.Type;

public class TrueNode extends ExpNode {
	private int myLineNum;
	private int myCharNum;
	
	public TrueNode(int lineNum, int charNum) {
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
		p.print("true");
	}
}