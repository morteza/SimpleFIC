package edu.fic.image;

import java.util.ArrayList;

public class CompressedImage {
	public int originalImageWidth;
	public int originalImageHeight;
	public ArrayList<ImageBlock> rangeBlocks;
	public ArrayList<ImageBlock> domainBlocks;
	public ArrayList<ImageBlockTransform> transforms;

}
