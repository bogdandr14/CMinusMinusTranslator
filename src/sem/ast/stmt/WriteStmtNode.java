package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.exp.ExpNode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class WriteStmtNode extends StmtNode {
	private ExpNode myExp;
	
	public WriteStmtNode(ExpNode exp) {
		myExp = exp;
	}

	public void nameAnalysis(SymTable symTab) {
        myExp.nameAnalysis(symTab);
    }
	
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

		if (type.isFnType()) {
			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write a function");
		}

		if (type.isArrayType()) {
			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write an array variable");
		}

		if (type.isVoidType()) {
			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write void");
		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("cout << ");
		myExp.unparse(p, 0);
		p.println(";");
	}
}