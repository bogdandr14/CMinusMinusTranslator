package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.ASTnode;
import sem.ast.decl.DeclListNode;
import sem.ast.type.Type;

public class FnBodyNode extends ASTnode {
	public FnBodyNode(DeclListNode declList, StmtListNode stmtList) {
		myDeclList = declList;
		myStmtList = stmtList;
	}

	/**
	 * typeCheck
	 */
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

	// 2 kids
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;
}
