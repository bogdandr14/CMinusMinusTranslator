package sem.ast.exp;

import sem.symb.SymTable;

public abstract class UnaryExpNode extends ExpNode {
	protected ExpNode myExp;
	
	public UnaryExpNode(ExpNode exp) {
		myExp = exp;
	}

	public int lineNum() {
		return myExp.lineNum();
	}

	public int charNum() {
		return myExp.charNum();
	}
	
	public void nameAnalysis(SymTable symTab) {
        myExp.nameAnalysis(symTab);
    }
}
