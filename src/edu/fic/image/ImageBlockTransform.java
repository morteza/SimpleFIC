package edu.fic.image;

import java.awt.Point;
import java.awt.Rectangle;

import edu.fic.Constants;

public class ImageBlockTransform implements Constants{
	public int dihedralAffineTransformerIndex;
	public double contrastScale;
	public int brightnessOffset;
	public int originalDomainX;
	public int originalDomainY;

	public ImageBlockTransform() {
		dihedralAffineTransformerIndex = -1;
		contrastScale = 0;
		brightnessOffset = 0;
		originalDomainX = 0;
		originalDomainY = 0;
	}
	
	public GrayscaleImage applyTransformOn(GrayscaleImage gsImage, Rectangle range, Point domainSize){
		//TODO: do the transform on range using domain
		return gsImage;
	}

}
