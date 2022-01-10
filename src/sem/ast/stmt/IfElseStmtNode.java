package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.decl.DeclListNode;
import sem.ast.exp.ExpNode;
import sem.ast.type.Type;

public class IfElseStmtNode extends StmtNode {
	public IfElseStmtNode(ExpNode exp, DeclListNode dlist1, StmtListNode slist1, DeclListNode dlist2,
			StmtListNode slist2) {
		myExp = exp;
		myThenDeclList = dlist1;
		myThenStmtList = slist1;
		myElseDeclList = dlist2;
		myElseStmtList = slist2;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (!type.isErrorType() && !type.isBoolType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Non-bool expression used as an if condition");
//		}

		myThenStmtList.typeCheck(retType);
		myElseStmtList.typeCheck(retType);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("if (");
		myExp.unparse(p, 0);
		p.println(") {");
		myThenDeclList.unparse(p, indent + 4);
		myThenStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
		doIndent(p, indent);
		p.println("else {");
		myElseDeclList.unparse(p, indent + 4);
		myElseStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// 5 kids
	private ExpNode myExp;
	private DeclListNode myThenDeclList;
	private StmtListNode myThenStmtList;
	private StmtListNode myElseStmtList;
	private DeclListNode myElseDeclList;
}