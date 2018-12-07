package com.alcuras.web.persist.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dao.MensajeDAO;
import com.alcuras.web.negocio.dto.MensajeDTO;
import com.alcuras.web.negocio.utils.NullAwareBeanUtilsBean;
import com.alcuras.web.persist.entities.Mensaje;
import com.alcuras.web.persist.transformer.MensajeTransformer;

@Component
@Scope(value = "singleton")
public class MensajeManager implements MensajeDAO {
	
	@Autowired
	protected IEMF beanEMF;
	
	public MensajeManager() {
	
	}
	
	protected EntityManager getEntityManager() {
		return beanEMF.get().createEntityManager();
	}

	public MensajeDTO create(MensajeDTO mensaje) throws Exception {
		EntityManager em = getEntityManager();
		Mensaje entityMensaje = MensajeTransformer.getInstance().transformDTOToEntity(mensaje);
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
		return MensajeTransformer.getInstance().transformEntityToDTO(entityMensaje);
	}

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
		return MensajeTransformer.getInstance().transformEntityToDTO(oldEntityMensaje);
	}
	
	public MensajeDTO update(MensajeDTO mensaje) throws Exception {
		EntityManager em = getEntityManager();
		Mensaje entityMensaje = MensajeTransformer.getInstance().transformDTOToEntity(mensaje);
		Mensaje oldEntityMensaje = null;
		MensajeDTO oldMensaje = null;
		try {
			em.getTransaction().begin();
			oldEntityMensaje = (Mensaje) em.find(Mensaje.class, entityMensaje.getMenId());
			oldMensaje = MensajeTransformer.getInstance().transformEntityToDTO(oldEntityMensaje);
			new NullAwareBeanUtilsBean().copyProperties(entityMensaje, oldEntityMensaje);
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
	}

	public MensajeDTO getById(long id) {
		Mensaje entityMensaje = null;
		EntityManager em = getEntityManager();
		try {
			entityMensaje = (Mensaje) em.find(Mensaje.class, id);
		} finally {
			em.close();
		}
		return MensajeTransformer.getInstance().transformEntityToDTO(entityMensaje);
	}

	public List<MensajeDTO> getMensaje(long menIdForo) {
		EntityManager em = getEntityManager();
		List<MensajeDTO> result = new ArrayList<MensajeDTO>();
		List<Mensaje> resultQuery = null;
		MensajeDTO mensaje = null;
		try {
			Query query = em.createNamedQuery("getMensaje");
			query.setParameter("menIdForo", menIdForo);
			resultQuery = (List<Mensaje>) query.getResultList();
			for (Mensaje entityMensaje: resultQuery){
				mensaje = MensajeTransformer.getInstance().transformEntityToDTO(entityMensaje);
				result.add(mensaje);
			}
		} finally {
			em.close();
		}
		return result;
	}

	public List<MensajeDTO> getMensajeAdmin(long menIdForo) {
		EntityManager em = getEntityManager();
		List<MensajeDTO> result = new ArrayList<MensajeDTO>();
		List<Mensaje> resultQuery = null;
		try {
			Query query = em.createNamedQuery("getMensajeAdmin");
			query.setParameter("menIdForo", menIdForo);
			resultQuery = (List<Mensaje>) query.getResultList();
			for (Mensaje entityMensaje : resultQuery) {
				result.add(MensajeTransformer.getInstance().transformEntityToDTO(entityMensaje));
			}
		} finally {
			em.close();
		}
		return result;
	}
	

 }