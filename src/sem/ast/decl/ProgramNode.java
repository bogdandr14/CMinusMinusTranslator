package sem.ast.decl;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.ASTnode;
import sem.symb.SymTable;

public class ProgramNode extends ASTnode {
	private DeclListNode myDeclList;
	
	public ProgramNode(DeclListNode dl) {
		myDeclList = dl;
	}

    public void nameAnalysis() {
        SymTable symTab = new SymTable();
        myDeclList.nameAnalysis(symTab);

        if(symTab.lookupGlobal("main") == null) {
          ErrMsg.fatal(0, 0, "No main function");
        }
    }
    
	public void typeCheck() {
		myDeclList.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		myDeclList.unparse(p, indent);
	}
}

