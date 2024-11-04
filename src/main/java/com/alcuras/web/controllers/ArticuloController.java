package com.alcuras.web.controllers;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alcuras.web.negocio.dto.ArticuloDTO;
import com.alcuras.web.negocio.manager.IArticuloManager;
import com.alcuras.web.negocio.utils.Utils;
import com.google.appengine.api.datastore.Text;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/*articulos*")
public class ArticuloController {
	    
	@Autowired
	protected IArticuloManager articuloManager;
	
	private Map viewMap;
	private String view;
	private String path;
	private Map typeMap;
	private long type;
 
	@RequestMapping("")
	private ModelAndView inicioInit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
	
		String path = arg0.getRequestURI().toLowerCase();
		
		Set setKeys = getViewMap().keySet();
		for (Object key : setKeys) {
			if (path.indexOf((String)key)!=-1){
				setView((String)getViewMap().get(key));
				setPath((String)key);
				setType(Long.valueOf((String)getTypeMap().get(key)));
				break;
			}
		}
		
		if (path.indexOf(ConstantsController.LIST.toLowerCase())!=-1){
			return articuloList(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.ADMIN.toLowerCase())!=-1){
			return articuloListAdmin(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.MANAGER.toLowerCase())!=-1){
			return articuloListManager(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.VIEW.toLowerCase())!=-1){
			return articuloView(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.NEW.toLowerCase())!=-1){
			return articuloNew(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.EDIT.toLowerCase())!=-1){
			return articuloEdit(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.UPDATE.toLowerCase())!=-1){
			return articuloUpdate(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.REMOVE.toLowerCase())!=-1){
			return articuloRemove(arg0,arg1);
		} 
		
		return articuloList(arg0,arg1);
		

	}
	
	private ModelAndView articuloList(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		List<ArticuloDTO> vArticulo = articuloManager.getArticulo(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST);
		mav.addObject("vArticulo", vArticulo);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView articuloListAdmin(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		List<ArticuloDTO> vArticulo = articuloManager.getArticuloAdmin(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST_ADMIN);
		mav.addObject("vArticulo", vArticulo);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView articuloListManager(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		List<ArticuloDTO> vArticulo = articuloManager.getArticuloAdmin(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST_MANAGER);
		mav.addObject("vArticulo", vArticulo);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView articuloNew(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = null;
		
		String artIdUsu = (String)arg0.getAttribute("artIdUsu");
		
		if (artIdUsu!=null){ // Recogemos de formulario para nuevo
			
			String fecha = (String)arg0.getAttribute("artFecha");
			Date artFecha = Utils.getDate(fecha);

			String artAsuntoEs = (String)arg0.getAttribute("artAsuntoEs");
			if (arg0.getCharacterEncoding()!=null){
				artAsuntoEs = new String(((String)arg0.getAttribute("artAsuntoEs")).getBytes("iso-8859-1"), "UTF-8");
			}
			String artAsuntoEn = (String)arg0.getAttribute("artAsuntoEn");
			if (arg0.getCharacterEncoding()!=null){
				artAsuntoEn = new String(((String)arg0.getAttribute("artAsuntoEn")).getBytes("iso-8859-1"), "UTF-8");
			}
			String artAsuntoDe = (String)arg0.getAttribute("artAsuntoDe");
			if (arg0.getCharacterEncoding()!=null){
				artAsuntoDe = new String(((String)arg0.getAttribute("artAsuntoDe")).getBytes("iso-8859-1"), "UTF-8");
			}
			String strArtTextoEs = (String)arg0.getAttribute("artTextoEs");
			Text artTextoEs = null;
			if (strArtTextoEs!=null){
				if (arg0.getCharacterEncoding()!=null){
					strArtTextoEs = new String(strArtTextoEs.getBytes("iso-8859-1"), "UTF-8");
				}
				artTextoEs = new Text(strArtTextoEs);
			}			
			String strArtTextoEn = (String)arg0.getAttribute("artTextoEn");
			Text artTextoEn = null;
			if (strArtTextoEn!=null){
				if (arg0.getCharacterEncoding()!=null){
					strArtTextoEn = new String(strArtTextoEn.getBytes("iso-8859-1"), "UTF-8");
				}
				artTextoEn = new Text(strArtTextoEn);
			}
			String strArtTextoDe = (String)arg0.getAttribute("artTextoDe");
			Text artTextoDe = null;
			if (strArtTextoDe!=null){
				if (arg0.getCharacterEncoding()!=null){
					strArtTextoDe = new String(strArtTextoDe.getBytes("iso-8859-1"), "UTF-8");
				}
				artTextoDe = new Text(strArtTextoDe);
			}
			
	        Integer artActivado = 0;
			if (arg0.getAttribute("artActivado")!=null){
				artActivado = 1;
			}
	        
			String artFichero = (String)arg0.getAttribute("artFichero");
			String artFicheroName = (String) arg0.getAttribute("artFicheroName");
			String artImagen = (String)arg0.getAttribute("artImagen");
			String artImagenName = (String) arg0.getAttribute("artImagenName");
			
			String artLink = (String)arg0.getAttribute("artLink");

			int artTipoLink = Integer.parseInt((String)arg0.getAttribute("artTipoLink"));
			
	        ArticuloDTO articulo = new ArticuloDTO();
	        articulo.setArtIdForo(getType());
	        articulo.setArtAsuntoEs(artAsuntoEs);
	        articulo.setArtAsuntoEn(artAsuntoEn);
	        articulo.setArtAsuntoDe(artAsuntoDe);
	        articulo.setArtTextoEs(artTextoEs);
	        articulo.setArtTextoEn(artTextoEn);
	        articulo.setArtTextoDe(artTextoDe);
	        articulo.setArtIdUsu(artIdUsu);
	        articulo.setArtActivado(artActivado);
	        articulo.setArtFecha(artFecha);
	        articulo.setArtFichero(artFichero);
	        articulo.setArtFicheroName(artFicheroName);
	        articulo.setArtImagen(artImagen);
	        articulo.setArtImagenName(artImagenName);
	        articulo.setArtLink(artLink);
	        articulo.setArtTipoLink(artTipoLink);
	        
	        articuloManager.create(articulo);
	        
	         arg1.sendRedirect("/"+getPath()+"-"+ConstantsController.MANAGER.toLowerCase());
	        
		} else { // Formulario para nuevo
			
			mav = new ModelAndView(getView()+ConstantsController.EDIT);
			
			mav.addObject("urlRedirect", getPath()+"-"+ConstantsController.NEW.toLowerCase());
			mav.addObject("path", getPath());
		}
		
		return mav;

	}
	
	private ModelAndView articuloView(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Long id = Long.parseLong(arg0.getParameter("id"));
		
		ArticuloDTO articulo = articuloManager.getById(id);

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.VIEW);
		mav.addObject("articulo", articulo);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView articuloEdit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		Long id = Long.parseLong(arg0.getParameter("id"));
		
		ArticuloDTO articulo = articuloManager.getById(id);
		
		ModelAndView mav = new ModelAndView(getView()+ConstantsController.EDIT);
		
		mav.addObject("articulo", articulo);
		mav.addObject("urlRedirect", getPath()+"-"+ConstantsController.UPDATE.toLowerCase());
		mav.addObject("path", getPath());
		
		return mav;
	}
	
	private ModelAndView articuloUpdate(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Long artId = Long.valueOf((String)arg0.getAttribute("artId"));

		String artAsuntoEs = (String)arg0.getAttribute("artAsuntoEs");
		if (arg0.getCharacterEncoding()!=null){
			artAsuntoEs = new String(((String)arg0.getAttribute("artAsuntoEs")).getBytes("iso-8859-1"), "UTF-8");
		}
		String artAsuntoEn = (String)arg0.getAttribute("artAsuntoEn");
		if (arg0.getCharacterEncoding()!=null){
			artAsuntoEn = new String(((String)arg0.getAttribute("artAsuntoEn")).getBytes("iso-8859-1"), "UTF-8");
		}
		String artAsuntoDe = (String)arg0.getAttribute("artAsuntoDe");
		if (arg0.getCharacterEncoding()!=null){
			artAsuntoDe = new String(((String)arg0.getAttribute("artAsuntoDe")).getBytes("iso-8859-1"), "UTF-8");
		}
		String strArtTextoEs = (String)arg0.getAttribute("artTextoEs");
		Text artTextoEs = null;
		if (strArtTextoEs!=null){
			if (arg0.getCharacterEncoding()!=null){
				strArtTextoEs = new String(strArtTextoEs.getBytes("iso-8859-1"), "UTF-8");
			}
			artTextoEs = new Text(strArtTextoEs);
		}			
		String strArtTextoEn = (String)arg0.getAttribute("artTextoEn");
		Text artTextoEn = null;
		if (strArtTextoEn!=null){
			if (arg0.getCharacterEncoding()!=null){
				strArtTextoEn = new String(strArtTextoEn.getBytes("iso-8859-1"), "UTF-8");
			}
			artTextoEn = new Text(strArtTextoEn);
		}
		String strArtTextoDe = (String)arg0.getAttribute("artTextoDe");
		Text artTextoDe = null;
		if (strArtTextoDe!=null){
			if (arg0.getCharacterEncoding()!=null){
				strArtTextoDe = new String(strArtTextoDe.getBytes("iso-8859-1"), "UTF-8");
			}
			artTextoDe = new Text(strArtTextoDe);
		}
		
		String fecha = (String)arg0.getAttribute("artFecha");
		Date artFecha = Utils.getDate(fecha);
		String artIdUsu = (String)arg0.getAttribute("artIdUsu");
		
		Integer artActivado = 0;
		if (arg0.getAttribute("artActivado")!=null){
			artActivado = 1;
		}
        
		String artFichero = (String)arg0.getAttribute("artFichero");
		String artFicheroName = (String) arg0.getAttribute("artFicheroName");
		String artImagen = (String)arg0.getAttribute("artImagen");
		String artImagenName = (String) arg0.getAttribute("artImagenName");
		if (arg0.getAttribute("artImagenDelete")!=null){
			artImagen = "";
			artImagenName = "";
		}		
		String artLink = (String)arg0.getAttribute("artLink");

		int artTipoLink = Integer.parseInt((String)arg0.getAttribute("artTipoLink"));
		
		
		ArticuloDTO articulo = new ArticuloDTO();
		articulo.setArtId(artId);
        articulo.setArtIdUsu(artIdUsu);
        articulo.setArtAsuntoEs(artAsuntoEs);
        articulo.setArtAsuntoEn(artAsuntoEn);
        articulo.setArtAsuntoDe(artAsuntoDe);
        articulo.setArtTextoEs(artTextoEs);
        articulo.setArtTextoEn(artTextoEn);
        articulo.setArtTextoDe(artTextoDe);
        articulo.setArtActivado(artActivado);
        articulo.setArtFecha(artFecha);
        articulo.setArtFicheroName(artFicheroName);
        articulo.setArtFichero(artFichero);
        articulo.setArtImagen(artImagen);
        articulo.setArtImagenName(artImagenName);
        articulo.setArtLink(artLink);
        articulo.setArtTipoLink(artTipoLink);
        
        articulo = articuloManager.update(articulo);
        
        if (arg0.getAttribute("artImagenDelete")!=null){
			if (articulo.getArtImagen()!=null){
				new EventoController().delete(articulo.getArtImagen());
			}
		}
        
         arg1.sendRedirect("/"+getPath()+"-"+ConstantsController.MANAGER.toLowerCase());
        	        
		return null;
	}
	
	private ModelAndView articuloRemove(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		String nameParam = null;
		int indx = 0;
		Long id = null;
		ArticuloDTO articulo = null;
		for (Enumeration params = arg0.getParameterNames(); params.hasMoreElements();) {
			nameParam = (String) params.nextElement();
			indx = nameParam.indexOf(ConstantsController.SELECTOR);
			if (indx != -1){
				id = Long.parseLong(nameParam.substring(ConstantsController.SELECTOR.length()));
				articulo = articuloManager.remove(id);
				if (articulo.getArtFichero()!=null){
					new EventoController().delete(articulo.getArtFichero());
				}
				if (articulo.getArtImagen()!=null){
					new EventoController().delete(articulo.getArtImagen());
				}
			}
		}
		
		 arg1.sendRedirect("/"+getPath()+"-"+ConstantsController.ADMIN.toLowerCase());
		
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

	public Map getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map typeMap) {
		this.typeMap = typeMap;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}
}
