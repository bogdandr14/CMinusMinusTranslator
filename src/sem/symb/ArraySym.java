package sem.symb;

import sem.ast.type.ArrayType;
import sem.ast.type.Type;
import sem.ast.type.TypeNode;

 /**
 * The ArraySym class is a subclass of the Sym class just for variables
 * declared to be a array type.
 * Each ArraySym contains a symbol table to hold information about its
 * fields.
 */
public class ArraySym extends SemSym {
    // new fields
    private TypeNode arrayType;  // name of the array type
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
        // make list of formals
    	String str = arrayType.toString() + " array[" + numElem +"]";
        return str;
    }
}