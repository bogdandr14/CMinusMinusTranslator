package sem.ast.decl;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.exp.IdNode;
import sem.ast.type.ArrayNode;
import sem.ast.type.TypeNode;
import sem.ast.type.VoidNode;
import sem.symb.ArraySym;
import sem.symb.DuplicateSymException;
import sem.symb.EmptySymTableException;
import sem.symb.SemSym;
import sem.symb.SymTable;

public class VarDeclNode extends DeclNode {
	public VarDeclNode(TypeNode type, IdNode id, int size) {
		myType = type;
		myId = id;
		mySize = size;
	}

	public SemSym nameAnalysis(SymTable symTab) {
		return nameAnalysis(symTab, symTab);
	}

	public SemSym nameAnalysis(SymTable symTab, SymTable globalTab) {
		boolean badDecl = false;
		String name = myId.name();
		SemSym sym = null;

		if (myType instanceof VoidNode) { // check for void type
			ErrMsg.fatal(myId.lineNum(), myId.charNum(), "Non-function declared void");
			badDecl = true;
		}
		if (symTab.lookupLocal(name) != null) {
			ErrMsg.fatal(myId.lineNum(), myId.charNum(), "Multiply declared identifier");
			badDecl = true;
		}

		if (!badDecl) { // insert into symbol table
			try {
				if (myType instanceof ArrayNode) {
					sym = new ArraySym(myType, ((ArrayNode) myType).getSize());
				} else {
					sym = new SemSym(myType.type());
					// set offset //
					sym.setOffset(symTab.size() - 1);
				}
				symTab.addDecl(name, sym);
				myId.link(sym);
			} catch (DuplicateSymException ex) {
				System.err.println("Unexpected DuplicateSymException " + " in VarDeclNode.nameAnalysis");
				System.exit(-1);
			} catch (EmptySymTableException ex) {
				System.err.println("Unexpected EmptySymTableException " + " in VarDeclNode.nameAnalysis");
				System.exit(-1);
			}
		}

		return sym;
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myType.unparse(p, 0);
		p.print(" ");
		p.print(myId.name());
		if (myType instanceof ArrayNode) {
			p.print("[" + ((ArrayNode) myType).getSize() + "]");
		}
		p.println(";");
	}

	// 3 kids
	private TypeNode myType;
	private IdNode myId;
	private int mySize; // use value NOT_ARRAY if this is not an array type

	public static int NOT_ARRAY = -1;
}
