package com.alcuras.web.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alcuras.web.negocio.dto.EventoDTO;
import com.alcuras.web.negocio.manager.IEventoManager;
import com.alcuras.web.negocio.utils.Utils;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.google.cloud.RetryHelper.RetryHelperException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/*eventos*")
public class EventoController {
   
	@Autowired
	protected IEventoManager eventoManager;
  
	private Map viewMap;
	private String view;
	private String path;
	private Map typeMap;
	private long type;

	public static final String BUCKET_NAME = "alcuras_documents";

	private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
			.initialRetryDelayMillis(10).retryMaxAttempts(10).totalRetryPeriodMillis(15000).build());

	@RequestMapping("")
	private ModelAndView inicioInit(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		String path = arg0.getRequestURI().toLowerCase();

		Set setKeys = getViewMap().keySet();
		for (Object key : setKeys) {
			if (path.indexOf((String) key) != -1) {
				setView((String) getViewMap().get(key));
				setPath((String) key);
				setType(Long.valueOf((String) getTypeMap().get(key)));
				break;
			}
		}

		if (path.indexOf(ConstantsController.LIST.toLowerCase()) != -1) {
			return eventoList(arg0, arg1);
		}
		if (path.indexOf(ConstantsController.ADMIN.toLowerCase()) != -1) {
			return eventoListAdmin(arg0, arg1);
		}
		if (path.indexOf(ConstantsController.MANAGER.toLowerCase()) != -1) {
			return eventoListManager(arg0, arg1);
		}
		if (path.indexOf(ConstantsController.VIEW.toLowerCase()) != -1) {
			return eventoView(arg0, arg1);
		}
		if (path.indexOf(ConstantsController.NEW.toLowerCase()) != -1) {
			return eventoNew(arg0, arg1);
		}
		if (path.indexOf(ConstantsController.EDIT.toLowerCase()) != -1) {
			return eventoEdit(arg0, arg1);
		}
		if (path.indexOf(ConstantsController.UPDATE.toLowerCase()) != -1) {
			return eventoUpdate(arg0, arg1);
		}
		if (path.indexOf(ConstantsController.REMOVE.toLowerCase()) != -1) {
			return eventoRemove(arg0, arg1);
		}

		return eventoList(arg0, arg1);

	}

	private ModelAndView eventoList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		List<EventoDTO> vEvento = eventoManager.getEvento(getType());

		ModelAndView mav = new ModelAndView(getView() + ConstantsController.LIST);
		mav.addObject("vEvento", vEvento);
		mav.addObject("path", getPath());

		return mav;

	}

	private ModelAndView eventoListAdmin(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		List<EventoDTO> vEvento = eventoManager.getEventoAdmin(getType());

		ModelAndView mav = new ModelAndView(getView() + ConstantsController.LIST_ADMIN);
		mav.addObject("vEvento", vEvento);
		mav.addObject("path", getPath());

		return mav;

	}

	private ModelAndView eventoListManager(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		List<EventoDTO> vEvento = eventoManager.getEventoAdmin(getType());

		ModelAndView mav = new ModelAndView(getView() + ConstantsController.LIST_MANAGER);
		mav.addObject("vEvento", vEvento);
		mav.addObject("path", getPath());

		return mav;

	}

	private ModelAndView eventoNew(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		ModelAndView mav = null;

		String eveIdUsu = (String)arg0.getAttribute("eveIdUsu");

		if (eveIdUsu != null) { // Recogemos de formulario para nuevo

			String fechaDesde = (String)arg0.getAttribute("eveFechaDesde");
			String fechaHHDesde = (String)arg0.getAttribute("eveFechaDesdeHH");
			String fechaMMDesde = (String)arg0.getAttribute("eveFechaDesdeMM");
			Date eveFechaDesde = Utils.getDateHora(fechaDesde + " " + fechaHHDesde + ":" + fechaMMDesde);

			String fechaHasta = (String)arg0.getAttribute("eveFechaHasta");
			String fechaHHHasta = (String)arg0.getAttribute("eveFechaHastaHH");
			String fechaMMHasta = (String)arg0.getAttribute("eveFechaHastaMM");
			Date eveFechaHasta = Utils.getDateHora(fechaHasta + " " + fechaHHHasta + ":" + fechaMMHasta);

			String eveAsuntoEs = (String)arg0.getAttribute("eveAsuntoEs");
			if (arg0.getCharacterEncoding() != null) {
				eveAsuntoEs = new String(((String)arg0.getAttribute("eveAsuntoEs")).getBytes("iso-8859-1"), "UTF-8");
			}
			String eveAsuntoEn = (String)arg0.getAttribute("eveAsuntoEn");
			if (arg0.getCharacterEncoding() != null) {
				eveAsuntoEn = new String(((String)arg0.getAttribute("eveAsuntoEn")).getBytes("iso-8859-1"), "UTF-8");
			}
			String eveAsuntoDe = (String)arg0.getAttribute("eveAsuntoDe");
			if (arg0.getCharacterEncoding() != null) {
				eveAsuntoDe = new String(((String)arg0.getAttribute("eveAsuntoDe")).getBytes("iso-8859-1"), "UTF-8");
			}
			String strEveTextoEs = (String)arg0.getAttribute("eveTextoEs");
			Text eveTextoEs = null;
			if (strEveTextoEs != null) {
				if (arg0.getCharacterEncoding() != null) {
					strEveTextoEs = new String(strEveTextoEs.getBytes("iso-8859-1"), "UTF-8");
				}
				eveTextoEs = new Text(strEveTextoEs);
			}
			String strEveTextoEn = (String)arg0.getAttribute("eveTextoEn");
			Text eveTextoEn = null;
			if (strEveTextoEn != null) {
				if (arg0.getCharacterEncoding() != null) {
					strEveTextoEn = new String(strEveTextoEn.getBytes("iso-8859-1"), "UTF-8");
				}
				eveTextoEn = new Text(strEveTextoEn);
			}
			String strEveTextoDe = (String)arg0.getAttribute("eveTextoDe");
			Text eveTextoDe = null;
			if (strEveTextoDe != null) {
				if (arg0.getCharacterEncoding() != null) {
					strEveTextoDe = new String(strEveTextoDe.getBytes("iso-8859-1"), "UTF-8");
				}
				eveTextoDe = new Text(strEveTextoDe);
			}

			Integer eveActivado = 0;
			if ((String)arg0.getAttribute("eveActivado") != null) {
				eveActivado = 1;
			}

			String eveFichero = (String)arg0.getAttribute("eveFichero");
			String eveFicheroName = (String)arg0.getAttribute("eveFicheroName");
			String eveImagen = (String)arg0.getAttribute("eveImagen");
			String eveImagenName = (String)arg0.getAttribute("eveImagenName");

			String eveLink = (String) (String)arg0.getAttribute("eveLink");

			int eveTipoLink = Integer.parseInt((String)arg0.getAttribute("eveTipoLink"));

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
			evento.setEveFicheroName(eveFicheroName);
			evento.setEveImagen(eveImagen);
			evento.setEveImagenName(eveImagenName);
			evento.setEveLink(eveLink);
			evento.setEveTipoLink(eveTipoLink);

			eventoManager.create(evento);

			arg1.sendRedirect("/" + getPath() + "-" + ConstantsController.MANAGER.toLowerCase());

		} else { // Formulario para nuevo

			mav = new ModelAndView(getView() + ConstantsController.EDIT);

			mav.addObject("urlRedirect", getPath() + "-" + ConstantsController.NEW.toLowerCase());
			mav.addObject("path", getPath());
		}

		return mav;

	}

	private ModelAndView eventoView(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		Long id = Long.parseLong(arg0.getParameter("id"));

		EventoDTO evento = eventoManager.getById(id);

		ModelAndView mav = new ModelAndView(getView() + ConstantsController.VIEW);
		mav.addObject("evento", evento);
		mav.addObject("path", getPath());

		return mav;

	}

	private ModelAndView eventoEdit(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		Long id = Long.parseLong(arg0.getParameter("id"));

		EventoDTO evento = eventoManager.getById(id);

		ModelAndView mav = new ModelAndView(getView() + ConstantsController.EDIT);

		mav.addObject("evento", evento);
		mav.addObject("urlRedirect", getPath() + "-" + ConstantsController.UPDATE.toLowerCase());
		mav.addObject("path", getPath());

		return mav;
	}

	private ModelAndView eventoUpdate(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		Long eveId = Long.valueOf((String)arg0.getAttribute("eveId"));

		String eveAsuntoEs = (String)arg0.getAttribute("eveAsuntoEs");
		if (arg0.getCharacterEncoding() != null) {
			eveAsuntoEs = new String(((String)arg0.getAttribute("eveAsuntoEs")).getBytes("iso-8859-1"), "UTF-8");
		}
		String eveAsuntoEn = (String)arg0.getAttribute("eveAsuntoEn");
		if (arg0.getCharacterEncoding() != null) {
			eveAsuntoEn = new String(((String)arg0.getAttribute("eveAsuntoEn")).getBytes("iso-8859-1"), "UTF-8");
		}
		String eveAsuntoDe = (String)arg0.getAttribute("eveAsuntoDe");
		if (arg0.getCharacterEncoding() != null) {
			eveAsuntoDe = new String(((String)arg0.getAttribute("eveAsuntoDe")).getBytes("iso-8859-1"), "UTF-8");
		}
		String strEveTextoEs = (String)arg0.getAttribute("eveTextoEs");
		Text eveTextoEs = null;
		if (strEveTextoEs != null) {
			if (arg0.getCharacterEncoding() != null) {
				strEveTextoEs = new String(strEveTextoEs.getBytes("iso-8859-1"), "UTF-8");
			}
			eveTextoEs = new Text(strEveTextoEs);
		}
		String strEveTextoEn = (String)arg0.getAttribute("eveTextoEn");
		Text eveTextoEn = null;
		if (strEveTextoEn != null) {
			if (arg0.getCharacterEncoding() != null) {
				strEveTextoEn = new String(strEveTextoEn.getBytes("iso-8859-1"), "UTF-8");
			}
			eveTextoEn = new Text(strEveTextoEn);
		}
		String strEveTextoDe = (String)arg0.getAttribute("eveTextoDe");
		Text eveTextoDe = null;
		if (strEveTextoDe != null) {
			if (arg0.getCharacterEncoding() != null) {
				strEveTextoDe = new String(strEveTextoDe.getBytes("iso-8859-1"), "UTF-8");
			}
			eveTextoDe = new Text(strEveTextoDe);
		}

		String fechaDesde = (String)arg0.getAttribute("eveFechaDesde");
		String fechaHHDesde = (String)arg0.getAttribute("eveFechaDesdeHH");
		String fechaMMDesde = (String)arg0.getAttribute("eveFechaDesdeMM");
		Date eveFechaDesde = Utils.getDateHora(fechaDesde + " " + fechaHHDesde + ":" + fechaMMDesde);

		String fechaHasta = (String)arg0.getAttribute("eveFechaHasta");
		String fechaHHHasta = (String)arg0.getAttribute("eveFechaHastaHH");
		String fechaMMHasta = (String)arg0.getAttribute("eveFechaHastaMM");
		Date eveFechaHasta = Utils.getDateHora(fechaHasta + " " + fechaHHHasta + ":" + fechaMMHasta);

		String eveIdUsu = (String)arg0.getAttribute("eveIdUsu");

		Integer eveActivado = 0;
		if ((String)arg0.getAttribute("eveActivado") != null) {
			eveActivado = 1;
		}

		String eveFichero = (String)arg0.getAttribute("eveFichero");
		String eveFicheroName = (String)arg0.getAttribute("eveFicheroName");
		String eveImagen = (String)arg0.getAttribute("eveImagen");
		String eveImagenName = (String)arg0.getAttribute("eveImagenName");
		if ((String)arg0.getAttribute("eveImagenDelete") != null) {
			eveImagen = "";
			eveImagenName = "";
		}
		String eveLink = (String)arg0.getAttribute("eveLink");

		int eveTipoLink = Integer.parseInt((String)arg0.getAttribute("eveTipoLink"));

		EventoDTO evento = new EventoDTO();
		evento.setEveId(eveId);
		evento.setEveIdForo(getType());
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
		evento.setEveFicheroName(eveFicheroName);
		evento.setEveImagen(eveImagen);
		evento.setEveImagenName(eveImagenName);
		evento.setEveLink(eveLink);
		evento.setEveTipoLink(eveTipoLink);

		evento = eventoManager.update(evento);

		if ((String)arg0.getAttribute("eveImagenDelete") != null) {
			if (evento.getEveImagen() != null) {
				delete(evento.getEveImagen());
			}
		}

		arg1.sendRedirect("/" + getPath() + "-" + ConstantsController.MANAGER.toLowerCase());

		return null;
	}

	private ModelAndView eventoRemove(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		String nameParam = null;
		int indx = 0;
		Long id = null;
		EventoDTO evento = null;
		for (Enumeration params = arg0.getParameterNames(); params.hasMoreElements();) {
			nameParam = (String) params.nextElement();
			indx = nameParam.indexOf(ConstantsController.SELECTOR);
			if (indx != -1) {
				id = Long.parseLong(nameParam.substring(ConstantsController.SELECTOR.length()));
				evento = eventoManager.remove(id);
				if (evento.getEveFichero() != null) {
					delete(evento.getEveFichero());
				}
				if (evento.getEveImagen() != null) {
					delete(evento.getEveImagen());
				}
			}
		}
		arg1.sendRedirect("/" + getPath() + "-" + ConstantsController.ADMIN.toLowerCase());

		return null;

	}

	public void delete(String objectId, final String bucketName) throws IOException {
		if (bucketName == null) {
			throw new IllegalArgumentException("bucketName null");
		}
		try {
			final GcsFilename fileName = new GcsFilename(bucketName, objectId);
			this.gcsService.delete(fileName);
		} catch (RetryHelperException e) {
			throw new IOException(e);
		}
	}

	public void delete(String objectId) throws IOException {
		delete(objectId,BUCKET_NAME);
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
