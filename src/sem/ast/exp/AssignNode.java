package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.Type;

public class AssignNode extends ExpNode {
	public AssignNode(ExpNode lhs, ExpNode exp) {
		myLhs = lhs;
		myExp = exp;
	}

	/**
	 * Return the line number for this assignment node. The line number is the one
	 * corresponding to the left operand.
	 */
	public int lineNum() {
		return myLhs.lineNum();
	}

	/**
	 * Return the char number for this assignment node. The char number is the one
	 * corresponding to the left operand.
	 */
	public int charNum() {
		return myLhs.charNum();
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		Type typeLhs = myLhs.typeCheck();
		Type typeExp = myExp.typeCheck();
		Type retType = typeLhs;

//		if (typeLhs.isFnType() && typeExp.isFnType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Function assignment");
//			retType = new ErrorType();
//		}
//
//		if (typeLhs.isArrayDefType() && typeExp.isArrayDefType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Array index assignment");
//			retType = new ErrorType();
//		}
//
//		if (typeLhs.isArrayType() && typeExp.isArrayType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Array variable assignment");
//			retType = new ErrorType();
//		}
//
//		if (!typeLhs.equals(typeExp) && !typeLhs.isErrorType() && !typeExp.isErrorType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Type mismatch");
//			retType = new ErrorType();
//		}
//
//		if (typeLhs.isErrorType() || typeExp.isErrorType()) {
//			retType = new ErrorType();
//		}

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

	// 2 kids
	private ExpNode myLhs;
	private ExpNode myExp;
}