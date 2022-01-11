package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.IntType;
import sem.ast.type.Type;

public class UnaryMinusNode extends UnaryExpNode {
	public UnaryMinusNode(ExpNode exp) {
		super(exp);
	}

	public Type typeCheck() {
		Type type = myExp.typeCheck();
		Type retType = new IntType();

//		if (!type.isErrorType() && !type.isIntType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Arithmetic operator applied to non-numeric operand");
//			retType = new ErrorType();
//		}
//
//		if (type.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(-");
		myExp.unparse(p, 0);
		p.print(")");
	}
}