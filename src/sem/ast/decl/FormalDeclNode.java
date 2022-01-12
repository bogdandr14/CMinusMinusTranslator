package sem.ast.decl;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.exp.IdNode;
import sem.ast.type.TypeNode;
import sem.ast.type.VoidNode;
import sem.symb.DuplicateSymException;
import sem.symb.EmptySymTableException;
import sem.symb.SemSym;
import sem.symb.SymTable;

public class FormalDeclNode extends DeclNode {
	private TypeNode myType;
	private IdNode myId;
	
	public FormalDeclNode(TypeNode type, IdNode id) {
		myType = type;
		myId = id;
	}
	
	public SemSym nameAnalysis(SymTable symTab) {
        String name = myId.name();
        boolean badDecl = false;
        SemSym sym = null;

        if (myType instanceof VoidNode) {
            ErrMsg.fatal(myId.lineNum(), myId.charNum(),
                         "Non-function declared void");
            badDecl = true;
        }

        if (symTab.lookupLocal(name) != null) {
            ErrMsg.fatal(myId.lineNum(), myId.charNum(),
                         "Multiply declared identifier");
            badDecl = true;
        }

        if (!badDecl) {  // insert into symbol table
            try {
                sym = new SemSym(myType.type());
                symTab.addDecl(name, sym);
                myId.link(sym);
            } catch (DuplicateSymException ex) {
                System.err.println("Unexpected DuplicateSymException " +
                                   " in VarDeclNode.nameAnalysis");
                System.exit(-1);
            } catch (EmptySymTableException ex) {
                System.err.println("Unexpected EmptySymTableException " +
                                   " in VarDeclNode.nameAnalysis");
                System.exit(-1);
            }
        }

        return sym;
    }

	public void unparse(PrintWriter p, int indent) {
		myType.unparse(p, 0);
		p.print(" ");
		p.print(myId.name());
	}
}
