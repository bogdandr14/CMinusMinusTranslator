package sem.ast.exp;

import sem.symb.SymTable;

public abstract class BinaryExpNode extends ExpNode {
	protected ExpNode myExp1;
	protected ExpNode myExp2;
	
	public BinaryExpNode(ExpNode exp1, ExpNode exp2) {
		myExp1 = exp1;
		myExp2 = exp2;
	}

	public int lineNum() {
		return myExp1.lineNum();
	}

	public int charNum() {
		return myExp1.charNum();
	}
	
	public void nameAnalysis(SymTable symTab) {
        myExp1.nameAnalysis(symTab);
        myExp2.nameAnalysis(symTab);
    }
}
