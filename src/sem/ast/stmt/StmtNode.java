package sem.ast.stmt;

import sem.ast.ASTnode;
import sem.ast.type.Type;

public abstract class StmtNode extends ASTnode {
	abstract public void typeCheck(Type retType);
}
