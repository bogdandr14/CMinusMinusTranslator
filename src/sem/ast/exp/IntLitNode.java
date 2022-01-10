package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.IntType;
import sem.ast.type.Type;

public class IntLitNode extends ExpNode {
	public IntLitNode(int lineNum, int charNum, int intVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myIntVal = intVal;
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
		return new IntType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myIntVal);
	}

	private int myLineNum;
	private int myCharNum;
	private int myIntVal;
}
