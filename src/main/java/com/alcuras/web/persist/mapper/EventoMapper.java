package com.alcuras.web.persist.mapper;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.EventoDTO;
import com.alcuras.web.persist.entities.Evento;

@Component
public class EventoMapper {
	private static EventoMapper transformerEvento =  new EventoMapper();
	

	public void reset(){
		transformerEvento = null;
	}
	
	public static EventoMapper getInstance(){
		if (transformerEvento == null){
			transformerEvento = new EventoMapper();
		}
		return transformerEvento;
	}	
	
	public Evento map(EventoDTO evento){
		
		Evento entityEvento = new Evento();
		
		try {
			PropertyUtils.copyProperties(entityEvento, evento);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return entityEvento;
	}
	
	public EventoDTO map(Evento entityEvento){
		
		EventoDTO evento = new EventoDTO();
		
		try {
			PropertyUtils.copyProperties(evento, entityEvento);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return evento;
	}
	
}
