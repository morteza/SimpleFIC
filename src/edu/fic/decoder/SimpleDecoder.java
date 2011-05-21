package edu.fic.decoder;

import edu.fic.Constants;
import edu.fic.image.CompressedImage;
import edu.fic.image.GrayscaleImage;
import edu.fic.image.ImageBlockTransform;

public class SimpleDecoder implements Constants{
	private CompressedImage compressedImage;
	private GrayscaleImage originalImage;
	
	public SimpleDecoder(CompressedImage compressedImage){
		this.compressedImage = compressedImage;
		originalImage = new GrayscaleImage(compressedImage.originalImageWidth, compressedImage.originalImageHeight);
	}
	
	public GrayscaleImage decode(){
		for(int it=0;it<NUMBER_OF_DECODER_ITERATIONS;it++){
			for(ImageBlockTransform transform:compressedImage.transforms){
				//TODO: iterate over transforms
				originalImage = transform.applyTransformOn(originalImage, range, domainSize)
			}
		}
		return originalImage;
	}
}
