package sem.ast.stmt;

import java.io.PrintWriter;

import sem.ast.decl.DeclListNode;
import sem.ast.exp.ExpNode;
import sem.ast.type.Type;
import sem.symb.EmptySymTableException;
import sem.symb.SymTable;

public class WhileStmtNode extends StmtNode {
	public WhileStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
		myExp = exp;
		myDeclList = dlist;
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

//		if (!type.isErrorType() && !type.isBoolType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Non-bool expression used as a while condition");
//		}

		myStmtList.typeCheck(retType);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("while (");
		myExp.unparse(p, 0);
		p.println(") {");
		myDeclList.unparse(p, indent + 4);
		myStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// 3 kids
	private ExpNode myExp;
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;

}

