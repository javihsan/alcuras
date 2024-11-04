package com.alcuras.web.negocio.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.EventoDTO;
import com.alcuras.web.negocio.utils.NullAwareBeanUtilsBean;
import com.alcuras.web.persist.dao.EventoDAO;
import com.alcuras.web.persist.entities.Evento;
import com.alcuras.web.persist.mapper.EventoMapper;

@Component
@Scope(value = "singleton")
public class EventoManager implements IEventoManager {

	@Autowired
	private EventoDAO eventoDAO;

	@Autowired
	private EventoMapper mapper;
	
	public static final String ID_FORO = "eveIdForo";
	public static final String ACTIVADO = "eveActivado";
	
	public EventoManager() {
	
	}
	
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

	
	/**
	 * Modifica un evento
	 * 
	 * @param encuestaDTO
	 * @return EncuestaDTO
	 */
	public EventoDTO update(EventoDTO eventoDTO) {
		Evento evento = mapper.map(eventoDTO);
		Evento oldEvento = eventoDAO.get(eventoDTO.getEveId());
		try {
			new NullAwareBeanUtilsBean().copyProperties(evento, oldEvento);
		} catch (Exception e) {
		}
		evento = eventoDAO.update(evento);
		if (evento == null) {
			return null;
		}
		return mapper.map(evento);
	}

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