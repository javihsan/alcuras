package com.alcuras.web.negocio.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.MensajeDTO;
import com.alcuras.web.persist.dao.MensajeDAO;
import com.alcuras.web.persist.entities.Mensaje;
import com.alcuras.web.persist.mapper.MensajeMapper;

@Component
@Scope(value = "singleton")
public class MensajeManager implements IMensajeManager {
	
//	@Autowired
//	protected IEMF beanEMF;
	
	@Autowired
	private MensajeDAO mensajeDAO;

	@Autowired
	private MensajeMapper mapper;
	
	public static final String ID_FORO = "menIdForo";
	public static final String ACTIVADO = "menActivado";
	
	public MensajeManager() {
	
	}
//	 
//	protected EntityManager getEntityManager() {
//		return beanEMF.get().createEntityManager();
//	}
/*
	public MensajeDTO create(MensajeDTO mensaje) throws Exception {
		EntityManager em = getEntityManager();
		Mensaje entityMensaje = MensajeMapper.getInstance().transformDTOToEntity(mensaje);
		try {
			em.getTransaction().begin();
			em.persist(entityMensaje);
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
		return MensajeMapper.getInstance().transformEntityToDTO(entityMensaje);
	}*/

	
	/**
	 * Crea un mensaje
	 * 
	 * @param mensajeDTO
	 * @return MensajeDTO
	 */
	public MensajeDTO create(MensajeDTO mensajeDTO) {
		Mensaje mensaje = mapper.map(mensajeDTO);
		mensaje = mensajeDAO.create(mensaje);
		return mapper.map(mensaje);
	}
	/*
	public MensajeDTO remove(long id) throws Exception {
		EntityManager em = getEntityManager();
		Mensaje oldEntityMensaje = new Mensaje();
		try {
			em.getTransaction().begin();
			Mensaje entityMensaje = (Mensaje) em.find(Mensaje.class, id);
			PropertyUtils.copyProperties(oldEntityMensaje, entityMensaje);
			em.remove(em.merge(entityMensaje));
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
		return MensajeMapper.getInstance().transformEntityToDTO(oldEntityMensaje);
	}*/
	
	/**
	 * Elimina un mensaje
	 * 
	 * @param id
	 */
	public MensajeDTO remove(long id) {
		Mensaje mensaje = mensajeDAO.get(id);
		mensajeDAO.delete(id);
		if (mensaje == null) {
			return null;
		}
		return mapper.map(mensaje);
	}
	/*
	public MensajeDTO update(MensajeDTO mensaje) throws Exception {
		EntityManager em = getEntityManager();
		Mensaje entityMensaje = MensajeMapper.getInstance().transformDTOToEntity(mensaje);
		Mensaje oldEntityMensaje = null;
		MensajeDTO oldMensaje = null;
		try {
			em.getTransaction().begin();
			oldEntityMensaje = (Mensaje) em.find(Mensaje.class, entityMensaje.getArtId());
			new NullAwareBeanUtilsBean().copyProperties(entityMensaje, oldEntityMensaje);
			oldMensaje = MensajeMapper.getInstance().transformEntityToDTO(oldEntityMensaje);
			entityMensaje = em.merge(entityMensaje);
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
		return oldMensaje;
	}*/
	
	/**
	 * Modifica un mensaje
	 * 
	 * @param encuestaDTO
	 * @return EncuestaDTO
	 */
	public MensajeDTO update(MensajeDTO mensajeDTO) {
		Mensaje mensaje = mapper.map(mensajeDTO);
		mensaje = mensajeDAO.update(mensaje);
		if (mensaje == null) {
			return null;
		}
		return mapper.map(mensaje);
	}
	/*
	public MensajeDTO getById(long id) {
		Mensaje entityMensaje = null;
		EntityManager em = getEntityManager();
		try {
			entityMensaje = (Mensaje) em.find(Mensaje.class, id);
		} finally {
			em.close();
		}
		return MensajeMapper.getInstance().transformEntityToDTO(entityMensaje);
	}
	*/

	/**
	 * Obtiene un mensaje
	 * 
	 * @param id
	 * @return MensajeDTO
	 */
	public MensajeDTO getById(long id) {
		Mensaje mensaje = mensajeDAO.get(id);
		if (mensaje == null) {
			return null;
		}
		return mapper.map(mensaje);
	}
	/*
	public List<MensajeDTO> getMensaje(long artIdForo) {
		EntityManager em = getEntityManager();
		List<MensajeDTO> result = new ArrayList<MensajeDTO>();
		List<Mensaje> resultQuery = null;
		MensajeDTO mensaje = null;
		try {
			Query query = em.createNamedQuery("getMensaje");
			query.setParameter("artIdForo", artIdForo);
			resultQuery = (List<Mensaje>) query.getResultList();
			for (Mensaje entityMensaje: resultQuery){
				mensaje = MensajeMapper.getInstance().transformEntityToDTO(entityMensaje);
				result.add(mensaje);
			}
		} finally {
			em.close();
		}
		return result;
	}*/
	
	/**
	 * Obtiene todos los mensajes activos de un foro
	 * 
	 * @return List<MensajeDTO>
	 */
	public List<MensajeDTO> getMensaje(long menIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, menIdForo);
		filters.put(ACTIVADO, 1);
		List<String> orders = new ArrayList<String>();
		orders.add("-menFecha");
		List<Mensaje> listMensaje = mensajeDAO.listOrderFilter(filters, orders);
		List<MensajeDTO> list = new ArrayList<MensajeDTO>();
		for (Mensaje mensaje : listMensaje) {
			list.add(mapper.map(mensaje));
		}
		return list;		
	}
	/*
	public List<MensajeDTO> getMensajeAdmin(long artIdForo) {
		EntityManager em = getEntityManager();
		List<MensajeDTO> result = new ArrayList<MensajeDTO>();
		List<Mensaje> resultQuery = null;
		try {
			Query query = em.createNamedQuery("getMensajeAdmin");
			query.setParameter("artIdForo", artIdForo);
			resultQuery = (List<Mensaje>) query.getResultList();
			for (Mensaje entityMensaje : resultQuery) {
				result.add(MensajeMapper.getInstance().transformEntityToDTO(entityMensaje));
			}
		} finally {
			em.close();
		}
		return result;
	}*/
	
	/**
	 * Obtiene todos los mensajes de un foro
	 * 
	 * @return List<MensajeDTO>
	 */
	public List<MensajeDTO> getMensajeAdmin(long menIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, menIdForo);
		List<String> orders = new ArrayList<String>();
		orders.add("-menFecha");
		List<Mensaje> listMensaje = mensajeDAO.listOrderFilter(filters, orders);
		List<MensajeDTO> list = new ArrayList<MensajeDTO>();
		for (Mensaje mensaje : listMensaje) {
			list.add(mapper.map(mensaje));
		}
		return list;		
	}	

 }