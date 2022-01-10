package sem.ast.exp;

public abstract class BinaryExpNode extends ExpNode {
	public BinaryExpNode(ExpNode exp1, ExpNode exp2) {
		myExp1 = exp1;
		myExp2 = exp2;
	}

	/**
	 * Return the line number for this binary expression node. The line number is
	 * the one corresponding to the left operand.
	 */
	public int lineNum() {
		return myExp1.lineNum();
	}

	/**
	 * Return the char number for this binary expression node. The char number is
	 * the one corresponding to the left operand.
	 */
	public int charNum() {
		return myExp1.charNum();
	}

	// two kids
	protected ExpNode myExp1;
	protected ExpNode myExp2;
}
