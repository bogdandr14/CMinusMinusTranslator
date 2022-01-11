package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.decl.DeclListNode;
import sem.ast.exp.ExpNode;
import sem.ast.type.Type;
import sem.symb.EmptySymTableException;
import sem.symb.SymTable;

public class IfStmtNode extends StmtNode {
	public IfStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
		myDeclList = dlist;
		myExp = exp;
		myStmtList = slist;
	}

	public void nameAnalysis(SymTable symTab) {
        myExp.nameAnalysis(symTab);
        symTab.addScope();
        myDeclList.nameAnalysis(symTab);
        myStmtList.nameAnalysis(symTab);
        try {
            symTab.removeScope();
        } catch (EmptySymTableException ex) {
            System.err.println("Unexpected EmptySymTableException " +
                               " in IfStmtNode.nameAnalysis");
            System.exit(-1);
        }
    }
	
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

		if (!type.isErrorType() && !type.isBoolType()) {
			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Non-bool expression used as an if condition");
		}

		myStmtList.typeCheck(retType);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("if (");
		myExp.unparse(p, 0);
		p.println(") {");
		myDeclList.unparse(p, indent + 4);
		myStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// e kids
	private ExpNode myExp;
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;
}