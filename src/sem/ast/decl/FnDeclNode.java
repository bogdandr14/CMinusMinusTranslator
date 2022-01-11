package sem.ast.decl;

import java.io.PrintWriter;
import java.util.List;

import sem.ErrMsg;
import sem.ast.exp.IdNode;
import sem.ast.stmt.FnBodyNode;
import sem.ast.type.Type;
import sem.ast.type.TypeNode;
import sem.symb.DuplicateSymException;
import sem.symb.EmptySymTableException;
import sem.symb.FnSym;
import sem.symb.SemSym;
import sem.symb.SymTable;

public class FnDeclNode extends DeclNode {
	public FnDeclNode(TypeNode type, IdNode id, FormalsListNode formalList, FnBodyNode body) {
		myType = type;
		myId = id;
		myFormalsList = formalList;
		myBody = body;
	}
	
	public SemSym nameAnalysis(SymTable symTab) {
        String name = myId.name();
        FnSym sym = null;

        if (symTab.lookupLocal(name) != null) {
            ErrMsg.fatal(myId.lineNum(), myId.charNum(),
                         "Multiply declared identifier");
        }

        else { // add function name to local symbol table
            try {
                sym = new FnSym(myType.type(), myFormalsList.length());
                symTab.addDecl(name, sym);
                myId.link(sym);
            } catch (DuplicateSymException ex) {
                System.err.println("Unexpected DuplicateSymException " +
                                   " in FnDeclNode.nameAnalysis");
                System.exit(-1);
            } catch (EmptySymTableException ex) {
                System.err.println("Unexpected EmptySymTableException " +
                                   " in FnDeclNode.nameAnalysis");
                System.exit(-1);
            }
        }

        symTab.addScope();  // add a new scope for locals and params

        // process the formals
        List<Type> typeList = myFormalsList.nameAnalysis(symTab);
        if (sym != null) {
            sym.addFormals(typeList);
        }

        myBody.nameAnalysis(symTab); // process the function body

        try {
            symTab.removeScope();  // exit scope
        } catch (EmptySymTableException ex) {
            System.err.println("Unexpected EmptySymTableException " +
                               " in FnDeclNode.nameAnalysis");
            System.exit(-1);
        }

        // formals //
    		this.formalsSize = typeList.size() * 4;
    		// locals //
    		this.localsSize = myBody.numLocals() * 4;

        return null;
    }

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
	
	// Keep track of nodes formals and locals size //
	private int formalsSize;
	private int localsSize;
}