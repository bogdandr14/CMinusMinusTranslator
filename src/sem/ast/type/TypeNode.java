package sem.ast.type;

import sem.ast.ASTnode;

public abstract class TypeNode extends ASTnode {
	/* all subclasses must provide a type method */
	abstract public Type type();
}