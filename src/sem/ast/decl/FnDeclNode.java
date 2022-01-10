package sem.ast.decl;

import java.io.PrintWriter;

import sem.ast.exp.IdNode;
import sem.ast.stmt.FnBodyNode;
import sem.ast.type.TypeNode;

public class FnDeclNode extends DeclNode {
	public FnDeclNode(TypeNode type, IdNode id, FormalsListNode formalList, FnBodyNode body) {
		myType = type;
		myId = id;
		myFormalsList = formalList;
		myBody = body;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck() {
		myBody.typeCheck(myType.type());
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myType.unparse(p, 0);
		p.print(" ");
		p.print(myId.name());
		p.print("(");
		myFormalsList.unparse(p, 0);
		p.println(") {");
		myBody.unparse(p, indent + 4);
		p.println("}\n");
	}

	// 4 kids
	private TypeNode myType;
	private IdNode myId;
	private FormalsListNode myFormalsList;
	private FnBodyNode myBody;
}