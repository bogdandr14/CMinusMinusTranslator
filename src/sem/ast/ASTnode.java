package sem.ast;

import java.io.PrintWriter;

public abstract class ASTnode {
	// every subclass must provide an unparse operation
	abstract public void unparse(PrintWriter p, int indent);

	// this method can be used by the unparse methods to do indenting
	protected void doIndent(PrintWriter p, int indent) {
		for (int k = 0; k < indent; k++)
			p.print(" ");
	}
}