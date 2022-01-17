package sem.ast.exp;

import java.io.PrintWriter;

import sem.ErrMsg;
import sem.ast.type.ArrayType;
import sem.ast.type.ErrorType;
import sem.ast.type.Type;
import sem.symb.SymTable;

public class AssignNode extends ExpNode {
	private ExpNode myLhs;
	private ExpNode myExp;
	
	public AssignNode(ExpNode lhs, ExpNode exp) {
		myLhs = lhs;
		myExp = exp;
	}

	public int lineNum() {
		return myLhs.lineNum();
	}

	public int charNum() {
		return myLhs.charNum();
	}

	public void nameAnalysis(SymTable symTab) {
        myLhs.nameAnalysis(symTab);
        myExp.nameAnalysis(symTab);
    }
	
	public Type typeCheck() {
		Type typeLhs = myLhs.typeCheck();
		Type typeExp = myExp.typeCheck();
		Type retType = typeLhs;

		if (typeLhs.isFnType() && typeExp.isFnType()) {
			ErrMsg.fatal(lineNum(), charNum(), "Function assignment");
			retType = new ErrorType();
		}
		
		if(typeLhs.isArrayType()) {
			typeLhs = ((ArrayType)typeLhs).getType().type();
		}
		
		if(typeExp.isArrayType()) {
			typeExp = ((ArrayType)typeExp).getType().type();
		}
		
		if (!typeLhs.equals(typeExp) && !typeLhs.isErrorType() && !typeExp.isErrorType()) {
			ErrMsg.fatal(lineNum(), charNum(), "Type mismatch");
			retType = new ErrorType();
		}

		if (typeLhs.isErrorType() || typeExp.isErrorType()) {
			retType = new ErrorType();
		}

		return retType;
	}

	public void unparse(PrintWriter p, int indent) {
		if (indent != -1)
			p.print("(");
		myLhs.unparse(p, 0);
		p.print(" = ");
		myExp.unparse(p, 0);
		if (indent != -1)
			p.print(")");
	}
}