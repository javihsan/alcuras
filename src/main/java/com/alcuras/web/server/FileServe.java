package com.alcuras.web.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.alcuras.web.controllers.EventoController;
import com.google.appengine.tools.cloudstorage.GcsFileMetadata;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryHelperException;
import com.google.appengine.tools.cloudstorage.RetryParams;

public class FileServe extends HttpServlet {

	private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
			.initialRetryDelayMillis(10).retryMaxAttempts(10).totalRetryPeriodMillis(15000).build());

	private static final int BUFFER_SIZE = 2 * 1024 * 1024;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		try {

			String objectId = req.getParameter("objectId");
			String fileName = req.getParameter("fileName");
			GcsFileMetadata metadata = getMetadata(objectId, EventoController.BUCKET_NAME);

			if ( metadata != null) {

				res.setContentLength(new Long(metadata.getLength()).intValue());
				res.setHeader("content-type", metadata.getOptions().getMimeType());
				res.setHeader("content-disposition", "attachment; filename=" + fileName);

				serve(objectId, res, EventoController.BUCKET_NAME);				
				
			}

		} catch (Exception e) {
		}

	}

	public void serve(String objectId, HttpServletResponse response, final String bucketName) throws IOException {
		try {
			GcsInputChannel readChannel = this.gcsService.openPrefetchingReadChannel(getFilename(objectId, bucketName),
					0, BUFFER_SIZE);
			IOUtils.copy(Channels.newInputStream(readChannel), response.getOutputStream());
		} catch (RetryHelperException e) {
			throw new IOException(e);
		}
	}

	private GcsFilename getFilename(String objectId, final String bucketName) {
		if (objectId == null) {
			throw new IllegalArgumentException("objectId null");
		}
		if (bucketName == null) {
			throw new IllegalArgumentException("bucketName null");
		}
		return new GcsFilename(bucketName, objectId);
	}

	public GcsFileMetadata getMetadata(String objectId, final String bucketName) throws IOException {
		try {
			GcsFilename filename = getFilename(objectId, bucketName);
			return this.gcsService.getMetadata(filename);

		} catch (RetryHelperException e) {
			throw new IOException(e);
		}
	}
	
	 public byte[] fetchFully(String objectId, final String bucketName)
	            throws IOException {
	        try {
	            GcsFilename filename = getFilename(objectId, bucketName);
	            GcsFileMetadata metadata = this.gcsService.getMetadata(filename);
	            
	            int fileSize = (int) metadata.getLength();
	            ByteBuffer result = ByteBuffer.allocate(fileSize);
	            GcsInputChannel readChannel = null;
	            try {
	                readChannel = this.gcsService.openReadChannel(filename, 0);
	                readChannel.read(result);
	            } finally {
	                if (readChannel != null) {
	                    readChannel.close();
	                }
	            }
	            return result.array();
	        } catch (RetryHelperException e) {
	            throw new IOException(e);
	        }
	    }
}
