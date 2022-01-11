package sem.symb;

import sem.ast.type.ArrayType;
import sem.ast.type.Type;
import sem.ast.type.TypeNode;


public class ArraySym extends SemSym {
    private TypeNode arrayType;
    private int numElem;

    public ArraySym(TypeNode type, int numelem) {
        super(new ArrayType(type, numelem));
        arrayType = type;
        numElem = numelem;
    }

    public TypeNode getArrayType() {
        return arrayType;
    }
    
    public int getNumElem() {
        return numElem;
    }
    
    public String toString() {
    	String str = arrayType.toString() + " array[" + numElem +"]";
        return str;
    }
}