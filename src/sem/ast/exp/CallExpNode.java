package sem.ast.exp;

import java.io.PrintWriter;
import java.util.LinkedList;

import sem.ErrMsg;
import sem.ast.type.ErrorType;
import sem.ast.type.Type;
import sem.symb.FnSym;
import sem.symb.SymTable;

public class CallExpNode extends ExpNode {
	private IdNode myId;
	private ExpListNode myExpList;
	private SymTable fnSymTab;
	
	public CallExpNode(IdNode name, ExpListNode elist) {
		myId = name;
		myExpList = elist;
	}

	public CallExpNode(IdNode name) {
		myId = name;
		myExpList = new ExpListNode(new LinkedList<ExpNode>());
	}

	public int lineNum() {
		return myId.lineNum();
	}

	public int charNum() {
		return myId.charNum();
	}
	
	public void nameAnalysis(SymTable symTab) {
        myId.nameAnalysis(symTab);
        myExpList.nameAnalysis(symTab);
    }

	public Type typeCheck() {
		if (!myId.typeCheck().isFnType()) {
			ErrMsg.fatal(myId.lineNum(), myId.charNum(), "Attempt to call a non-function");
			return new ErrorType();
		}

        FnSym fnSym = (FnSym)(myId.sym());

        if (fnSym == null) {
        	 System.err.println("null sym for Id in CallExpNode.typeCheck");
             System.exit(-1);
        }

        if (myExpList.size() != fnSym.getNumParams()) {
            ErrMsg.fatal(myId.lineNum(), myId.charNum(),
                         "Function call with wrong number of args");
        }
        return fnSym.getReturnType();
	}

	public void unparse(PrintWriter p, int indent) {
		myId.unparse(p, 0);
		p.print("(");
		if (myExpList != null) {
			myExpList.unparse(p, 0);
		}
		p.print(")");
	}
}
