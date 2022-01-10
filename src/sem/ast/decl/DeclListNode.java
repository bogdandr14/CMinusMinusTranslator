package sem.ast.decl;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import sem.ast.ASTnode;

public class DeclListNode extends ASTnode {
	public DeclListNode(List<DeclNode> S) {
		myDecls = S;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck() {
		for (DeclNode node : myDecls) {
			node.typeCheck();
		}
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator it = myDecls.iterator();
		try {
			while (it.hasNext()) {
				((DeclNode) it.next()).unparse(p, indent);
			}
		} catch (NoSuchElementException ex) {
			System.err.println("unexpected NoSuchElementException in DeclListNode.print");
			System.exit(-1);
		}
	}

	// How many declared variables do we have //
	public int numDecl() {
		return myDecls.size();
	}

	// list of kids (DeclNodes)
	private List<DeclNode> myDecls;
}