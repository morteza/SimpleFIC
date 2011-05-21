package edu.fic.encoder;

import java.awt.Point;

import edu.fic.image.CompressedImage;
import edu.fic.image.CompressedImageWriter;
import edu.fic.image.GrayscaleImage;
import edu.fic.image.GrayscaleImageReader;

public class SimpleEncoderTest {

	public static void main(String[] args) {
		try{
			// input is 128*128 image
			GrayscaleImage originalImage = GrayscaleImageReader.loadFile("/cameraman.jpg");
			// range block size is 32*32
			Point rangeSize = new Point(16, 16);
			// domain block size is 32*32 (just for test before resizing domains!)
			Point domainSize = new Point(32, 32);
			SimpleEncoder encoder = new SimpleEncoder(originalImage, rangeSize, domainSize);
			CompressedImage cImage = encoder.encode();
			CompressedImageWriter.writeToFile(cImage, "/cameraman.hfic");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
