package com.codecrocodile.club.image;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public interface ImageUtil {

	public String writeImage(InputStream imageInputStream, ImageInfo imageInfo);
	
	public String cropImage(String imageName, int x, int y, int width, int height);
	
	public BufferedImage getImage(String imageName);
	
}
