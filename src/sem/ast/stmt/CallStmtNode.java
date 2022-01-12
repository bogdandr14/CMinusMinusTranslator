package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.exp.CallExpNode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class CallStmtNode extends StmtNode {
	private CallExpNode myCall;
	
	public CallStmtNode(CallExpNode call) {
		myCall = call;
	}

	public void nameAnalysis(SymTable symTab) {
        myCall.nameAnalysis(symTab);
    }
	
	public void typeCheck(Type retType) {
		myCall.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myCall.unparse(p, indent);
		p.println(";");
	}
}
