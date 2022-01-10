package sem.ast.stmt;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import sem.ast.ASTnode;
import sem.ast.type.Type;

public class StmtListNode extends ASTnode {
	public StmtListNode(List<StmtNode> S) {
		myStmts = S;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		for (StmtNode node : myStmts) {
			node.typeCheck(retType);
		}
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<StmtNode> it = myStmts.iterator();
		while (it.hasNext()) {
			it.next().unparse(p, indent);
		}
	}

	// list of kids (StmtNodes)
	private List<StmtNode> myStmts;
}
