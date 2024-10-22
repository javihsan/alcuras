package com.alcuras.web.server;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alcuras.web.controllers.EventoController;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.tools.cloudstorage.GcsFileMetadata;

public class ImageServe extends HttpServlet {

	private final static ImagesService imagesService = ImagesServiceFactory.getImagesService();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		FileServe fs = new FileServe();
		
		try {
			String objectId = req.getParameter("objectId");

			if (req.getParameter("x") != null) {
 
				BufferedOutputStream outputStream = new BufferedOutputStream(res.getOutputStream());
				int xImage = Integer.parseInt(req.getParameter("x"));
				int yImage = Integer.parseInt(req.getParameter("y"));
				try {
					byte[] imageBytes = fs.fetchFully(objectId, EventoController.BUCKET_NAME);
					Image oldImage = ImagesServiceFactory.makeImage(imageBytes);
					String fileName = req.getParameter("fileName");
					Transform resize = ImagesServiceFactory.makeResize(xImage, yImage);
					Image newImage = imagesService.applyTransform(resize, oldImage);

					res.setHeader("content-disposition", "attachment; filename=" + fileName);
					
					byte[] newImageData = newImage.getImageData();

					outputStream.write(newImageData, 0, newImageData.length);
					outputStream.flush();

				} catch (Exception e) {
				} finally {
					outputStream.close();
				}
			} else {
				String fileName = req.getParameter("fileName");
				GcsFileMetadata metadata = fs.getMetadata(objectId, EventoController.BUCKET_NAME);

				if ( metadata != null) {

					res.setContentLength(new Long(metadata.getLength()).intValue());
					res.setHeader("content-type", metadata.getOptions().getMimeType());
					res.setHeader("content-disposition", "attachment; filename=" + fileName);

					fs.serve(objectId, res, EventoController.BUCKET_NAME);				
					
				}
			}
		} catch (Exception e) {
		}

	}
}
