package sem.ast.stmt;

import sem.ast.ASTnode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public abstract class StmtNode extends ASTnode {
	
	abstract public void nameAnalysis(SymTable symTab);
	
	abstract public void typeCheck(Type retType);
}
