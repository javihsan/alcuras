package com.alcuras.web.server;

import java.io.IOException;

import com.alcuras.web.controllers.EventoController;
import com.google.appengine.tools.cloudstorage.GcsFileMetadata;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VCardServe extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		try {

			String agent = req.getHeader("user-agent");
			if (agent != null && agent.indexOf("iPhone") > -1) {
				// iPhone
				// Mozilla/5.0 (iPhone; CPU iPhone OS 6_0_1 like Mac OS X) AppleWebKit/536.26
				// (KHTML, like Gecko) Version/6.0 Mobile/10A525 Safari/8536.25
				String out = "https://maps.google.com/maps?ie=UTF8&q=Alcuras&gl=US&hl=es&hq=Alcuras&hnear=&t=h&z=16&vpsrc=0&iwloc=addr&amp;output=embed&cid=8290626191308617307";
				String search = "iPhone OS";
				int indx = agent.indexOf(search);
				if (indx != -1) {
					String strVersion = agent.substring(indx + search.length() + 1, indx + search.length() + 2);
					int version = Integer.parseInt(strVersion);
					if (version >= 6) {
						out = out.replaceAll(".google.", ".apple.");
					}
				}
				res.sendRedirect(out);

			} else {
				// Non-iPhone

				FileServe fs = new FileServe();
				String objectId = req.getParameter("objectId");
				String fileName = req.getParameter("fileName");
				GcsFileMetadata metadata = fs.getMetadata(objectId, EventoController.BUCKET_NAME);

				if (metadata != null) {

					res.setContentLength(new Long(metadata.getLength()).intValue());
					res.setHeader("content-type", metadata.getOptions().getMimeType());
					// res.setContentType("text/x-vcard");
					res.setHeader("content-disposition", "attachment; filename=" + fileName);

					fs.serve(objectId, res, EventoController.BUCKET_NAME);

				}
			}

		} catch (Exception e) {
		}

	}
}
