package sem.ast.exp;

import sem.ast.type.IntType;
import sem.ast.type.Type;

public abstract class ArithmeticExpNode extends BinaryExpNode {
	public ArithmeticExpNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public Type typeCheck() {
		Type type1 = myExp1.typeCheck();
		Type type2 = myExp2.typeCheck();
		Type retType = new IntType();

//		if (!type1.isErrorType() && !type1.isIntType()) {
//			ErrMsg.fatal(myExp1.lineNum(), myExp1.charNum(), "Arithmetic operator applied to non-numeric operand");
//			retType = new ErrorType();
//		}
//
//		if (!type2.isErrorType() && !type2.isIntType()) {
//			ErrMsg.fatal(myExp2.lineNum(), myExp2.charNum(), "Arithmetic operator applied to non-numeric operand");
//			retType = new ErrorType();
//		}
//
//		if (type1.isErrorType() || type2.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}
}
