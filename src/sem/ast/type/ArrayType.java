package sem.ast.type;

import sem.ast.exp.ExpNode;
import sem.ast.exp.IdNode;
import sem.ast.exp.IntLitNode;
//**********************************************************************
//ArrayDefType
//**********************************************************************
public class ArrayType extends Type {
	private TypeNode myType;
	private ExpNode numElem;

	public ArrayType(TypeNode type, ExpNode exp) {
		myType = type;
		numElem = exp;
	}

	public boolean isArrayType() {
		return true;
	}
	
	public TypeNode getType() {
		return myType;
	}
	
	public int sizeArray() {
		return ((IntLitNode)numElem).getIntVal();
	}

	public boolean equals(Type t) {
		return t.isArrayType();
	}

	public String toString() {
		return myType.type().toString();
	}
}