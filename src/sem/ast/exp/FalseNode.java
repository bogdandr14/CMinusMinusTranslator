package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.BoolType;
import sem.ast.type.Type;

public class FalseNode extends ExpNode {
	public FalseNode(int lineNum, int charNum) {
		myLineNum = lineNum;
		myCharNum = charNum;
	}

	/**
	 * Return the line number for this literal.
	 */
	public int lineNum() {
		return myLineNum;
	}

	/**
	 * Return the char number for this literal.
	 */
	public int charNum() {
		return myCharNum;
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		return new BoolType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("false");
	}

	private int myLineNum;
	private int myCharNum;
}
