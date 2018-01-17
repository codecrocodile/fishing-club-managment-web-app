package com.codecrocodile.club.rest;

import java.io.InputStream;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.codecrocodile.club.image.ImageInfo;
import com.codecrocodile.club.image.ImageUtil;
import com.codecrocodile.club.image.LocalImageUtil;

@Path("/file")
@Component
public class UploadFileEndPoint {
	
	Logger log = Logger.getLogger(UploadFileEndPoint.class.getName());
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public String   uploadFile(
		@FormDataParam("photo") InputStream uploadedInputStream,
		@FormDataParam("photo") FormDataContentDisposition fileDetail) {

		String imageUrl = writeImage(uploadedInputStream, fileDetail);
		
		JSONObject json = new JSONObject();
		try {
			json.put("success", true);
			json.put("msg", "Image Uploaded");
			json.put("imageUrl", imageUrl);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json.toString();
	}
 
	private String writeImage(InputStream imageInputStream, FormDataContentDisposition fileDetail) {
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImageName(fileDetail.getFileName());
		ImageUtil imageWriter = new LocalImageUtil();
		return imageWriter.writeImage(imageInputStream, imageInfo);
	}
	
	@GET
	@Path("/crop")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String cropImage(
			@QueryParam("imageName") String imageName, 
			@QueryParam("x") int x, 
			@QueryParam("y") int y, 
			@QueryParam("width") int width, 
			@QueryParam("height") int height) {
		
		ImageUtil imageWriter = new LocalImageUtil();
		return imageWriter.cropImage(imageName, x, y, width, height);
	}
}