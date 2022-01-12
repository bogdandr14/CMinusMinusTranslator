package sem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;
import sem.ast.decl.ProgramNode;

public class Translator {
	FileReader inFile;
	private PrintWriter outFile;
	private static PrintStream outStream = System.err;

	public static final int RESULT_CORRECT = 0;
	public static final int RESULT_SYNTAX_ERROR = 1;
	public static final int RESULT_TYPE_ERROR = 2;
	public static final int RESULT_OTHER_ERROR = -1;

	Translator(String[] args) {
		if (args.length < 1) {
			String msg = "please supply name of file to be parsed" + " and name of file for unparsed version";
			pukeAndDie(msg);
		}

		try {
			setInfile(args[0]);
			setOutfile(args[1]);
		} catch (BadInfileException e) {
			pukeAndDie(e.getMessage());
		} catch (BadOutfileException e) {
			pukeAndDie(e.getMessage());
		}
	}

	public void setInfile(String filename) throws BadInfileException {
		try {
			inFile = new FileReader(filename);
		} catch (FileNotFoundException ex) {
			throw new BadInfileException(ex, filename);
		}
	}

	public void setOutfile(String filename) throws BadOutfileException {
		try {
			outFile = new PrintWriter(filename);
		} catch (FileNotFoundException ex) {
			throw new BadOutfileException(ex, filename);
		}
	}

	private void pukeAndDie(String error) {
		pukeAndDie(error, -1);
	}

	private void pukeAndDie(String error, int retCode) {
		outStream.println(error);
		cleanup();
		System.exit(-1);
	}

	public void cleanup() {
		if (inFile != null) {
			try {
				inFile.close();
			} catch (IOException e) {
			}
		}
		if (outFile != null) {
			outFile.flush();
			outFile.close();
		}
	}

	private class BadInfileException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message;

		public BadInfileException(Exception cause, String filename) {
			super(cause);
			this.message = "Could not open " + filename + " for reading";
		}

		@Override
		public String getMessage() {
			return message;
		}
	}

	private class BadOutfileException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message;

		public BadOutfileException(Exception cause, String filename) {
			super(cause);
			this.message = "Could not open " + filename + " for reading";
		}

		@Override
		public String getMessage() {
			return message;
		}
	}

	private Symbol parseConfig() {
		try {
			Parser P = new Parser(new Lexer(inFile));
			return P.parse();
		} catch (Exception e) {
			return null;
		}
	}

	public int process() {
		Symbol configRoot = parseConfig();

		ProgramNode astRoot = (ProgramNode) configRoot.value;
		if (ErrMsg.getErr()) {
			return Translator.RESULT_SYNTAX_ERROR;
		}
		
		astRoot.nameAnalysis();
		//astRoot.typeCheck(); // perform type check
		if (ErrMsg.getErr()) {
			return Translator.RESULT_TYPE_ERROR;
		}

		astRoot.unparse(outFile, 0); // unparse the tree

		return Translator.RESULT_CORRECT;
	}

	public void run() {
		int resultCode = process();
		if (resultCode == RESULT_CORRECT) {
			cleanup();
			return;
		}

		switch (resultCode) {
		case RESULT_SYNTAX_ERROR:
			pukeAndDie("Syntax error", resultCode);
		case RESULT_TYPE_ERROR:
			pukeAndDie("Type checking error", resultCode);
		default:
			pukeAndDie("Type checking error", RESULT_OTHER_ERROR);
		}
	}
}
