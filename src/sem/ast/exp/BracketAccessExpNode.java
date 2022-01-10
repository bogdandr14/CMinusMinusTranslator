package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.Type;
import sem.token.IntLitTokenVal;

public class BracketAccessExpNode extends ExpNode {
	public BracketAccessExpNode(IdNode loc, IntLitTokenVal pos) {
		myLoc = loc;
		myPos = pos;
	}

	/**
	 * Return the line number for this dot-access node. The line number is the one
	 * corresponding to the RHS of the bracket-access.
	 */
	public int lineNum() {
		return myLoc.lineNum();
	}

	/**
	 * Return the char number for this dot-access node. The char number is the one
	 * corresponding to the RHS of the bracket-access.
	 */
	public int charNum() {
		return myLoc.charNum();
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		return myLoc.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		myLoc.unparse(p, 0);
		p.print("["+ myPos.getVal() + "]");
	}

	// 2 kids
	private IdNode myLoc;
	private IntLitTokenVal myPos;
	private boolean badAccess; // to prevent multiple, cascading errors
}