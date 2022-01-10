package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.exp.AssignNode;
import sem.ast.type.Type;

public class AssignStmtNode extends StmtNode {
	public AssignStmtNode(AssignNode assign) {
		myAssign = assign;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		myAssign.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myAssign.unparse(p, -1); // no parentheses
		p.println(";");
	}

	// 1 kid
	private AssignNode myAssign;
}
