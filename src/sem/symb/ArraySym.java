package sem.symb;

import sem.ast.exp.ExpNode;
import sem.ast.exp.IdNode;
import sem.ast.type.ArrayType;
import sem.ast.type.TypeNode;

public class ArraySym extends SemSym {
	 // new fields
    private IdNode myId;
    
    public ArraySym(IdNode id, TypeNode type, ExpNode numElem) {
        super(new ArrayType(type, numElem));
        myId = id;
    }
    
    public IdNode getArrayType() {
        return myId;
    }
}
