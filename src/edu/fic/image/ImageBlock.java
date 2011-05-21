package edu.fic.image;

public class ImageBlock {
	public int x, y;
	public int width, height;
	public short[][] pixelValues;
	public double beta;
	public double meanPixelValue;
	
	public ImageBlock(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		pixelValues = new short[width][height];
		beta = 0;
		meanPixelValue = 0;
	}
	
	public void resize(int contractivity){
		int newWidth = width / contractivity;
		int newHeight = height / contractivity;
		
		short[][] newPixelValues = new short[newWidth][newHeight];
		for (int i = 0; i< newWidth;i++){
			for (int j=0;j< newHeight;j++){
				newPixelValues[i][j] = (short)((pixelValues[i*2][j*2]
				                       + pixelValues[i*2][j*2+1]
				                       + pixelValues[i*2+1][j*2]
				                       + pixelValues[i*2+1][j*2])/contractivity*contractivity);
			}
		}
		
		this.width = newWidth;
		this.height = newHeight;
		this.pixelValues = newPixelValues;
	}
}
