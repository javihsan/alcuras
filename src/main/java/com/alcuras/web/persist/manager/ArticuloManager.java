package com.alcuras.web.persist.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dao.ArticuloDAO;
import com.alcuras.web.negocio.dto.ArticuloDTO;
import com.alcuras.web.negocio.utils.NullAwareBeanUtilsBean;
import com.alcuras.web.persist.entities.Articulo;
import com.alcuras.web.persist.transformer.ArticuloTransformer;

@Component
@Scope(value = "singleton")
public class ArticuloManager implements ArticuloDAO {

	@Autowired
	protected IEMF beanEMF;
	
	public ArticuloManager() {
	
	}
	 
	protected EntityManager getEntityManager() {
		return beanEMF.get().createEntityManager();
	}

	public ArticuloDTO create(ArticuloDTO articulo) throws Exception {
		EntityManager em = getEntityManager();
		Articulo entityArticulo = ArticuloTransformer.getInstance().transformDTOToEntity(articulo);
		try {
			em.getTransaction().begin();
			em.persist(entityArticulo);
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
		return ArticuloTransformer.getInstance().transformEntityToDTO(entityArticulo);
	}

	public ArticuloDTO remove(long id) throws Exception {
		EntityManager em = getEntityManager();
		Articulo oldEntityArticulo = new Articulo();
		try {
			em.getTransaction().begin();
			Articulo entityArticulo = (Articulo) em.find(Articulo.class, id);
			PropertyUtils.copyProperties(oldEntityArticulo, entityArticulo);
			em.remove(em.merge(entityArticulo));
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
		return ArticuloTransformer.getInstance().transformEntityToDTO(oldEntityArticulo);
	}
	
	public ArticuloDTO update(ArticuloDTO articulo) throws Exception {
		EntityManager em = getEntityManager();
		Articulo entityArticulo = ArticuloTransformer.getInstance().transformDTOToEntity(articulo);
		Articulo oldEntityArticulo = null;
		ArticuloDTO oldArticulo = null;
		try {
			em.getTransaction().begin();
			oldEntityArticulo = (Articulo) em.find(Articulo.class, entityArticulo.getArtId());
			new NullAwareBeanUtilsBean().copyProperties(entityArticulo, oldEntityArticulo);
			oldArticulo = ArticuloTransformer.getInstance().transformEntityToDTO(oldEntityArticulo);
			entityArticulo = em.merge(entityArticulo);
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
		return oldArticulo;
	}

	public ArticuloDTO getById(long id) {
		Articulo entityArticulo = null;
		EntityManager em = getEntityManager();
		try {
			entityArticulo = (Articulo) em.find(Articulo.class, id);
		} finally {
			em.close();
		}
		return ArticuloTransformer.getInstance().transformEntityToDTO(entityArticulo);
	}

	public List<ArticuloDTO> getArticulo(long artIdForo) {
		EntityManager em = getEntityManager();
		List<ArticuloDTO> result = new ArrayList<ArticuloDTO>();
		List<Articulo> resultQuery = null;
		ArticuloDTO articulo = null;
		try {
			Query query = em.createNamedQuery("getArticulo");
			query.setParameter("artIdForo", artIdForo);
			resultQuery = (List<Articulo>) query.getResultList();
			for (Articulo entityArticulo: resultQuery){
				articulo = ArticuloTransformer.getInstance().transformEntityToDTO(entityArticulo);
				result.add(articulo);
			}
		} finally {
			em.close();
		}
		return result;
	}

	public List<ArticuloDTO> getArticuloAdmin(long artIdForo) {
		EntityManager em = getEntityManager();
		List<ArticuloDTO> result = new ArrayList<ArticuloDTO>();
		List<Articulo> resultQuery = null;
		try {
			Query query = em.createNamedQuery("getArticuloAdmin");
			query.setParameter("artIdForo", artIdForo);
			resultQuery = (List<Articulo>) query.getResultList();
			for (Articulo entityArticulo : resultQuery) {
				result.add(ArticuloTransformer.getInstance().transformEntityToDTO(entityArticulo));
			}
		} finally {
			em.close();
		}
		return result;
	}


 }