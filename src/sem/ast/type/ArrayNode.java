package sem.ast.type;

import java.io.PrintWriter;

public class ArrayNode extends TypeNode {
    public ArrayNode(TypeNode type, int arraySize) {
    	myType = type;
    	mySize = arraySize;
    }

    public TypeNode typeNode() {
        return myType;
    }

    public int getSize() {
    	return mySize;
    }
    /**
     * type
     */
    public Type type() {
        return new ArrayType(myType, mySize);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myType.type());
    }

    // 1 kid
    private TypeNode myType;
    private int mySize;
}