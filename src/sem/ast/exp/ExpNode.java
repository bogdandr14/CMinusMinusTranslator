package sem.ast.exp;

import sem.ast.ASTnode;
import sem.ast.type.Type;
import sem.symb.SymTable;

abstract public class ExpNode extends ASTnode {
	
	public void nameAnalysis(SymTable symTab) { }
	
	abstract public Type typeCheck();

	abstract public int lineNum();

	abstract public int charNum();
}
