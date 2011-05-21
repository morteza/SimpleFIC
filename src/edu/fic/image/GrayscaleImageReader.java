package edu.fic.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Read uncompressed image files
 * 
 * @author Morteza
 * 
 */
public class GrayscaleImageReader {

	public static GrayscaleImage loadFile(String imagePath) throws IOException {
		BufferedImage bi = ImageIO.read(new File(imagePath));
		if (bi == null)
			throw new IOException("Cannot open image file.");

		int width = bi.getWidth();
		int height = bi.getHeight();
		GrayscaleImage gsImage = new GrayscaleImage(width,height);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (bi.getRaster().getNumBands() == 1) {
					gsImage.pixelValues[i][j] = (short) bi.getRaster().getSample(i, j, 0);
				} else if (bi.getRaster().getNumBands() == 3) {
					// convert RGB images to gray-scale
					int r = bi.getRaster().getSample(i, j, 0);
					int g = bi.getRaster().getSample(i, j, 1);
					int b = bi.getRaster().getSample(i, j, 2);
					gsImage.pixelValues[i][j] = (short) ((r + g + b) / 3 + 0.49);
				} else {
					//TODO: throws exception for unknown band type
				}
			}
		}
		return gsImage;
	}
}
