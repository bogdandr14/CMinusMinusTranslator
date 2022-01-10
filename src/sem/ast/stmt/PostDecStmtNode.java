package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.exp.ExpNode;
import sem.ast.type.Type;

public class PostDecStmtNode extends StmtNode {
	public PostDecStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (!type.isErrorType() && !type.isIntType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Arithmetic operator applied to non-numeric operand");
//		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myExp.unparse(p, 0);
		p.println("--;");
	}

	// 1 kid
	private ExpNode myExp;
}
