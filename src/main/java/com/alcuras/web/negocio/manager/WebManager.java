package com.alcuras.web.negocio.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.WebDTO;
import com.alcuras.web.negocio.utils.NullAwareBeanUtilsBean;
import com.alcuras.web.persist.dao.WebDAO;
import com.alcuras.web.persist.entities.Web;
import com.alcuras.web.persist.mapper.WebMapper;


@Component
@Scope(value = "singleton")
public class WebManager implements IWebManager {
	
	@Autowired
	private WebDAO webDAO;

	@Autowired
	private WebMapper mapper;
	
	public static final String PARAMETRO = "webParametro";
	public static final String ACTIVADO = "webActivado";
	public static final String ORDER_KY_DESC = "-__key__";
	
	public WebManager() {
	
	}

	
	/**
	 * Crea un web
	 * 
	 * @param webDTO
	 * @return WebDTO
	 */
	public WebDTO create(WebDTO webDTO) {
		Web web = mapper.map(webDTO);
		web = webDAO.create(web);
		return mapper.map(web);
	}
	
	/**
	 * Elimina un web
	 * 
	 * @param id
	 */
	public WebDTO remove(long id) {
		Web web = webDAO.get(id);
		webDAO.delete(id);
		if (web == null) {
			return null;
		}
		return mapper.map(web);
	}
	
	/**
	 * Modifica un web
	 * 
	 * @param encuestaDTO
	 * @return EncuestaDTO
	 */
	public WebDTO update(WebDTO webDTO) {
		Web web = mapper.map(webDTO);
		Web oldWeb = webDAO.get(webDTO.getWebId());
		try {
			new NullAwareBeanUtilsBean().copyProperties(web, oldWeb);
		} catch (Exception e) {
		}
		web = webDAO.update(web);
		if (web == null) {
			return null;
		}
		return mapper.map(web);
	}

	/**
	 * Obtiene un web
	 * 
	 * @param id
	 * @return WebDTO
	 */
	public WebDTO getById(long id) {
		Web web = webDAO.get(id);
		if (web == null) {
			return null;
		}
		return mapper.map(web);
	}	
	
	/**
	 * Obtiene todos los web
	 * 
	 * @return List<WebDTO>
	 */
	public List<WebDTO> getWeb() {
		List<String> orders = new ArrayList<String>();
		orders.add(ORDER_KY_DESC);
		List<Web> listWeb = webDAO.listOrder(orders);
		List<WebDTO> list = new ArrayList<WebDTO>();
		for (Web web : listWeb) {
			list.add(mapper.map(web));
		}
		return list;		
	}
	
	/**
	 * Obtiene web parametro activo
	 * 
	 * @return List<WebDTO>
	 */
	public WebDTO getWebByWebParametro(String webParametro) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(PARAMETRO, webParametro);
		filters.put(ACTIVADO, 1);
		List<Web> listWeb = webDAO.listFilter(filters);
		if (listWeb.size()==0) return null;
		Web web = (Web) listWeb.get(0);
		return mapper.map(web);
	}
	
	/**
	 * Obtiene web parametro
	 * 
	 * @return List<WebDTO>
	 */
	public WebDTO getWebBBDDByWebParametro(String webParametro) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(PARAMETRO, webParametro);
		List<Web> listWeb = webDAO.listFilter(filters);
		if (listWeb.size()==0) return null;
		Web web = (Web) listWeb.get(0);
		return mapper.map(web);	
	}
	
}