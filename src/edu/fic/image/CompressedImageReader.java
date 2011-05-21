package edu.fic.image;

import java.io.File;

/**
 * Read an compressed image file
 * <br />
 * ====================================== <br />
 * Compressed File Format (hfic): <br />
 * </br >
 * <code>
 * 1: (Encoder Version) <br />
 * 2: (Image Width in Pixel) (Image Height in Pixel) <br />
 * 3: (Domain Width in Pixel) (Domain Length in Pixel) <br />
 * 4: (Range Width in Pixel) (Range Length in Pixel) <br />
 * 5: (Range Index) (Domain Index) (Affine Transfer Index) (Brightness) (Contrast) <br />
 * ... <br />
 * n: (Range Index) (Domain Index) (Affine Transfer Index) (Brightness) (Contrast) <br />
 * </code>
 **/
public class CompressedImageReader {

	public static CompressedImage loadFromFile(String imagePath){
		//TODO: open file in text mode
		//TODO: check version
		//TODO: read width and height create a fake black start-image
		//TODO: read domain data and add them to start-image
		//TODO: read ranges data and add them to start-image
		//TODO: iterate over transforms and add them to start-image
		//TODO: call decoder on start-image
		//TODO: save result-image
		return null;
	}
}
