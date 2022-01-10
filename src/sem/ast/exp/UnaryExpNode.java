package sem.ast.exp;

public abstract class UnaryExpNode extends ExpNode {
	public UnaryExpNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * Return the line number for this unary expression node. The line number is the
	 * one corresponding to the operand.
	 */
	public int lineNum() {
		return myExp.lineNum();
	}

	/**
	 * Return the char number for this unary expression node. The char number is the
	 * one corresponding to the operand.
	 */
	public int charNum() {
		return myExp.charNum();
	}

	// one child
	protected ExpNode myExp;
}
