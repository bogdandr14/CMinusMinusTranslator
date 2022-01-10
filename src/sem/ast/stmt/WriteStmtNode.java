package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.exp.ExpNode;
import sem.ast.type.Type;

public class WriteStmtNode extends StmtNode {
	public WriteStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (type.isFnType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write a function");
//		}
//
//		if (type.isArrayDefType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write an array name");
//		}
//
//		if (type.isArrayType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write an array variable");
//		}
//
//		if (type.isVoidType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write void");
//		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("cout << ");
		myExp.unparse(p, 0);
		p.println(";");
	}

	// 1 kid
	private ExpNode myExp;
}