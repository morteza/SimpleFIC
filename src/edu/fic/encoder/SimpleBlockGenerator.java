package edu.fic.encoder;

import java.awt.Point;
import java.util.ArrayList;

import edu.fic.image.GrayscaleImage;
import edu.fic.image.ImageBlock;

public class SimpleBlockGenerator {
	
	protected Point rangeSize, domainSize;
	
	public SimpleBlockGenerator(Point rangeSize, Point domainSize){
		this.rangeSize = rangeSize;
		this.domainSize = domainSize;
	}
	
	public ArrayList<ImageBlock> generateRangeBlocks(GrayscaleImage inputImage) {

		int blockWidth = (int) rangeSize.getX();
		int blockHeight = (int) rangeSize.getY();
		int numOfBlocksPerRow = inputImage.getWidth() / blockWidth;
		int numOfBlocksPerCol = inputImage.getHeight() / blockHeight;

		ArrayList<ImageBlock> blocks = new ArrayList<ImageBlock>();

		for (int i = 0; i < numOfBlocksPerRow; i++) {
			for (int j = 0; j < numOfBlocksPerCol; j++) {
				ImageBlock block = new ImageBlock(	i * blockWidth,
													j * blockHeight,
													blockWidth,
													blockHeight);
				double sumOfPixelValues = 0;
				for (int x = 0; x < blockWidth; x++) {
					for (int y = 0; y < blockHeight; y++) {
						block.pixelValues[x][y] = inputImage.pixelValues[i * blockWidth + x]
						                                                [j * blockHeight + y];
						sumOfPixelValues += block.pixelValues[x][y];
					}
				}
				block.meanPixelValue = sumOfPixelValues / (double)(blockWidth*blockHeight);
				blocks.add(block);
			}
		}

		return blocks;
	}

	public ArrayList<ImageBlock> generateDomainBlocks(GrayscaleImage inputImage) {
		int blockWidth = (int) domainSize.getX();
		int blockHeight = (int) domainSize.getY();
		int numOfBlocksPerRow = inputImage.getWidth() - blockWidth + 1;
		int numOfBlocksPerCol = inputImage.getHeight() - blockHeight + 1;

		ArrayList<ImageBlock> blocks = new ArrayList<ImageBlock>();

		for (int i = 0; i < numOfBlocksPerRow; i++) {
			for (int j = 0; j < numOfBlocksPerCol; j++) {
				ImageBlock block = new ImageBlock(i, j, blockWidth, blockHeight);
				double sumOfPixelValues = 0;
				for (int x = 0; x < blockWidth; x++) {
					for (int y = 0; y < blockHeight; y++) {
						block.pixelValues[x][y] = inputImage.pixelValues[i + x][j + y];
						sumOfPixelValues += block.pixelValues[x][y];
					}
				}
				block.meanPixelValue = sumOfPixelValues / (double)(blockWidth*blockHeight);
				for (int x = 0; x < blockWidth; x++) {
					for (int y = 0; y < blockHeight; y++) {
						block.beta += (block.pixelValues[x][y] - block.meanPixelValue)
										* (block.pixelValues[x][y] - block.meanPixelValue);
					}
				}
				block.resize(2);
				blocks.add(block);
			}
		}

		return blocks;
	}

}
