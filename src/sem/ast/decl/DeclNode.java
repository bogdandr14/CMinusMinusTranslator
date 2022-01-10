package sem.ast.decl;

import sem.ast.ASTnode;

public abstract class DeclNode extends ASTnode {

	// default version of typeCheck for non-function decls
	public void typeCheck() {
	}
}
