package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.exp.ExpNode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class ReadStmtNode extends StmtNode {
	private ExpNode myExp;
	
	public ReadStmtNode(ExpNode e) {
		myExp = e;
	}

	public void nameAnalysis(SymTable symTab) {
        myExp.nameAnalysis(symTab);
    }
	
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

		if (type.isFnType()) {
			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to read a function");
		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("cin >> ");
		myExp.unparse(p, 0);
		p.println(";");
	}
}