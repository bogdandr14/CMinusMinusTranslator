package sem.ast.type;

//**********************************************************************
//ErrorType
//**********************************************************************
public class ErrorType extends Type {

	public boolean isErrorType() {
		return true;
	}

	public boolean equals(Type t) {
		return t.isErrorType();
	}

	public String toString() {
		return "error";
	}
}