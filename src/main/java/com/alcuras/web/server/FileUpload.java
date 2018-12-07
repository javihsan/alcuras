package com.alcuras.web.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class FileUpload extends HttpServlet {
	private final static BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	private final static BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
	//static Log logger = LogFactory.getLog(FileUpload.class);
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		try {
			
			Map<String, java.util.List<BlobKey>> blobs = blobstoreService.getUploads(req);
			java.util.List<BlobKey> blobsKey = null;
			BlobKey blobKey = null;
		    BlobInfo blobInfo = null;
			for (String key : blobs.keySet()) {
				blobsKey = blobs.get(key);
				blobKey = blobsKey.get(0);
				blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
				if (blobInfo != null && blobInfo.getSize()>0){
					req.setAttribute(key, blobKey.getKeyString());
					//logger.error("req.setAttribute: " + key + " :"+ blobKey.getKeyString());
				}
			}
			
			String urlRedirect = req.getParameter("urlRedirect");
			
			ServletContext sc = super.getServletConfig().getServletContext(); 
			RequestDispatcher rd = sc.getRequestDispatcher("/"+urlRedirect);
			rd.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
