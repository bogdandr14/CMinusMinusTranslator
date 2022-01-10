package sem.token;

public class StrLitTokenVal extends TokenVal {
	// new field: the value of the string literal
	String strVal;

	// constructor
	public StrLitTokenVal(int line, int ch, String val) {
		super(line, ch);
		strVal = val;
	}

	public Object getVal() {
		return strVal;
	}
}