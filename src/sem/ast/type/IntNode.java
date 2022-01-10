package sem.ast.type;

import java.io.PrintWriter;

public class IntNode extends TypeNode {
	public IntNode() {
	}

	/**
	 * type
	 */
	public Type type() {
		return new IntType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("int");
	}
}
