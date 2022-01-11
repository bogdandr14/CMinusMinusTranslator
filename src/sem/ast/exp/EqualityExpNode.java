package sem.ast.exp;

import sem.ast.type.*;

public abstract class EqualityExpNode extends BinaryExpNode {
	public EqualityExpNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public Type typeCheck() {
		Type type1 = myExp1.typeCheck();
		Type type2 = myExp2.typeCheck();
		Type retType = new BoolType();

//		if (type1.isVoidType() && type2.isVoidType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Equality operator applied to void functions");
//			retType = new ErrorType();
//		}
//
//		if (type1.isFnType() && type2.isFnType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Equality operator applied to functions");
//			retType = new ErrorType();
//		}
//
//		if (type1.isArrayDefType() && type2.isArrayDefType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Equality operator applied to array names");
//			retType = new ErrorType();
//		}
//
//		if (type1.isArrayType() && type2.isArrayType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Equality operator applied to array variables");
//			retType = new ErrorType();
//		}
//
//		if (!type1.equals(type2) && !type1.isErrorType() && !type2.isErrorType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Type mismatch");
//			retType = new ErrorType();
//		}
//
//		if (type1.isErrorType() || type2.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}
}
