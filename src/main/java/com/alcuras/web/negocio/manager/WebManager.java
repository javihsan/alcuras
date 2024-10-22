package com.alcuras.web.negocio.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.WebDTO;
import com.alcuras.web.persist.dao.WebDAO;
import com.alcuras.web.persist.entities.Web;
import com.alcuras.web.persist.mapper.WebMapper;


@Component
@Scope(value = "singleton")
public class WebManager implements IWebManager {

//	@Autowired
//	protected IEMF beanEMF;
	
	@Autowired
	private WebDAO webDAO;

	@Autowired
	private WebMapper mapper;
	
	public static final String PARAMETRO = "webParametro";
	public static final String ACTIVADO = "webActivado";
	
	public WebManager() {
	
	}
//	 
//	protected EntityManager getEntityManager() {
//		return beanEMF.get().createEntityManager();
//	}
/*
	public WebDTO create(WebDTO web) throws Exception {
		EntityManager em = getEntityManager();
		Web entityWeb = WebMapper.getInstance().transformDTOToEntity(web);
		try {
			em.getTransaction().begin();
			em.persist(entityWeb);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return WebMapper.getInstance().transformEntityToDTO(entityWeb);
	}*/

	
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
	/*
	public WebDTO remove(long id) throws Exception {
		EntityManager em = getEntityManager();
		Web oldEntityWeb = new Web();
		try {
			em.getTransaction().begin();
			Web entityWeb = (Web) em.find(Web.class, id);
			PropertyUtils.copyProperties(oldEntityWeb, entityWeb);
			em.remove(em.merge(entityWeb));
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return WebMapper.getInstance().transformEntityToDTO(oldEntityWeb);
	}*/
	
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
	/*
	public WebDTO update(WebDTO web) throws Exception {
		EntityManager em = getEntityManager();
		Web entityWeb = WebMapper.getInstance().transformDTOToEntity(web);
		Web oldEntityWeb = null;
		WebDTO oldWeb = null;
		try {
			em.getTransaction().begin();
			oldEntityWeb = (Web) em.find(Web.class, entityWeb.getArtId());
			new NullAwareBeanUtilsBean().copyProperties(entityWeb, oldEntityWeb);
			oldWeb = WebMapper.getInstance().transformEntityToDTO(oldEntityWeb);
			entityWeb = em.merge(entityWeb);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return oldWeb;
	}*/
	
	/**
	 * Modifica un web
	 * 
	 * @param encuestaDTO
	 * @return EncuestaDTO
	 */
	public WebDTO update(WebDTO webDTO) {
		Web web = mapper.map(webDTO);
		web = webDAO.update(web);
		if (web == null) {
			return null;
		}
		return mapper.map(web);
	}
	/*
	public WebDTO getById(long id) {
		Web entityWeb = null;
		EntityManager em = getEntityManager();
		try {
			entityWeb = (Web) em.find(Web.class, id);
		} finally {
			em.close();
		}
		return WebMapper.getInstance().transformEntityToDTO(entityWeb);
	}
	*/

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

	/*
	public List<WebDTO> getWeb() {
		EntityManager em = getEntityManager();
		List<WebDTO> result = new ArrayList<WebDTO>();
		List<Web> resultQuery = null;
		try {
			Query query = em.createNamedQuery("getWeb");
			resultQuery = (List<Web>) query.getResultList();
			for (Web entityWeb : resultQuery) {
				result.add(WebTransformer.getInstance().transformEntityToDTO(entityWeb));
			}
		} finally {
			em.close();
		}
		return result;
	}*/
	
	/**
	 * Obtiene todos los web
	 * 
	 * @return List<WebDTO>
	 */
	public List<WebDTO> getWeb() {
		List<String> orders = new ArrayList<String>();
		orders.add("-__key__");
		List<Web> listWeb = webDAO.listOrder(orders);
		List<WebDTO> list = new ArrayList<WebDTO>();
		for (Web web : listWeb) {
			list.add(mapper.map(web));
		}
		return list;		
	}

	/*
	public WebDTO getWebByWebParametro(String webParametro) {
		EntityManager em = getEntityManager();
		Web entityWeb = null;
		try {
			Query query = em.createNamedQuery("getWebByWebParametro");
			query.setParameter("webParametro", webParametro);
			List<Web> list = query.getResultList();
			if (list.size()==0) return null;
			entityWeb = (Web) list.get(0);
			
		} finally {
			em.close();
		}
		return WebMapper.getInstance().transformEntityToDTO(entityWeb);
	} 
	*/
	
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
	
	/*
	public WebDTO getWebBBDDByWebParametro(String webParametro) {
		EntityManager em = getEntityManager();
		Web entityWeb = null;
		try {
			Query query = em.createNamedQuery("getWebBBDDByWebParametro");
			query.setParameter("webParametro", webParametro);
			List<Web> list = query.getResultList();
			if (list.size()==0) return null;
			entityWeb = (Web) list.get(0);
			
		} finally {
			em.close();
		}
		return WebMapper.getInstance().transformEntityToDTO(entityWeb);
	}*/
	
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