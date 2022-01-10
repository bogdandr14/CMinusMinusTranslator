package sem.ast.decl;

import java.io.PrintWriter;

import sem.ast.exp.IdNode;
import sem.ast.type.TypeNode;

public class VarDeclNode extends DeclNode {
	public VarDeclNode(TypeNode type, IdNode id, int size) {
		myType = type;
		myId = id;
		mySize = size;
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myType.unparse(p, 0);
		p.print(" ");
		p.print(myId.name());
		p.println(";");
	}

	// 3 kids
	private TypeNode myType;
	private IdNode myId;
	private int mySize; // use value NOT_ARRAY if this is not an array type

	public static int NOT_ARRAY = -1;
}
