package edu.fic.encoder;

import java.awt.Point;
import java.util.ArrayList;

import edu.fic.Constants;
import edu.fic.image.CompressedImage;
import edu.fic.image.GrayscaleImage;
import edu.fic.image.ImageBlock;
import edu.fic.image.ImageBlockTransform;

public class SimpleEncoder implements Constants{

	ArrayList<ImageBlock> rangeBlocks;
	ArrayList<ImageBlock> domainBlocks;
	
	ArrayList<ImageBlockTransform> transforms = null;
	GrayscaleImage inputImage;
	CompressedImage outputImage;
	
	public SimpleEncoder(GrayscaleImage inputImage, Point rangeSize, Point domainSize) {
		this.inputImage = inputImage;
		outputImage = new CompressedImage();
		outputImage.originalImageWidth = inputImage.getWidth();
		outputImage.originalImageHeight = inputImage.getHeight();
		
		SimpleBlockGenerator blockGenerator = new SimpleBlockGenerator(rangeSize, domainSize);
		rangeBlocks = blockGenerator.generateRangeBlocks(inputImage);
		domainBlocks = blockGenerator.generateDomainBlocks(inputImage);
		transforms = new ArrayList<ImageBlockTransform>();
		
		outputImage.rangeBlocks = rangeBlocks;
		outputImage.domainBlocks = domainBlocks;
	}

	public CompressedImage encode() {

		for (ImageBlock rangeBlock : rangeBlocks) {
			int percent = 100 * (rangeBlocks.indexOf(rangeBlock)+1) / rangeBlocks.size();
			System.err.println(percent + "%");
			ImageBlockTransform bestTransform = new ImageBlockTransform();
			int minDistance = Integer.MAX_VALUE;
			
			for (ImageBlock domainBlock : domainBlocks) {	

				double alpha = 0;
				for(int i = 0; i<domainBlock.width ; i++){
					for(int j = 0 ; j<domainBlock.height ; j++){
						alpha += (domainBlock.pixelValues[i][j] - domainBlock.meanPixelValue)
									* (rangeBlock.pixelValues[i][j] - rangeBlock.meanPixelValue);
					}
				}

				double contrast = alpha / domainBlock.beta;
				int brightness = (int) (rangeBlock.meanPixelValue - contrast * domainBlock.meanPixelValue);

				for(int indx = 0; indx<8;indx++){
					ImageBlock transformedDomainBlock = new ImageBlock(	domainBlock.x,
																		domainBlock.y, 
																	domainBlock.width, 
																	domainBlock.height);
					
					for(int x = 0; x<domainBlock.width ; x++){
						for(int y = 0 ; y<domainBlock.height ; y++){
							int newX = x * dihedralAffineTransforms[indx][0] + y * dihedralAffineTransforms[indx][1];
							int newY = x * dihedralAffineTransforms[indx][2] + y * dihedralAffineTransforms[indx][3];
							if(newX<0) newX +=domainBlock.width;
							if(newY<0) newY +=domainBlock.height;
							short newPixelValue = (short)(contrast * domainBlock.pixelValues[x][y] + brightness);
							if(newPixelValue<MAX_PIXEL_VALUE
									&& newPixelValue>=0)
								transformedDomainBlock.pixelValues[newX][newY] = newPixelValue;
							else
								transformedDomainBlock.pixelValues[newX][newY] = MAX_PIXEL_VALUE / 2;								
								
						}
					}
					
					int distance = getDistance(rangeBlock, transformedDomainBlock);

					if( (distance < minDistance)
							&& (distance!=0)){
						minDistance = distance;
						bestTransform.dihedralAffineTransformerIndex = indx;
						bestTransform.originalDomainX = domainBlock.x;
						bestTransform.originalDomainY = domainBlock.y;						
						bestTransform.brightnessOffset = brightness;
						bestTransform.contrastScale = contrast;
					}
				}
			}
			transforms.add(bestTransform);
		}
		outputImage.transforms = transforms;
		return outputImage;
	}

	public int getDistance(ImageBlock range, ImageBlock domain){
		double error = 0;
		for(int i=0;i<range.width;i++){
			for (int j=0;j<range.height;j++){
				error += Math.pow(range.pixelValues[i][j]-domain.pixelValues[i][j],2);
			}
		}
		error = error / (double)(range.width * range.height);
		return (int)error;
	}
}
