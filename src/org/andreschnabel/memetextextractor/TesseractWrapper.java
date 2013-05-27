package org.andreschnabel.memetextextractor;

import java.io.File;

public class TesseractWrapper {
	
	public static void main(String[] args) throws Exception {
		String imgFilename = "SampleOut.jpg";
		String text = extractText(imgFilename, "deu");
		System.out.println(text);
	}

	public static String extractText(String imgFilename, String language) throws Exception {
		ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\Tesseract-OCR\\tesseract.exe", imgFilename, "temp", "-l", language);
		Process p = pb.start();
		p.waitFor();
		File txtFile = new File("temp.txt");
		String text = Utils.readEntireFile(txtFile);
		txtFile.delete();
		return text;
	}

}
