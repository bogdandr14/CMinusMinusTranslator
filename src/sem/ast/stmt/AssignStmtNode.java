package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.exp.AssignNode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class AssignStmtNode extends StmtNode {
	private AssignNode myAssign;
	
	public AssignStmtNode(AssignNode assign) {
		myAssign = assign;
	}

	public void nameAnalysis(SymTable symTab) {
        myAssign.nameAnalysis(symTab);
    }
	
	public void typeCheck(Type retType) {
		myAssign.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myAssign.unparse(p, -1);
		p.println(";");
	}
}
