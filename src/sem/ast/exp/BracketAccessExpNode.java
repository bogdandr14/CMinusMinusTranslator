package sem.ast.exp;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.type.ArrayType;
import sem.ast.type.ErrorType;
import sem.ast.type.Type;
import sem.symb.ArraySym;
import sem.symb.SemSym;
import sem.symb.SymTable;

public class BracketAccessExpNode extends ExpNode {
	private IdNode myId;
	private ExpNode myPos;
	private boolean badAccess;

	public BracketAccessExpNode(IdNode id, ExpNode pos) {
		myId = id;
		myPos = pos;
	}

	public int lineNum() {
		return myId.lineNum();
	}

	public int charNum() {
		return myId.charNum();
	}

	public void nameAnalysis(SymTable symTab) {
		badAccess = false;
		SemSym sym = null;

		myId.nameAnalysis(symTab);

		sym = myId.sym();

		if (sym == null) { // ID was undeclared
			badAccess = true;
		} else if (!(sym instanceof ArraySym)) {
			ErrMsg.fatal(myId.lineNum(), myId.charNum(), "Bracket-access of non-array type");
			badAccess = true;
		}

		if (!badAccess) {
			sym = symTab.lookupGlobal(myId.name());
			if (sym == null) {
				ErrMsg.fatal(myId.lineNum(), myId.charNum(), "Invalid array name");
				badAccess = true;
			} else {
				myId.link(sym);
				ExpNode exp = ((ArrayType) sym.getType()).getExp();
				if (exp instanceof IntLitNode && myPos instanceof IntLitNode) {
					int arraySize = ((IntLitNode) exp).getIntVal();
					int arrayPos = ((IntLitNode) myPos).getIntVal();
					if (arraySize <= arrayPos || arrayPos < 0) {
						ErrMsg.fatal(myId.lineNum(), myId.charNum(), "Array out of bounds");
					}
				}

			}
		}
	}

	public Type typeCheck() {
		if (!(myPos instanceof IntLitNode)) {
			ErrMsg.fatal(lineNum(), charNum(), "Array size not int type");
			return new ErrorType();
		}
		return myId.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		myId.unparse(p, 0);
		p.print("[" + ((IntLitNode) myPos).getIntVal() + "]");
	}
}