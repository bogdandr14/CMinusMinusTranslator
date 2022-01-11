package sem.ast.exp;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import sem.ErrMsg;
import sem.ast.ASTnode;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class ExpListNode extends ASTnode {
	public ExpListNode(List<ExpNode> S) {
		myExps = S;
	}

	public int size() {
		return myExps.size();
	}
	
	public void nameAnalysis(SymTable symTab) {
        for (ExpNode node : myExps) {
            node.nameAnalysis(symTab);
        }
    }

	public void typeCheck(List<Type> typeList) {
		int k = 0;
		try {
			for (ExpNode node : myExps) {
				Type actualType = node.typeCheck(); // actual type of arg

				if (!actualType.isErrorType()) { // if this is not an error
					Type formalType = typeList.get(k); // get the formal type
					if (!formalType.equals(actualType)) {
						ErrMsg.fatal(node.lineNum(), node.charNum(), "Type of actual does not match type of formal");
					}
				}
				k++;
			}
		} catch (NoSuchElementException e) {
			System.err.println("unexpected NoSuchElementException in ExpListNode.typeCheck");
			System.exit(-1);
		}
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<ExpNode> it = myExps.iterator();
		if (it.hasNext()) { // if there is at least one element
			it.next().unparse(p, indent);
			while (it.hasNext()) { // print the rest of the list
				p.print(", ");
				it.next().unparse(p, indent);
			}
		}
	}

	// list of kids (ExpNodes)
	private List<ExpNode> myExps;
}