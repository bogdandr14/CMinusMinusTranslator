package sem.ast.type;

import java.io.PrintWriter;

public class BoolNode extends TypeNode {
	public BoolNode() {
	}

	/**
	 * type
	 */
	public Type type() {
		return new BoolType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("bool");
	}
}