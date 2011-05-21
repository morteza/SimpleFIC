package edu.fic;

public interface Constants {
	public final int[][] dihedralAffineTransforms = {	{ 1, 0, 0, 1 },
														{ 1, 0, 0,-1 },
														{-1, 0, 0, 1 }, 
														{-1, 0, 0,-1 }, 
														{ 0, 1, 1, 0 }, 
														{ 0, 1,-1, 0 },
														{ 0,-1, 1, 0 }, 
														{ 0,-1,-1, 0 } };

	public final String ENCODER_VERSION	=	"1";
	public final short MAX_PIXEL_VALUE 	=	255;
	public final int NUMBER_OF_DECODER_ITERATIONS	=	1;
}
