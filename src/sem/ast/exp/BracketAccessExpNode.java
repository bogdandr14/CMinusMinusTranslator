package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.Type;
import sem.token.IntLitTokenVal;

public class BracketAccessExpNode extends ExpNode {
	private IdNode myLoc;
	private IntLitTokenVal myPos;
	private boolean badAccess;
	
	public BracketAccessExpNode(IdNode loc, IntLitTokenVal pos) {
		myLoc = loc;
		myPos = pos;
	}

	public int lineNum() {
		return myLoc.lineNum();
	}

	public int charNum() {
		return myLoc.charNum();
	}

	public Type typeCheck() {
		return myLoc.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		myLoc.unparse(p, 0);
		p.print("["+ myPos.getVal() + "]");
	}
}