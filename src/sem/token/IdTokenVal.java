package sem.token;

public class IdTokenVal extends TokenVal {
	// new field: the value of the identifier
	String idVal;

	// constructor
	public IdTokenVal(int line, int ch, String val) {
		super(line, ch);
		idVal = val;
	}

	public Object getVal() {
		return idVal;
	}
}