package sem.ast.decl;

import java.io.PrintWriter;

import sem.ast.exp.IdNode;
import sem.ast.type.TypeNode;

public class FormalDeclNode extends DeclNode {
	public FormalDeclNode(TypeNode type, IdNode id) {
		myType = type;
		myId = id;
	}

	public void unparse(PrintWriter p, int indent) {
		myType.unparse(p, 0);
		p.print(" ");
		p.print(myId.name());
	}

	// 2 kids
	private TypeNode myType;
	private IdNode myId;
}
