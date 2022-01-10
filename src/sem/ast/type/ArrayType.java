package sem.ast.type;

//**********************************************************************
//ArrayType
//**********************************************************************
public class ArrayType extends Type {
 private TypeNode myType;
 private int numElem;

 public ArrayType(TypeNode type, int numelem) {
 	myType = type;
 	numElem = numelem;
 }

 public boolean isArrayType() {
     return true;
 }

 public int sizeArray() {
	 return numElem;
 }
 
 public boolean equals(Type t) {
     return t.isArrayType();
 }

 public String toString() {
     return myType.type().toString();
 }
}