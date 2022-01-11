package sem.ast.type;

import java.io.PrintWriter;

public class VoidNode extends TypeNode {
	public VoidNode() {
	}

	public Type type() {
		return new VoidType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("void");
	}
}