package sem.ast.decl;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import sem.ast.ASTnode;

public class FormalsListNode extends ASTnode {
	public FormalsListNode(List<FormalDeclNode> S) {
		myFormals = S;
	}

	/**
	 * Return the number of formals in this list.
	 */
	public int length() {
		return myFormals.size();
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<FormalDeclNode> it = myFormals.iterator();
		if (it.hasNext()) { // if there is at least one element
			it.next().unparse(p, indent);
			while (it.hasNext()) { // print the rest of the list
				p.print(", ");
				it.next().unparse(p, indent);
			}
		}
	}

	// list of kids (FormalDeclNodes)
	private List<FormalDeclNode> myFormals;
}