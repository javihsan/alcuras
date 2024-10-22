package com.alcuras.web.negocio.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.EventoDTO;
import com.alcuras.web.persist.dao.EventoDAO;
import com.alcuras.web.persist.entities.Evento;
import com.alcuras.web.persist.mapper.EventoMapper;

@Component
@Scope(value = "singleton")
public class EventoManager implements IEventoManager {

//	@Autowired
//	protected IEMF beanEMF;
	
	@Autowired
	private EventoDAO eventoDAO;

	@Autowired
	private EventoMapper mapper;
	
	public static final String ID_FORO = "eveIdForo";
	public static final String ACTIVADO = "eveActivado";
	
	public EventoManager() {
	
	}
//	 
//	protected EntityManager getEntityManager() {
//		return beanEMF.get().createEntityManager();
//	}
/*
	public EventoDTO create(EventoDTO evento) throws Exception {
		EntityManager em = getEntityManager();
		Evento entityEvento = EventoMapper.getInstance().transformDTOToEntity(evento);
		try {
			em.getTransaction().begin();
			em.persist(entityEvento);
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
		return EventoMapper.getInstance().transformEntityToDTO(entityEvento);
	}*/

	
	/**
	 * Crea un evento
	 * 
	 * @param eventoDTO
	 * @return EventoDTO
	 */
	public EventoDTO create(EventoDTO eventoDTO) {
		Evento evento = mapper.map(eventoDTO);
		evento = eventoDAO.create(evento);
		return mapper.map(evento);
	}
	/*
	public EventoDTO remove(long id) throws Exception {
		EntityManager em = getEntityManager();
		Evento oldEntityEvento = new Evento();
		try {
			em.getTransaction().begin();
			Evento entityEvento = (Evento) em.find(Evento.class, id);
			PropertyUtils.copyProperties(oldEntityEvento, entityEvento);
			em.remove(em.merge(entityEvento));
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
		return EventoMapper.getInstance().transformEntityToDTO(oldEntityEvento);
	}*/
	
	/**
	 * Elimina un evento
	 * 
	 * @param id
	 */
	public EventoDTO remove(long id) {
		Evento evento = eventoDAO.get(id);
		eventoDAO.delete(id);
		if (evento == null) {
			return null;
		}
		return mapper.map(evento);
	}
	/*
	public EventoDTO update(EventoDTO evento) throws Exception {
		EntityManager em = getEntityManager();
		Evento entityEvento = EventoMapper.getInstance().transformDTOToEntity(evento);
		Evento oldEntityEvento = null;
		EventoDTO oldEvento = null;
		try {
			em.getTransaction().begin();
			oldEntityEvento = (Evento) em.find(Evento.class, entityEvento.getArtId());
			new NullAwareBeanUtilsBean().copyProperties(entityEvento, oldEntityEvento);
			oldEvento = EventoMapper.getInstance().transformEntityToDTO(oldEntityEvento);
			entityEvento = em.merge(entityEvento);
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
		return oldEvento;
	}*/
	
	/**
	 * Modifica un evento
	 * 
	 * @param encuestaDTO
	 * @return EncuestaDTO
	 */
	public EventoDTO update(EventoDTO eventoDTO) {
		Evento evento = mapper.map(eventoDTO);
		evento = eventoDAO.update(evento);
		if (evento == null) {
			return null;
		}
		return mapper.map(evento);
	}
	/*
	public EventoDTO getById(long id) {
		Evento entityEvento = null;
		EntityManager em = getEntityManager();
		try {
			entityEvento = (Evento) em.find(Evento.class, id);
		} finally {
			em.close();
		}
		return EventoMapper.getInstance().transformEntityToDTO(entityEvento);
	}
	*/

	/**
	 * Obtiene un evento
	 * 
	 * @param id
	 * @return EventoDTO
	 */
	public EventoDTO getById(long id) {
		Evento evento = eventoDAO.get(id);
		if (evento == null) {
			return null;
		}
		return mapper.map(evento);
	}
	
	
/*
	public List<EventoDTO> getEvento(long eveIdForo) {
		EntityManager em = getEntityManager();
		List<EventoDTO> result = new ArrayList<EventoDTO>();
		List<Evento> resultQuery = null;
		EventoDTO evento = null;
		try {
			Query query = em.createNamedQuery("getEvento");
			query.setParameter("eveIdForo", eveIdForo);
			resultQuery = (List<Evento>) query.getResultList();
			for (Evento entityEvento: resultQuery){
				evento = EventoMapper.getInstance().transformEntityToDTO(entityEvento);
				result.add(evento);
			}
		} finally {
			em.close();
		}
		return result;
	}
*/
	
	/**
	 * Obtiene todos los eventos activos de un foro
	 * 
	 * @return List<EventoDTO>
	 */
	public List<EventoDTO> getEvento(long eveIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, eveIdForo);
		filters.put(ACTIVADO, 1);
		List<String> orders = new ArrayList<String>();
		orders.add("-eveFechaDesde");
		List<Evento> listEvento = eventoDAO.listOrderFilter(filters, orders);
		List<EventoDTO> list = new ArrayList<EventoDTO>();
		for (Evento evento : listEvento) {
			list.add(mapper.map(evento));
		}
		return list;		
	}
	/*
	public List<EventoDTO> getEventoAdmin(long eveIdForo) {
		EntityManager em = getEntityManager();
		List<EventoDTO> result = new ArrayList<EventoDTO>();
		List<Evento> resultQuery = null;
		try {
			Query query = em.createNamedQuery("getEventoAdmin");
			query.setParameter("eveIdForo", eveIdForo);
			resultQuery = (List<Evento>) query.getResultList();
			for (Evento entityEvento : resultQuery) {
				result.add(EventoMapper.getInstance().transformEntityToDTO(entityEvento));
			}
		} finally {
			em.close();
		}
		return result;
	}*/
	/**
	 * Obtiene todos los eventos de un foro
	 * 
	 * @return List<EventoDTO>
	 */
	public List<EventoDTO> getEventoAdmin(long eveIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, eveIdForo);
		List<String> orders = new ArrayList<String>();
		orders.add("-eveFechaDesde");
		List<Evento> listEvento = eventoDAO.listOrderFilter(filters, orders);
		List<EventoDTO> list = new ArrayList<EventoDTO>();
		for (Evento evento : listEvento) {
			list.add(mapper.map(evento));
		}
		return list;		
	}

 }