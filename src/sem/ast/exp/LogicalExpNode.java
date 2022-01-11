package sem.ast.exp;

import sem.ast.type.BoolType;
import sem.ast.type.Type;

public abstract class LogicalExpNode extends BinaryExpNode {
	public LogicalExpNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public Type typeCheck() {
		Type type1 = myExp1.typeCheck();
		Type type2 = myExp2.typeCheck();
		Type retType = new BoolType();

//		if (!type1.isErrorType() && !type1.isBoolType()) {
//			ErrMsg.fatal(myExp1.lineNum(), myExp1.charNum(), "Logical operator applied to non-bool operand");
//			retType = new ErrorType();
//		}
//
//		if (!type2.isErrorType() && !type2.isBoolType()) {
//			ErrMsg.fatal(myExp2.lineNum(), myExp2.charNum(), "Logical operator applied to non-bool operand");
//			retType = new ErrorType();
//		}
//
//		if (type1.isErrorType() || type2.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}
}
