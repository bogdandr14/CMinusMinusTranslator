package sem.ast.decl;

import sem.ast.ASTnode;
import sem.symb.SemSym;
import sem.symb.SymTable;

public abstract class DeclNode extends ASTnode {
	
	abstract public SemSym nameAnalysis(SymTable symTab);

	// default version of typeCheck for non-function decls
	public void typeCheck() {
	}
}
