package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.ASTnode;
import sem.ast.decl.DeclListNode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class FnBodyNode extends ASTnode {
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;
	
	public FnBodyNode(DeclListNode declList, StmtListNode stmtList) {
		myDeclList = declList;
		myStmtList = stmtList;
	}

	public void nameAnalysis(SymTable symTab) {
        myDeclList.nameAnalysis(symTab);
        myStmtList.nameAnalysis(symTab);
    }
	
	public void typeCheck(Type retType) {
		myStmtList.typeCheck(retType);
	}

	public void unparse(PrintWriter p, int indent) {
		myDeclList.unparse(p, indent);
		myStmtList.unparse(p, indent);
	}

	public int numLocals() {
		return myDeclList.numDecl();
	}
}
