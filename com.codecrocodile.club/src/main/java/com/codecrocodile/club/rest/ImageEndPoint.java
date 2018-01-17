package com.codecrocodile.club.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.codecrocodile.club.image.ImageUtil;
import com.codecrocodile.club.image.LocalImageUtil;

@Path("/image")
@Component
public class ImageEndPoint {
	
	@GET
	@Path("/{imageId}")
	@Produces({"image/jpeg, image/png"})
	public Response image(@PathParam(value = "imageId") String imageId) {
		ImageUtil imageUtil = new LocalImageUtil();
		
		System.out.println("image request = " + imageId);
		
		BufferedImage image = imageUtil.getImage(imageId);
        if (image != null) {
 
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                ImageIO.write((BufferedImage) image, "png", out);
 
                final byte[] imgData = out.toByteArray();
 
                final InputStream bigInputStream =
                      new ByteArrayInputStream(imgData);
 
                return Response.ok(bigInputStream).build();
            }
            catch (final IOException e) {
                return Response.noContent().build();
            }
        }
	
		return Response.noContent().build();
	}

}
