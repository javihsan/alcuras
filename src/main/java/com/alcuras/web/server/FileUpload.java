package com.alcuras.web.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;

import com.alcuras.web.controllers.EventoController;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.google.cloud.RetryHelper.RetryHelperException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FileUpload extends HttpServlet {

	private static final String ENCODING_UTF8 = "UTF-8";

	private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
			.initialRetryDelayMillis(10).retryMaxAttempts(10).totalRetryPeriodMillis(15000).build());
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

 
		try {
			var factory = DiskFileItemFactory.builder().get();
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

			String urlRedirect = req.getParameter("urlRedirect");

			// Parse the request
			List<FileItem> items = upload.parseRequest(req);

			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.isFormField()) {
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString();
						req.setAttribute(name, value);
						if (name.equals("urlRedirect")) {
							urlRedirect = value;
						}
					}
				} else {
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String idStore = generateId(fileName);
					String contentType = item.getContentType();
					req.setAttribute(fieldName, idStore);
					req.setAttribute(fieldName + "Name", fileName);
					store(idStore, contentType, IOUtils.toByteArray(item.getInputStream()),
								EventoController.BUCKET_NAME);
				}
			}

			ServletContext sc = super.getServletConfig().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/" + urlRedirect);
			rd.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void store(String objectId, String contentType, byte[] data, final String bucketName) throws IOException {
		try {
			GcsOutputChannel outputChannel = this.gcsService.createOrReplace(getFilename(objectId, bucketName),
					new GcsFileOptions.Builder().contentDisposition(objectId).mimeType(contentType).build());
			outputChannel.write(ByteBuffer.wrap(data));
			outputChannel.close();
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

	private String generateId(String filename) throws IOException {
		String fileNameEncode = new String(
				org.apache.geronimo.mail.util.Base64.encode(filename.getBytes(ENCODING_UTF8)));
		return UUID.randomUUID() + "" + (new DateTime()).toString("yyyyMMddHHmmss") + ""
				+ fileNameEncode.replace(".", "__");
	}
}
