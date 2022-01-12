package sem.ast.decl;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sem.ast.ASTnode;
import sem.ast.type.Type;
import sem.symb.SemSym;
import sem.symb.SymTable;

public class FormalsListNode extends ASTnode {
	private List<FormalDeclNode> myFormals;
	
	public FormalsListNode(List<FormalDeclNode> S) {
		myFormals = S;
	}

	public List<Type> nameAnalysis(SymTable symTab) {
        List<Type> typeList = new LinkedList<Type>();
        for (FormalDeclNode node : myFormals) {
            SemSym sym = node.nameAnalysis(symTab);
            if (sym != null) {
                typeList.add(sym.getType());
            }
        }
        return typeList;
    }
	
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
}