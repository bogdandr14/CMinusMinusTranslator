package sem.ast.exp;

import sem.ast.ASTnode;
import sem.ast.type.Type;

abstract public class ExpNode extends ASTnode {
	abstract public Type typeCheck();

	abstract public int lineNum();

	abstract public int charNum();
}
