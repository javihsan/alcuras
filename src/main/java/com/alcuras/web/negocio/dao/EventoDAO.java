package com.alcuras.web.negocio.dao;

import java.util.List;

import com.alcuras.web.negocio.dto.EventoDTO;

public interface EventoDAO {

	public EventoDTO create(EventoDTO evento) throws Exception;

	public EventoDTO remove(long id) throws Exception;

	public EventoDTO update(EventoDTO evento) throws Exception;

	public EventoDTO getById(long id);

	public List<EventoDTO> getEvento(long artIdForo);

	public List<EventoDTO> getEventoAdmin(long artIdForo);
	

 }