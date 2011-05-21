package edu.fic.image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Write image files
 * 
 * @author Morteza
 * 
 */
public class GrayscaleImageWriter {

	public static void writeToFile(GrayscaleImage gsImage, String imagePath){
		BufferedImage image = new BufferedImage(gsImage.getWidth(), gsImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();

		for (int x = 0; x < gsImage.getWidth(); x++)
			for (int y = 0; y < gsImage.getHeight(); y++)
				raster.setSample(x, y, 0, gsImage.pixelValues[x][y]);

		try {
			ImageIO.write(image, "jpg", new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
