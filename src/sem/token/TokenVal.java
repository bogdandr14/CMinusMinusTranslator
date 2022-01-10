package sem.token;

public class TokenVal {
	// fields
	int linenum;
	int charnum;

	// constructor
	public TokenVal(int line, int ch) {
		linenum = line;
		charnum = ch;
	}

	public int getLine() {
		return linenum;
	}

	public int getCol() {
		return charnum;
	}

	public Object getVal() {
		return null;
	}
}
