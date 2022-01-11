package sem.ast.exp;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.type.Type;
import sem.symb.SemSym;
import sem.symb.SymTable;

public class IdNode extends ExpNode {
	public IdNode(int lineNum, int charNum, String strVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myStrVal = strVal;
	}
	
	public void link(SemSym sym) {
        mySym = sym;
    }

	public SemSym sym() {
        return mySym;
    }
	
	public String name() {
		return myStrVal;
	}

	public int lineNum() {
		return myLineNum;
	}

	public int charNum() {
		return myCharNum;
	}
	
	public void nameAnalysis(SymTable symTab) {
        SemSym sym = symTab.lookupGlobal(myStrVal);
        if (sym == null) {
            ErrMsg.fatal(myLineNum, myCharNum, "Undeclared identifier");
        } else {
            link(sym);
        }
    }

	public Type typeCheck() {
        if (mySym != null) {
            return mySym.getType();
        }
        else {
            System.err.println("ID with null sym field in IdNode.typeCheck at"+ myLineNum + " " + myCharNum + " " + myStrVal);
            System.exit(-1);
        }
		return null;
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myStrVal);
	}

	private int myLineNum;
	private int myCharNum;
	private String myStrVal;
	private SemSym mySym;
}
