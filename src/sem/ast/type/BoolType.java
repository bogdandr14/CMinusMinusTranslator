package sem.ast.type;

//**********************************************************************
//BoolType
//**********************************************************************
public class BoolType extends Type {

	public boolean isBoolType() {
		return true;
	}

	public boolean equals(Type t) {
		return t.isBoolType();
	}

	public String toString() {
		return "bool";
	}
}
