package sem.ast.exp;

import java.io.PrintWriter;
import java.util.LinkedList;

import sem.ast.type.ErrorType;
import sem.ast.type.Type;

public class CallExpNode extends ExpNode {
	public CallExpNode(IdNode name, ExpListNode elist) {
		myId = name;
		myExpList = elist;
	}

	public CallExpNode(IdNode name) {
		myId = name;
		myExpList = new ExpListNode(new LinkedList<ExpNode>());
	}

	/**
	 * Return the line number for this call node. The line number is the one
	 * corresponding to the function name.
	 */
	public int lineNum() {
		return myId.lineNum();
	}

	/**
	 * Return the char number for this call node. The char number is the one
	 * corresponding to the function name.
	 */
	public int charNum() {
		return myId.charNum();
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
//		if (!myId.typeCheck().isFnType()) {
//			ErrMsg.fatal(myId.lineNum(), myId.charNum(), "Attempt to call a non-function");
//			return new ErrorType();
//		}

//        FnSym fnSym = (FnSym)(myId.sym());
//
//        if (fnSym == null) {
//            System.err.println("null sym for Id in CallExpNode.typeCheck");
//            System.exit(-1);
//        }
//
//        if (myExpList.size() != fnSym.getNumParams()) {
//            ErrMsg.fatal(myId.lineNum(), myId.charNum(),
//                         "Function call with wrong number of args");
//            return fnSym.getReturnType();
//        }
//
//        myExpList.typeCheck(fnSym.getParamTypes());
		return null;
	}

	public void unparse(PrintWriter p, int indent) {
		myId.unparse(p, 0);
		p.print("(");
		if (myExpList != null) {
			myExpList.unparse(p, 0);
		}
		p.print(")");
	}

	// 2 kids
	private IdNode myId;
	private ExpListNode myExpList; // possibly null
}
