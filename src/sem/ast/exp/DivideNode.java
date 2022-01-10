package sem.ast.exp;

import java.io.PrintWriter;

public class DivideNode extends ArithmeticExpNode {
	public DivideNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" / ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}
