package sem.ast.exp;

import java.io.PrintWriter;

import sem.ast.type.BoolType;
import sem.ast.type.Type;

public class NotNode extends UnaryExpNode {
	public NotNode(ExpNode exp) {
		super(exp);
	}

	public Type typeCheck() {
		Type type = myExp.typeCheck();
		Type retType = new BoolType();

//		if (!type.isErrorType() && !type.isBoolType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Logical operator applied to non-bool operand");
//			retType = new ErrorType();
//		}
//
//		if (type.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(!");
		myExp.unparse(p, 0);
		p.print(")");
	}
}