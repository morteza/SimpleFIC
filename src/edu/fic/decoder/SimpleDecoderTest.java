package edu.fic.decoder;

import edu.fic.image.CompressedImage;
import edu.fic.image.CompressedImageReader;
import edu.fic.image.GrayscaleImage;
import edu.fic.image.GrayscaleImageWriter;

public class SimpleDecoderTest {
	public static void main(String[] args){
		try{
			CompressedImage cImage = CompressedImageReader.loadFromFile("/cameraman.hfic");
			SimpleDecoder decoder = new SimpleDecoder(cImage);
			GrayscaleImage originalImage = decoder.decode();
			GrayscaleImageWriter.writeToFile(originalImage, "/uncomcamera.jpg");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
