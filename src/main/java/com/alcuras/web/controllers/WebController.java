package com.alcuras.web.controllers;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alcuras.web.negocio.dao.WebDAO;
import com.alcuras.web.negocio.dto.WebDTO;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Text;

@Controller
@RequestMapping("/*vcard*")
public class WebController {

	@Autowired
	protected WebDAO webManager;
	 
	private Map viewMap;
	private String view;
	private String path;
	private Map paramMap;
	private String param;
	
	@RequestMapping("")
	private ModelAndView inicioInit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		String path = arg0.getRequestURI().toLowerCase();
		
		Set setKeys = getViewMap().keySet();
		for (Object key : setKeys) {
			if (path.indexOf((String)key)!=-1){
				setView((String)getViewMap().get(key));
				setParam((String)getParamMap().get(key));
				setPath((String)key);
				break;
			}
		}
	
		
		if (path.indexOf(ConstantsController.EDIT.toLowerCase())!=-1){
			return webEdit(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.UPDATE.toLowerCase())!=-1){
			return webUpdate(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.DOWNLOAD.toLowerCase())!=-1){
			return webDownload(arg0,arg1);
		}
		
		return webEdit(arg0,arg1);

	}
	
	private ModelAndView webEdit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		WebDTO web = webManager.getWebBBDDByWebParametro(getParam());
		
		if (web == null){ // New
			
			web = new WebDTO();
			web.setWebParametro(getParam());
	        
			web = webManager.create(web);
		}

		
		ModelAndView mav = new ModelAndView(getView()+ConstantsController.EDIT);
		
		mav.addObject("web", web);
		mav.addObject("urlRedirect", getPath()+"-"+ConstantsController.UPDATE.toLowerCase());
		mav.addObject("path", getPath());
		
		return mav;
	}
	
	private ModelAndView webUpdate(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Long webId = Long.valueOf(arg0.getParameter("webId"));

		String webValorFile = (String)arg0.getAttribute("webValorFile");
		if (arg0.getParameter("webValorFileDelete")!=null){
			webValorFile = "";
		}	
		Text webValorTextEs = arg0.getParameter("webValorTextEs")!=null?new Text(arg0.getParameter("webValorTextEs")):null;
		Text webValorTextEn = arg0.getParameter("webValorTextEn")!=null?new Text(arg0.getParameter("webValorTextEn")):null;
		Text webValorTextDe = arg0.getParameter("webValorTextDe")!=null?new Text(arg0.getParameter("webValorTextDe")):null;
		
		Integer artActivado = 0;
		if (arg0.getParameter("webActivado")!=null){
			artActivado = 1;
		}
		
		WebDTO web = new WebDTO();
		web.setWebId(webId);
        web.setWebValorFile(webValorFile);
        web.setWebValorTextEs(webValorTextEs);
        web.setWebValorTextEn(webValorTextEn);
        web.setWebValorTextDe(webValorTextDe);
        web.setWebActivado(artActivado);
        
        web = webManager.update(web);
        
        if (arg0.getParameter("webValorFileDelete")!=null){
			if (web.getWebValorFile()!=null){
				BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); 
				blobstoreService.delete(new BlobKey(web.getWebValorFile()));
			}
		}
        
         arg1.sendRedirect("/"+getPath());
	        
		return null;
	}
	
	private ModelAndView webDownload(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		WebDTO web = webManager.getWebByWebParametro(getParam());
        
		if (web != null && web.getWebValorFile()!= null && web.getWebValorFile()!= ""){ 
			
			 arg1.sendRedirect("/"+"vcardServe?blob-key="+web.getWebValorFile());
		}
	        
		return null;
	}
	
	public Map getViewMap() {
		return viewMap;
	}

	public void setViewMap(Map viewMap) {
		this.viewMap = viewMap;
	}

	
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	
}
