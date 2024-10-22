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

import com.alcuras.web.negocio.dto.MensajeDTO;
import com.alcuras.web.negocio.manager.IMensajeManager;
import com.alcuras.web.negocio.manager.MensajeManager;
import com.alcuras.web.negocio.utils.Utils;

@Controller
@RequestMapping("/*comentarios*")
public class MensajeController {
	    
	@Autowired
	protected IMensajeManager mensajeManager;
	   
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
			return mensajeList(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.ADMIN.toLowerCase())!=-1){
			return mensajeListAdmin(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.MANAGER.toLowerCase())!=-1){
			return mensajeListManager(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.NEW.toLowerCase())!=-1){
			return mensajeNew(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.UPDATE.toLowerCase())!=-1){
			return mensajeUpdate(arg0,arg1);
		}
		if (path.indexOf(ConstantsController.REMOVE.toLowerCase())!=-1){
			return mensajeRemove(arg0,arg1);
		} 
		return mensajeList(arg0,arg1);
		
	}
	
	private ModelAndView mensajeList(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		
		List<MensajeDTO> vMensaje = mensajeManager.getMensaje(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST);
		mav.addObject("vMensaje", vMensaje);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	
	private ModelAndView mensajeListAdmin(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		
		List<MensajeDTO> vMensaje = mensajeManager.getMensajeAdmin(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST_ADMIN);
		mav.addObject("vMensaje", vMensaje);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView mensajeListManager(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		
		List<MensajeDTO> vMensaje = mensajeManager.getMensajeAdmin(getType());

		ModelAndView mav = new ModelAndView(getView()+ConstantsController.LIST_MANAGER);
		mav.addObject("vMensaje", vMensaje);
		mav.addObject("path", getPath());
		
		return mav;

	}
	
	private ModelAndView mensajeNew(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = null;
		
		String menIdUsu = arg0.getParameter("menIdUsu");
		
		if (menIdUsu!=null){ // Recogemos de formulario para nuevo
			
			String menAsunto = arg0.getParameter("menAsunto");
			String menTexto = arg0.getParameter("menTexto");
			String fecha = Utils.getCurrentDateHora();
			Date menFecha = Utils.getDateHora(fecha);
	      	        
	        Integer menActivado = 1;
			
	        MensajeDTO mensaje = new MensajeDTO();
	        mensaje.setMenIdForo(getType());
	        mensaje.setMenAsunto(menAsunto);
	        mensaje.setMenTexto(menTexto);
	        mensaje.setMenIdUsu(menIdUsu);
	        mensaje.setMenActivado(menActivado);
	        mensaje.setMenFecha(menFecha);
	        
	        mensajeManager.create(mensaje);
	        
	         arg1.sendRedirect("/"+getPath());
	        
		} else { // Formulario para nuevo
			
			mav = new ModelAndView(getView()+ConstantsController.EDIT);
			
			mav.addObject("path", getPath());
		}
		
		return mav;

	}
	
	private ModelAndView mensajeUpdate(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		String nameParam = null;
		int indx = 0;
		Long id = null;
		MensajeDTO mensaje = null;
		Integer menActivado = 0;
		for (Enumeration params = arg0.getParameterNames(); params.hasMoreElements();) {
			nameParam = (String) params.nextElement();
			indx = nameParam.indexOf("idMensaje");
			if (indx != -1){
				id = Long.parseLong(nameParam.substring("idMensaje".length()));
				if (arg0.getParameter("menActivado"+id)!=null){
					menActivado = 1;
				} else {
					menActivado = 0;
				}
				
				mensaje = new MensajeDTO();
		        mensaje.setMenId(id);
		        mensaje.setMenActivado(menActivado);
					mensajeManager.update(mensaje);
			}
		}
	
		 arg1.sendRedirect("/"+getPath()+"-"+ConstantsController.MANAGER.toLowerCase());
		
		return null;

	}
	
	private ModelAndView mensajeRemove(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		String nameParam = null;
		int indx = 0;
		Long id = null;
		MensajeDTO mensaje = null;
		for (Enumeration params = arg0.getParameterNames(); params.hasMoreElements();) {
			nameParam = (String) params.nextElement();
			indx = nameParam.indexOf(ConstantsController.SELECTOR);
			if (indx != -1){
				id = Long.parseLong(nameParam.substring(ConstantsController.SELECTOR.length()));
				mensaje = mensajeManager.remove(id);
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
