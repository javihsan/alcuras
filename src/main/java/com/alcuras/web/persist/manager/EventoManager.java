package com.alcuras.web.persist.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dao.EventoDAO;
import com.alcuras.web.negocio.dto.EventoDTO;
import com.alcuras.web.negocio.utils.NullAwareBeanUtilsBean;
import com.alcuras.web.persist.entities.Evento;
import com.alcuras.web.persist.transformer.EventoTransformer;

@Component
@Scope(value = "singleton")
public class EventoManager implements EventoDAO {

	@Autowired
	protected IEMF beanEMF;
	
	public EventoManager() {
	
	}
		 
	protected EntityManager getEntityManager() {
		return beanEMF.get().createEntityManager();
	}
	public EventoDTO create(EventoDTO evento) throws Exception {
		EntityManager em = getEntityManager();
		Evento entityEvento = EventoTransformer.getInstance().transformDTOToEntity(evento);
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
		return EventoTransformer.getInstance().transformEntityToDTO(entityEvento);
	}

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
		return EventoTransformer.getInstance().transformEntityToDTO(oldEntityEvento);
	}
	
	public EventoDTO update(EventoDTO evento) throws Exception {
		EntityManager em = getEntityManager();
		Evento entityEvento = EventoTransformer.getInstance().transformDTOToEntity(evento);
		Evento oldEntityEvento = null;
		EventoDTO oldEvento = null;
		try {
			em.getTransaction().begin();
			oldEntityEvento = (Evento) em.find(Evento.class, entityEvento.getEveId());
			new NullAwareBeanUtilsBean().copyProperties(entityEvento, oldEntityEvento);
			oldEvento = EventoTransformer.getInstance().transformEntityToDTO(oldEntityEvento);
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
	}

	public EventoDTO getById(long id) {
		Evento entityEvento = null;
		EntityManager em = getEntityManager();
		try {
			entityEvento = (Evento) em.find(Evento.class, id);
		} finally {
			em.close();
		}
		return EventoTransformer.getInstance().transformEntityToDTO(entityEvento);
	}

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
				evento = EventoTransformer.getInstance().transformEntityToDTO(entityEvento);
				result.add(evento);
			}
		} finally {
			em.close();
		}
		return result;
	}

	public List<EventoDTO> getEventoAdmin(long eveIdForo) {
		EntityManager em = getEntityManager();
		List<EventoDTO> result = new ArrayList<EventoDTO>();
		List<Evento> resultQuery = null;
		try {
			Query query = em.createNamedQuery("getEventoAdmin");
			query.setParameter("eveIdForo", eveIdForo);
			resultQuery = (List<Evento>) query.getResultList();
			for (Evento entityEvento : resultQuery) {
				result.add(EventoTransformer.getInstance().transformEntityToDTO(entityEvento));
			}
		} finally {
			em.close();
		}
		return result;
	}


 }