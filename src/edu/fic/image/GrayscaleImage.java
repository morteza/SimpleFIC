package edu.fic.image;

public class GrayscaleImage {
	public short[][] pixelValues;
	private int width;
	private int height;
	
	public GrayscaleImage(int width, int height){
		setWidth(width);
		setHeight(height);
		pixelValues = new short[width][height];
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
