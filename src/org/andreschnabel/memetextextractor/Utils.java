package org.andreschnabel.memetextextractor;

import java.io.File;
import java.io.FileReader;

public class Utils {
	
	public static String readEntireFile(File file) throws Exception {
		FileReader fr = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		int ch;
		while((ch = fr.read()) != -1) {
			builder.append((char)ch);
		}
		fr.close();
		return builder.toString();
	}

}
