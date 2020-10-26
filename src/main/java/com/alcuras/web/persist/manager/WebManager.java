package com.alcuras.web.persist.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dao.WebDAO;
import com.alcuras.web.negocio.dto.WebDTO;
import com.alcuras.web.negocio.utils.NullAwareBeanUtilsBean;
import com.alcuras.web.persist.entities.Web;
import com.alcuras.web.persist.transformer.WebTransformer;


@Component
@Scope(value = "singleton")
public class WebManager implements WebDAO {

	@Autowired
	protected IEMF beanEMF;
	
	public WebManager() {
	
	}
		 
	protected EntityManager getEntityManager() {
		return beanEMF.get().createEntityManager();
	}

	public WebDTO create(WebDTO web) throws Exception {
		EntityManager em = getEntityManager();
		Web entityWeb = WebTransformer.getInstance().transformDTOToEntity(web);
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
		return WebTransformer.getInstance().transformEntityToDTO(entityWeb);
	}

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
		return WebTransformer.getInstance().transformEntityToDTO(oldEntityWeb);
	}

	public WebDTO update(WebDTO web) throws Exception {
		EntityManager em = getEntityManager();
		Web entityWeb = WebTransformer.getInstance().transformDTOToEntity(web);
		Web oldEntityWeb = null;
		WebDTO oldWeb = null;
		try {
			em.getTransaction().begin();
			oldEntityWeb = (Web) em.find(Web.class, entityWeb.getWebId());
			new NullAwareBeanUtilsBean().copyProperties(entityWeb, oldEntityWeb);
			oldWeb = WebTransformer.getInstance().transformEntityToDTO(oldEntityWeb);
			entityWeb = em.merge(entityWeb);
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
		return oldWeb;
	}

	public WebDTO getById(long id) {
		EntityManager em = getEntityManager();
		Web entityWeb = null;
		try {
			entityWeb = (Web) em.find(Web.class, id);
		} finally {
			em.close();
		}
		return WebTransformer.getInstance().transformEntityToDTO(entityWeb);
	}

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
	}
	
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
		return WebTransformer.getInstance().transformEntityToDTO(entityWeb);
	} 
	
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
		return WebTransformer.getInstance().transformEntityToDTO(entityWeb);
	}
	
}