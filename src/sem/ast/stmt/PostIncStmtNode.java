package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.exp.ExpNode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class PostIncStmtNode extends StmtNode {
	public PostIncStmtNode(ExpNode exp) {
		myExp = exp;
	}

	public void nameAnalysis(SymTable symTab) {
        myExp.nameAnalysis(symTab);
    }
	
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

		if (!type.isErrorType() && !type.isIntType()) {
			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Arithmetic operator applied to non-numeric operand");
		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myExp.unparse(p, 0);
		p.println("++;");
	}

	// 1 kid
	private ExpNode myExp;
}