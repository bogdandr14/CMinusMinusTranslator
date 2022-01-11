package sem.ast.decl;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import sem.ast.ASTnode;
import sem.symb.SymTable;

public class DeclListNode extends ASTnode {
	public DeclListNode(List<DeclNode> S) {
		myDecls = S;
	}

	public void nameAnalysis(SymTable symTab) {
        nameAnalysis(symTab, symTab);
    }

    public void nameAnalysis(SymTable symTab, SymTable globalTab) {
        for (DeclNode node : myDecls) {
            if (node instanceof VarDeclNode) {
                ((VarDeclNode)node).nameAnalysis(symTab, globalTab);
            } else {
                node.nameAnalysis(symTab);
            }
        }
    }
	
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

	public int numDecl() {
		return myDecls.size();
	}

	// list of kids (DeclNodes)
	private List<DeclNode> myDecls;
}