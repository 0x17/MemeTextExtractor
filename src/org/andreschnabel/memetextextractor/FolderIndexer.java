package org.andreschnabel.memetextextractor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class FolderIndexer {
	
	public static List<Meme> indexFolder(String path) throws Exception {
		List<Meme> memes = new LinkedList<Meme>();
		
		File folder = new File(path);
		if(!folder.isDirectory()) {
			throw new Exception("Path needs to point to directory!");
		}
		
		File[] files = folder.listFiles();
		for(File f : files) {
			String fname = f.getName();
			if(isImgFile(fname)) {
				BufferedImage img = TextPixelFilter.extractTextPixels(f);
				if(TextPixelFilter.containsText(img)) {
					File tmpImgFile = new File("temp.jpg");
					ImageIO.write(img, "jpg", tmpImgFile);
					String deText = TesseractWrapper.extractText("temp.jpg", "deu");
					String enText = TesseractWrapper.extractText("temp.jpg", "eng");					
					Meme nmeme = new Meme(f.getPath(), deText, enText);
					if(deText.trim().length() > 0 || enText.trim().length() > 0) {
						memes.add(nmeme);
						System.out.println("Added meme: " + nmeme);
					}
					tmpImgFile.delete();
				}
			}
		}
		
		return memes;
	}
	
	private static boolean isImgFile(String fname) {
		final String[] imgExtensions = new String[] {"jpg"};
		for(String ext : imgExtensions) {
			if(fname.endsWith(ext))
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		List<Meme> memes = indexFolder("C:\\Users\\Andre\\Dropbox\\Public\\b"/*"C:\\Users\\Andre\\Dropbox\\Code\\MemeTextExtractor"*/);
		for(Meme m : memes) {
			System.out.println(m);
		}
		MemeSerialization.serializeMemes(memes, new File("memes.json"));
	}

}
