package com.alcuras.web.controllers;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alcuras.web.negocio.dao.EventoDAO;
import com.alcuras.web.negocio.dto.EventoDTO;
import com.alcuras.web.negocio.utils.Utils;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Text;

@Controller
@RequestMapping("/*eventos*")
public class EventoController {

	@Autowired
	protected EventoDAO eventoManager;
	
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
			return eventoList(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.ADMIN.toLowerCase())!=-1){
			return eventoListAdmin(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.MANAGER.toLowerCase())!=-1){
			return eventoListManager(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.VIEW.toLowerCase())!=-1){
			return eventoView(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.NEW.toLowerCase())!=-1){
			return eventoNew(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.EDIT.toLowerCase())!=-1){
			return eventoEdit(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.UPDATE.toLowerCase())!=-1){
			return eventoUpdate(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.REMOVE.toLowerCase())!=-1){
			return eventoRemove(arg0,arg1);
		}
		
		return eventoList(arg0,arg1);
		

	}
	
	private ModelAndView eventoList(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		
		List<EventoDTO> vEvento = eventoManager.getEvento(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST);
		mav.addObject("vEvento", vEvento);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView eventoListAdmin(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		
		List<EventoDTO> vEvento = eventoManager.getEventoAdmin(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST_ADMIN);
		mav.addObject("vEvento", vEvento);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView eventoListManager(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		
		List<EventoDTO> vEvento = eventoManager.getEventoAdmin(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST_MANAGER);
		mav.addObject("vEvento", vEvento);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView eventoNew(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = null;
		
		String eveIdUsu = arg0.getParameter("eveIdUsu");
		
		if (eveIdUsu!=null){ // Recogemos de formulario para nuevo
			
			String fechaDesde = arg0.getParameter("eveFechaDesde");
			String fechaHHDesde = arg0.getParameter("eveFechaDesdeHH");
			String fechaMMDesde = arg0.getParameter("eveFechaDesdeMM");
			Date eveFechaDesde = Utils.getDateHora(fechaDesde + " " + fechaHHDesde + ":" + fechaMMDesde);
			
			String fechaHasta = arg0.getParameter("eveFechaHasta");
			String fechaHHHasta = arg0.getParameter("eveFechaHastaHH");
			String fechaMMHasta = arg0.getParameter("eveFechaHastaMM");
			Date eveFechaHasta = Utils.getDateHora(fechaHasta + " " + fechaHHHasta + ":" + fechaMMHasta);
			
			String eveAsuntoEs = arg0.getParameter("eveAsuntoEs");
			if (arg0.getCharacterEncoding()!=null){
				eveAsuntoEs = new String(arg0.getParameter("eveAsuntoEs").getBytes(
						"iso-8859-1"), "UTF-8");
			}
			String eveAsuntoEn = arg0.getParameter("eveAsuntoEn");
			if (arg0.getCharacterEncoding()!=null){
				eveAsuntoEn = new String(arg0.getParameter("eveAsuntoEn").getBytes(
					"iso-8859-1"), "UTF-8");
			}
			String eveAsuntoDe = arg0.getParameter("eveAsuntoDe");
			if (arg0.getCharacterEncoding()!=null){
				eveAsuntoDe = new String(arg0.getParameter("eveAsuntoDe").getBytes(
					"iso-8859-1"), "UTF-8");
			}
			String strEveTextoEs = arg0.getParameter("eveTextoEs");
			Text eveTextoEs = null;
			if (strEveTextoEs!=null){
				if (arg0.getCharacterEncoding()!=null){
					strEveTextoEs = new String(strEveTextoEs.getBytes("iso-8859-1"), "UTF-8");
				}
				eveTextoEs = new Text(strEveTextoEs);
			}			
			String strEveTextoEn = arg0.getParameter("eveTextoEn");
			Text eveTextoEn = null;
			if (strEveTextoEn!=null){
				if (arg0.getCharacterEncoding()!=null){
					strEveTextoEn = new String(strEveTextoEn.getBytes("iso-8859-1"), "UTF-8");
				}
				eveTextoEn = new Text(strEveTextoEn);
			}
			String strEveTextoDe = arg0.getParameter("eveTextoDe");
			Text eveTextoDe = null;
			if (strEveTextoDe!=null){
				if (arg0.getCharacterEncoding()!=null){
					strEveTextoDe = new String(strEveTextoDe.getBytes("iso-8859-1"), "UTF-8");
				}
				eveTextoDe = new Text(strEveTextoDe);
			}
			
	        Integer eveActivado = 0;
			if (arg0.getParameter("eveActivado")!=null){
				eveActivado = 1;
			}
	        
			String eveFichero = (String)arg0.getAttribute("eveFichero");
			String eveImagen = (String)arg0.getAttribute("eveImagen");
			
			String eveLink = (String)arg0.getParameter("eveLink");

			int eveTipoLink = Integer.parseInt(arg0.getParameter("eveTipoLink"));
			
	        EventoDTO evento = new EventoDTO();
	        evento.setEveIdForo(getType());
	        evento.setEveAsuntoEs(eveAsuntoEs);
	        evento.setEveAsuntoEn(eveAsuntoEn);
	        evento.setEveAsuntoDe(eveAsuntoDe);
	        evento.setEveTextoEs(eveTextoEs);
	        evento.setEveTextoEn(eveTextoEn);
	        evento.setEveTextoDe(eveTextoDe);
	        evento.setEveIdUsu(eveIdUsu);
	        evento.setEveActivado(eveActivado);
	        evento.setEveFechaDesde(eveFechaDesde);
	        evento.setEveFechaHasta(eveFechaHasta);
	        evento.setEveFichero(eveFichero);
	        evento.setEveImagen(eveImagen);
	        evento.setEveLink(eveLink);
	        evento.setEveTipoLink(eveTipoLink);
	        
	        
	        eventoManager.create(evento);
	        
	         arg1.sendRedirect("/"+getPath()+"-"+ConstantsController.MANAGER.toLowerCase());
	        
		} else { // Formulario para nuevo
			
			mav = new ModelAndView(getView()+ConstantsController.EDIT);
			
			mav.addObject("urlRedirect", getPath()+"-"+ConstantsController.NEW.toLowerCase());
			mav.addObject("path", getPath());
		}
		
		return mav;

	}
	
	private ModelAndView eventoView(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Long id = Long.parseLong(arg0.getParameter("id"));
		
		
		EventoDTO evento = eventoManager.getById(id);

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.VIEW);
		mav.addObject("evento", evento);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView eventoEdit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		Long id = Long.parseLong(arg0.getParameter("id"));
		
		
		EventoDTO evento = eventoManager.getById(id);
		
		ModelAndView mav = new ModelAndView(getView()+ConstantsController.EDIT);
		
		mav.addObject("evento", evento);
		mav.addObject("urlRedirect", getPath()+"-"+ConstantsController.UPDATE.toLowerCase());
		mav.addObject("path", getPath());
		
		return mav;
	}
	
	private ModelAndView eventoUpdate(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Long eveId = Long.valueOf(arg0.getParameter("eveId"));

		String eveAsuntoEs = arg0.getParameter("eveAsuntoEs");
		if (arg0.getCharacterEncoding()!=null){
			eveAsuntoEs = new String(arg0.getParameter("eveAsuntoEs").getBytes(
					"iso-8859-1"), "UTF-8");
		}
		String eveAsuntoEn = arg0.getParameter("eveAsuntoEn");
		if (arg0.getCharacterEncoding()!=null){
			eveAsuntoEn = new String(arg0.getParameter("eveAsuntoEn").getBytes(
				"iso-8859-1"), "UTF-8");
		}
		String eveAsuntoDe = arg0.getParameter("eveAsuntoDe");
		if (arg0.getCharacterEncoding()!=null){
			eveAsuntoDe = new String(arg0.getParameter("eveAsuntoDe").getBytes(
				"iso-8859-1"), "UTF-8");
		}
		String strEveTextoEs = arg0.getParameter("eveTextoEs");
		Text eveTextoEs = null;
		if (strEveTextoEs!=null){
			if (arg0.getCharacterEncoding()!=null){
				strEveTextoEs = new String(strEveTextoEs.getBytes("iso-8859-1"), "UTF-8");
			}
			eveTextoEs = new Text(strEveTextoEs);
		}			
		String strEveTextoEn = arg0.getParameter("eveTextoEn");
		Text eveTextoEn = null;
		if (strEveTextoEn!=null){
			if (arg0.getCharacterEncoding()!=null){
				strEveTextoEn = new String(strEveTextoEn.getBytes("iso-8859-1"), "UTF-8");
			}
			eveTextoEn = new Text(strEveTextoEn);
		}
		String strEveTextoDe = arg0.getParameter("eveTextoDe");
		Text eveTextoDe = null;
		if (strEveTextoDe!=null){
			if (arg0.getCharacterEncoding()!=null){
				strEveTextoDe = new String(strEveTextoDe.getBytes("iso-8859-1"), "UTF-8");
			}
			eveTextoDe = new Text(strEveTextoDe);
		}
		
		String fechaDesde = arg0.getParameter("eveFechaDesde");
		String fechaHHDesde = arg0.getParameter("eveFechaDesdeHH");
		String fechaMMDesde = arg0.getParameter("eveFechaDesdeMM");
		Date eveFechaDesde = Utils.getDateHora(fechaDesde + " " + fechaHHDesde + ":" + fechaMMDesde);
		
		String fechaHasta = arg0.getParameter("eveFechaHasta");
		String fechaHHHasta = arg0.getParameter("eveFechaHastaHH");
		String fechaMMHasta = arg0.getParameter("eveFechaHastaMM");
		Date eveFechaHasta = Utils.getDateHora(fechaHasta + " " + fechaHHHasta + ":" + fechaMMHasta);		
		
		String eveIdUsu = arg0.getParameter("eveIdUsu");
		
		Integer eveActivado = 0;
		if (arg0.getParameter("eveActivado")!=null){
			eveActivado = 1;
		}
        
		String eveFichero = (String)arg0.getAttribute("eveFichero");
		String eveImagen = (String)arg0.getAttribute("eveImagen");
		if (arg0.getParameter("eveImagenDelete")!=null){
			eveImagen = "";
		}		
		String eveLink = (String)arg0.getParameter("eveLink");
		
		int eveTipoLink = Integer.parseInt(arg0.getParameter("eveTipoLink"));
		
		
		EventoDTO evento = new EventoDTO();
		evento.setEveId(eveId);
        evento.setEveIdUsu(eveIdUsu);
        evento.setEveAsuntoEs(eveAsuntoEs);
        evento.setEveAsuntoEn(eveAsuntoEn);
        evento.setEveAsuntoDe(eveAsuntoDe);
        evento.setEveTextoEs(eveTextoEs);
        evento.setEveTextoEn(eveTextoEn);
        evento.setEveTextoDe(eveTextoDe);
        evento.setEveActivado(eveActivado);
        evento.setEveFechaDesde(eveFechaDesde);
        evento.setEveFechaHasta(eveFechaHasta);
        evento.setEveFichero(eveFichero);
        evento.setEveImagen(eveImagen);
        evento.setEveLink(eveLink);
        evento.setEveTipoLink(eveTipoLink);
        
        
        evento = eventoManager.update(evento);
        
        if (arg0.getParameter("eveImagenDelete")!=null){
			if (evento.getEveImagen()!=null){
				BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); 
				blobstoreService.delete(new BlobKey(evento.getEveImagen()));
			}
		}
        
         arg1.sendRedirect("/"+getPath()+"-"+ConstantsController.MANAGER.toLowerCase());
	        
		return null;
	}
	
	private ModelAndView eventoRemove(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		String nameParam = null;
		int indx = 0;
		Long id = null;
		EventoDTO evento = null;
		BlobstoreService blobstoreService = null;
		for (Enumeration params = arg0.getParameterNames(); params.hasMoreElements();) {
			nameParam = (String) params.nextElement();
			indx = nameParam.indexOf(ConstantsController.SELECTOR);
			if (indx != -1){
				id = Long.parseLong(nameParam.substring(ConstantsController.SELECTOR.length()));
				if (blobstoreService==null){
					blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
				}
				evento = eventoManager.remove(id);
				if (evento.getEveFichero()!=null){
					blobstoreService.delete(new BlobKey(evento.getEveFichero()));
				}
				if (evento.getEveImagen()!=null){
					blobstoreService.delete(new BlobKey(evento.getEveImagen()));
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
