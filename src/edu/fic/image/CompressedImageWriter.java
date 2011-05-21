package edu.fic.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import edu.fic.Constants;

/**
 * Write a compressed image into a file
 * <br />
 * ====================================== <br />
 * Compressed File Format (hfic): <br />
 * </br >
 * <code>
 * 1: (Encoder Version) <br />
 * 2: (Image Width in Pixel) (Image Height in Pixel) <br />
 * 3: (Domain Width in Pixel) (Domain Length in Pixel) <br />
 * 4: (Range Width in Pixel) (Range Length in Pixel) <br />
 * 5: (Range Index) (Domain X) (Domain Y) (Affine Transfer Index) (Brightness) (Contrast) <br />
 * ... <br />
 * n: (Range Index) (Domain X) (Domain Y) (Affine Transfer Index) (Brightness) (Contrast) <br />
 * </code>
 **/
public class CompressedImageWriter implements Constants{
	public static void writeToFile(CompressedImage img, String filePath){
		try{
			BufferedWriter fWriter = new BufferedWriter(new FileWriter(new File(filePath)));
			fWriter.write(ENCODER_VERSION);
			fWriter.newLine();
			fWriter.write(""+img.originalImageWidth + " "+img.originalImageHeight);
			fWriter.newLine();
			int domainWidth = img.domainBlocks.get(0).width;
			int domainHeight = img.domainBlocks.get(0).height;
			fWriter.write("" + domainWidth + " " + domainHeight);
			fWriter.newLine();
			int rangeWidth = img.rangeBlocks.get(0).width;
			int rangeHeight = img.rangeBlocks.get(0).height;
			fWriter.write("" + rangeWidth + " " + rangeHeight);
			fWriter.newLine();
			for(ImageBlockTransform t: img.transforms){
				int rangeIndex = img.transforms.indexOf(t);
				fWriter.write(""+ rangeIndex + " ");
				fWriter.write(""+ t.originalDomainX + " ");
				fWriter.write(""+ t.originalDomainY + " ");
				fWriter.write(""+ t.dihedralAffineTransformerIndex + " ");
				fWriter.write(""+ t.brightnessOffset + " ");
				fWriter.write(""+ t.contrastScale + " ");
				fWriter.newLine();
			}
			fWriter.flush();
			fWriter.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
