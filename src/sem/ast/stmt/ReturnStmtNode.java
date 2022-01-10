package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.exp.ExpNode;
import sem.ast.type.Type;

public class ReturnStmtNode extends StmtNode {
	public ReturnStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
//		if (myExp != null) { // return value given
//			Type type = myExp.typeCheck();
//
//			if (retType.isVoidType()) {
//				ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Return with a value in a void function");
//			}
//
//			else if (!retType.isErrorType() && !type.isErrorType() && !retType.equals(type)) {
//				ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Bad return value");
//			}
//		}
//
//		else { // no return value given -- ok if this is a void function
//			if (!retType.isVoidType()) {
//				ErrMsg.fatal(0, 0, "Missing return value");
//			}
//		}

	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("return");
		if (myExp != null) {
			p.print(" ");
			myExp.unparse(p, 0);
		}
		p.println(";");
	}

	// 1 kid
	private ExpNode myExp; // possibly null
}
