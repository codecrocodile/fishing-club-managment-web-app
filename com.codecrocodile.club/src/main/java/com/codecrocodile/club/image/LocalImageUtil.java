package com.codecrocodile.club.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;

public class LocalImageUtil implements ImageUtil {
	
	private String openshiftDataDir = System.getenv("OPENSHIFT_DATA_DIR");
	private String memberProfilePhotoDir = openshiftDataDir + System.getProperty("file.separator") + "MemberProfilePhotos";
	
	@Override
	public String writeImage(InputStream imageInputStream, ImageInfo imageInfo) {
		
		try {
			File folder = new File(memberProfilePhotoDir);
			if (!folder.exists()) {
				folder.mkdir();
			}
					
			File f = new File(memberProfilePhotoDir + System.getProperty("file.separator") +  imageInfo.getImageName());
			
			f.createNewFile();
			OutputStream out = new FileOutputStream(f);
			int read = 0;
			byte[] bytes = new byte[1024];
 
			while ((read = imageInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			
			return imageInfo.getImageName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String cropImage(String imageName, int x, int y, int width, int height) {
		try {
			File imageFile = new File(memberProfilePhotoDir + System.getProperty("file.separator") + imageName);
			File croppedImageFile = new File(memberProfilePhotoDir + System.getProperty("file.separator") + imageName);
			BufferedImage originalImage = Imaging.getBufferedImage(imageFile);
			BufferedImage croppedImage = this.crop(originalImage, x, y, width, height);
			
			ImageFormat format = ImageFormats.PNG;
			Map<String, Object> optionalParams = new HashMap<String, Object>();
			Imaging.writeImage(croppedImage, croppedImageFile, format, optionalParams);
			
		} catch (ImageReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ImageWriteException e) {
			e.printStackTrace();
		}
		return imageName;
	}
	
	public BufferedImage crop(BufferedImage image, int startX, int startY, int endX, int endY) {
		BufferedImage img = image.getSubimage(startX, startY, endX, endY); //fill in the corners of the desired crop location here
		BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = copyOfImage.createGraphics();
		g.drawImage(img, 0, 0, null);
		return copyOfImage; 
	}

	@Override
	public BufferedImage getImage(String imageName) {
		File croppedImageFile = new File(memberProfilePhotoDir + System.getProperty("file.separator") + imageName);
		try {
			return Imaging.getBufferedImage(croppedImageFile);
		} catch (ImageReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
