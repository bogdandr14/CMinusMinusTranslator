package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.exp.ExpNode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class ReadStmtNode extends StmtNode {
	public ReadStmtNode(ExpNode e) {
		myExp = e;
	}

	public void nameAnalysis(SymTable symTab) {
        myExp.nameAnalysis(symTab);
    }
	
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (type.isFnType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to read a function");
//		}
//
//		if (type.isArrayDefType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to read a array index");
//		}
//
//		if (type.isArrayType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to read a array variable");
//		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("cin >> ");
		myExp.unparse(p, 0);
		p.println(";");
	}

	// 1 kid (actually can only be an IdNode or an ArrayExpNode)
	private ExpNode myExp;
}