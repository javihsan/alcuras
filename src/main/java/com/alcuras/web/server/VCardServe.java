package com.alcuras.web.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class VCardServe extends HttpServlet {
	private final static BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	
	private final static BlobInfoFactory blobInfoFactory = new
	 BlobInfoFactory(DatastoreServiceFactory.getDatastoreService());

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		try {
			
			String agent = req.getHeader("user-agent");
			if (agent != null && agent.indexOf("iPhone") > -1) {
				// iPhone
				//Mozilla/5.0 (iPhone; CPU iPhone OS 6_0_1 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A525 Safari/8536.25
				String out = "https://maps.google.com/maps?ie=UTF8&q=Alcuras&gl=US&hl=es&hq=Alcuras&hnear=&t=h&z=16&vpsrc=0&iwloc=addr&amp;output=embed&cid=8290626191308617307";
				String search = "iPhone OS";
				int indx = agent.indexOf(search);
				if (indx!=-1){
					String strVersion = agent.substring(indx+search.length()+1,indx+search.length()+2);
					int version = Integer.parseInt(strVersion);
					if (version>=6){
						out = out.replaceAll(".google.", ".apple.");
					}
				}
				res.sendRedirect(out);
 
			} else {
				// Non-iPhone
						
				BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
				BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
				res.setContentLength(new Long(blobInfo.getSize()).intValue());
				res.setHeader("content-disposition", "attachment; filename="
						+ blobInfo.getFilename());
				res.setContentType("text/x-vcard");
				blobstoreService.serve(blobKey, res);
			}
			
		} catch (Exception e) {
		}

	}
}
