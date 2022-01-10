package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.exp.CallExpNode;
import sem.ast.type.Type;

public class CallStmtNode extends StmtNode {
	public CallStmtNode(CallExpNode call) {
		myCall = call;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		myCall.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myCall.unparse(p, indent);
		p.println(";");
	}

	// 1 kid
	private CallExpNode myCall;
}
