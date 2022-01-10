package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.Type;

public class IdNode extends ExpNode {
	public IdNode(int lineNum, int charNum, String strVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myStrVal = strVal;
	}

	/**
	 * Return the name of this ID.
	 */
	public String name() {
		return myStrVal;
	}

	/**
	 * Return the line number for this ID.
	 */
	public int lineNum() {
		return myLineNum;
	}

	/**
	 * Return the char number for this ID.
	 */
	public int charNum() {
		return myCharNum;
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
//        if (mySym != null) {
//            return mySym.getType();
//        }
//        else {
//            System.err.println("ID with null sym field in IdNode.typeCheck");
//            System.exit(-1);
//        }
		return null;
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myStrVal);
	}

	private int myLineNum;
	private int myCharNum;
	private String myStrVal;
}
