package sem.ast.type;

//**********************************************************************
//FnType
//**********************************************************************
public class FnType extends Type {

	public boolean isFnType() {
		return true;
	}

	public boolean equals(Type t) {
		return t.isFnType();
	}

	public boolean hasReturnStmt() {
		return false;
	}

	public String toString() {
		return "function";
	}
}
