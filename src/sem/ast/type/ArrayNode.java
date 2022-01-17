package sem.ast.type;

import java.io.PrintWriter;

import sem.ast.exp.ExpNode;
import sem.ast.exp.IdNode;
import sem.ast.exp.IntLitNode;

public class ArrayNode extends TypeNode {
	private TypeNode myType;
	private ExpNode mySize;
	    
    public ArrayNode(TypeNode type, ExpNode arraySize) {
    	myType = type;
    	mySize = arraySize;
    }

    public TypeNode typeNode() {
        return myType;
    }

    public ExpNode getSize() {
    	return mySize;
    }

    public Type type() {
        return new ArrayType(myType, mySize);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myType.type().toString());
    }
}