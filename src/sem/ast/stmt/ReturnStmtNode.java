package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.exp.ExpNode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class ReturnStmtNode extends StmtNode {
	private ExpNode myExp;

	public ReturnStmtNode(ExpNode exp) {
		myExp = exp;
	}

	public void nameAnalysis(SymTable symTab) {
		if (myExp != null) {
			myExp.nameAnalysis(symTab);
		}
	}

	public void typeCheck(Type retType) {
		if (myExp != null) { // return value given
			Type type = myExp.typeCheck();

			if (retType.isVoidType()) {
				ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Return with a value in a void function");
			}

			else if (type != null) {
				if (!retType.isErrorType() && !type.isErrorType() && !retType.equals(type)) {
					ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Bad return value");
				}
			}
		}

		else { // no return value given -- ok if this is a void function
			if (!retType.isVoidType()) {
				ErrMsg.fatal(0, 0, "Missing return value");
			}
		}

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
}
