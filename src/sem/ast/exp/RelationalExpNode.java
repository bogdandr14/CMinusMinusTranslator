package sem.ast.exp;

import sem.ErrMsg;
import sem.ast.type.BoolType;
import sem.ast.type.ErrorType;
import sem.ast.type.Type;

public abstract class RelationalExpNode extends BinaryExpNode {
	public RelationalExpNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public Type typeCheck() {
		Type type1 = myExp1.typeCheck();
		Type type2 = myExp2.typeCheck();
		Type retType = new BoolType();
		if (!type1.isErrorType() && !type1.isIntType()) {
			ErrMsg.fatal(myExp1.lineNum(), myExp1.charNum(), "Relational operator applied to non-numeric operand");
			retType = new ErrorType();
		}
		if (!type2.isErrorType() && !type2.isIntType()) {
			ErrMsg.fatal(myExp2.lineNum(), myExp2.charNum(), "Relational operator applied to non-numeric operand");
			retType = new ErrorType();
		}
		if (type1.isErrorType() || type2.isErrorType())
			retType = new ErrorType();
		return retType;
	}
}