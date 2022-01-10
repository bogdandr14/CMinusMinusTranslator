package sem;

import java.io.File;

public class main {

	public static void main(String[] args) {
		String basePath = new File("").getAbsolutePath();
		String[] files = new String[2];
		files[0] = basePath + "\\src\\input.txt";
		Translator t = new Translator(files);
		t.run();
	}
}
