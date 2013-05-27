package org.andreschnabel.memetextextractor;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class TextPixelFilter {
	
	public static boolean containsText(BufferedImage img) {
		int imgW = img.getWidth();
		int imgH = img.getHeight();		
		return textInRect(img, 0, imgW, 0, (int)(imgH/3.0f)) && textInRect(img, 0, imgW, (int)(2.0f/3.0f*imgH), imgH);
	}
	
	private static boolean textInRect(BufferedImage img, int startX, int endX, int startY, int endY) {
		for(int y=startY; y<endY; y++) {
			for(int x=startX; x<endX; x++) {
				if(isBlack(img.getRGB(x, y)))
					return true;
			}
		}			
		return false;
	}

	public static BufferedImage extractTextPixels(File imgFile) throws Exception {
		BufferedImage srcImg = ImageIO.read(imgFile);
		int imgW = srcImg.getWidth();
		int imgH = srcImg.getHeight();
		BufferedImage destImg = new BufferedImage(imgW, imgH, srcImg.getType());
		for(int y=0; y<imgH; y++) {
			for(int x=0; x<imgW; x++) {
				int srcVal = srcImg.getRGB(x, y);
				int destVal = thresholdRgbValue(srcVal);
				destImg.setRGB(x, y, destVal);
			}
		}
		return destImg; //dilate(destImg);
	}

	@SuppressWarnings("unused")
	private static BufferedImage dilate(BufferedImage img) {
		int imgW = img.getWidth();
		int imgH = img.getHeight();
		BufferedImage destImg = new BufferedImage(imgW, imgH, img.getType());
		for(int y=0; y<imgH; y++) {
			for(int x=0; x<imgW; x++) {
				int center = img.getRGB(x, y);
				int left = img.getRGB(x > 0 ? x-1 : 0, y);
				int right = img.getRGB(x < imgW-1 ? x+1 : imgW-1, y);
				int above = img.getRGB(x, y > 0 ? y-1 : 0);
				int below = img.getRGB(x, y < imgH-1 ? y+1 : imgH-1);
				boolean cb = isBlack(center);
				boolean lb = isBlack(left);
				boolean rb = isBlack(right);
				boolean ab = isBlack(above);
				boolean bb = isBlack(below);
				if((lb || rb || ab || bb) || cb) {
					destImg.setRGB(x, y, 0x000000);
				} else {
					destImg.setRGB(x, y, 0xffffff);
				}
			}
		}
		return destImg;
	}
	
	private static boolean isBlack(int srcRgbVal) {
		final int tl = 5;
		int r = (srcRgbVal >> 16) & 0xff;
		int g = (srcRgbVal >> 8) & 0xff;
		int b = (srcRgbVal >> 0) & 0xff;
		return (r <= tl && g <= tl && b <= tl);
	}

	private static int thresholdRgbValue(int srcRgbVal) {		
		return isWhite(srcRgbVal) ? 0x000000 : 0xffffff;
	}

	private static boolean isWhite(int srcRgbVal) {
		final int th = 250;
		int r = (srcRgbVal >> 16) & 0xff;
		int g = (srcRgbVal >> 8) & 0xff;
		int b = (srcRgbVal >> 0) & 0xff;
		return (r >= th && g >= th && b >= th);
	}

	public static void main(String[] args) throws Exception {
		String filename = "Sample.jpg";
		BufferedImage outImg = extractTextPixels(new File(filename));
		String outFilename = "SampleOut.jpg";
		ImageIO.write(outImg, "jpg", new File(outFilename));
	}

}
