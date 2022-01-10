package sem.ast.type;

//**********************************************************************
//IntType
//**********************************************************************
public class IntType extends Type {

	public boolean isIntType() {
		return true;
	}

	public boolean equals(Type t) {
		return t.isIntType();
	}

	public String toString() {
		return "int";
	}
}