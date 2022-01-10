package sem.ast.type;

//**********************************************************************
//VoidType
//**********************************************************************
public class VoidType extends Type {

	public boolean isVoidType() {
		return true;
	}

	public boolean equals(Type t) {
		return t.isVoidType();
	}

	public String toString() {
		return "void";
	}
}
