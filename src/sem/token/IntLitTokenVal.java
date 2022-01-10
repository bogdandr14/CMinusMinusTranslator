package sem.token;

public class IntLitTokenVal extends TokenVal {
	// new field: the value of the integer literal
	int intVal;

	// constructor
	public IntLitTokenVal(int line, int ch, int val) {
		super(line, ch);
		intVal = val;
	}

	public Object getVal() {
		return intVal;
	}
}
