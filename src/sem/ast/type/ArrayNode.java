package sem.ast.type;

import java.io.PrintWriter;

public class ArrayNode extends TypeNode {
    public ArrayNode(TypeNode type, int arraySyze) {
    	myType = type;
    }

    public TypeNode typeNode() {
        return myType;
    }

    /**
     * type
     */
    public Type type() {
        return new ArrayType(myType, mySize);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myType.type() + " array[" + mySize + "]");
    }

    // 1 kid
    private TypeNode myType;
    private int mySize;
}