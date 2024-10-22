package com.alcuras.web.negocio.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.ArticuloDTO;
import com.alcuras.web.persist.dao.ArticuloDAO;
import com.alcuras.web.persist.entities.Articulo;
import com.alcuras.web.persist.mapper.ArticuloMapper;



@Component
@Scope(value = "singleton")
public class ArticuloManager implements IArticuloManager {
 
//	@Autowired
//	protected IEMF beanEMF;
	
	@Autowired
	private ArticuloDAO articuloDAO;

	@Autowired
	private ArticuloMapper mapper;
	
	public static final String ID_FORO = "artIdForo";
	public static final String ACTIVADO = "artActivado";
	
	public ArticuloManager() {
	
	}
//	 
//	protected EntityManager getEntityManager() {
//		return beanEMF.get().createEntityManager();
//	}
/*
	public ArticuloDTO create(ArticuloDTO articulo) throws Exception {
		EntityManager em = getEntityManager();
		Articulo entityArticulo = ArticuloMapper.getInstance().transformDTOToEntity(articulo);
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
		return ArticuloMapper.getInstance().transformEntityToDTO(entityArticulo);
	}*/

	
	/**
	 * Crea un articulo
	 * 
	 * @param articuloDTO
	 * @return ArticuloDTO
	 */
	public ArticuloDTO create(ArticuloDTO articuloDTO) {
		Articulo articulo = mapper.map(articuloDTO);
		articulo = articuloDAO.create(articulo);
		return mapper.map(articulo);
	}
	/*
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
		return ArticuloMapper.getInstance().transformEntityToDTO(oldEntityArticulo);
	}*/
	
	/**
	 * Elimina un articulo
	 * 
	 * @param id
	 */
	public ArticuloDTO remove(long id) {
		Articulo articulo = articuloDAO.get(id);
		articuloDAO.delete(id);
		if (articulo == null) {
			return null;
		}
		return mapper.map(articulo);
	}
	/*
	public ArticuloDTO update(ArticuloDTO articulo) throws Exception {
		EntityManager em = getEntityManager();
		Articulo entityArticulo = ArticuloMapper.getInstance().transformDTOToEntity(articulo);
		Articulo oldEntityArticulo = null;
		ArticuloDTO oldArticulo = null;
		try {
			em.getTransaction().begin();
			oldEntityArticulo = (Articulo) em.find(Articulo.class, entityArticulo.getArtId());
			new NullAwareBeanUtilsBean().copyProperties(entityArticulo, oldEntityArticulo);
			oldArticulo = ArticuloMapper.getInstance().transformEntityToDTO(oldEntityArticulo);
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
	}*/
	
	/**
	 * Modifica un articulo
	 * 
	 * @param encuestaDTO
	 * @return EncuestaDTO
	 */
	public ArticuloDTO update(ArticuloDTO articuloDTO) {
		Articulo articulo = mapper.map(articuloDTO);
		articulo = articuloDAO.update(articulo);
		if (articulo == null) {
			return null;
		}
		return mapper.map(articulo);
	}
	/*
	public ArticuloDTO getById(long id) {
		Articulo entityArticulo = null;
		EntityManager em = getEntityManager();
		try {
			entityArticulo = (Articulo) em.find(Articulo.class, id);
		} finally {
			em.close();
		}
		return ArticuloMapper.getInstance().transformEntityToDTO(entityArticulo);
	}
	*/

	/**
	 * Obtiene un articulo
	 * 
	 * @param id
	 * @return ArticuloDTO
	 */
	public ArticuloDTO getById(long id) {
		Articulo articulo = articuloDAO.get(id);
		if (articulo == null) {
			return null;
		}
		return mapper.map(articulo);
	}
	/*
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
				articulo = ArticuloMapper.getInstance().transformEntityToDTO(entityArticulo);
				result.add(articulo);
			}
		} finally {
			em.close();
		}
		return result;
	}*/
	
	/**
	 * Obtiene todos los articulos activos de un foro
	 * 
	 * @return List<ArticuloDTO>
	 */
	public List<ArticuloDTO> getArticulo(long artIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, artIdForo);
		filters.put(ACTIVADO, 1);
		List<String> orders = new ArrayList<String>();
		orders.add("-__key__");
		List<Articulo> listArticulo = articuloDAO.listOrderFilter(filters, orders);
		List<ArticuloDTO> list = new ArrayList<ArticuloDTO>();
		for (Articulo articulo : listArticulo) {
			list.add(mapper.map(articulo));
		}
		return list;		
	}
	/*
	public List<ArticuloDTO> getArticuloAdmin(long artIdForo) {
		EntityManager em = getEntityManager();
		List<ArticuloDTO> result = new ArrayList<ArticuloDTO>();
		List<Articulo> resultQuery = null;
		try {
			Query query = em.createNamedQuery("getArticuloAdmin");
			query.setParameter("artIdForo", artIdForo);
			resultQuery = (List<Articulo>) query.getResultList();
			for (Articulo entityArticulo : resultQuery) {
				result.add(ArticuloMapper.getInstance().transformEntityToDTO(entityArticulo));
			}
		} finally {
			em.close();
		}
		return result;
	}*/
	
	/**
	 * Obtiene todos los articulos de un foro
	 * 
	 * @return List<ArticuloDTO>
	 */
	public List<ArticuloDTO> getArticuloAdmin(long artIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, artIdForo);
		List<String> orders = new ArrayList<String>();
		orders.add("-__key__");
		List<Articulo> listArticulo = articuloDAO.listOrderFilter(filters, orders);
		List<ArticuloDTO> list = new ArrayList<ArticuloDTO>();
		for (Articulo articulo : listArticulo) {
			list.add(mapper.map(articulo));
		}
		return list;		
	}	

 }