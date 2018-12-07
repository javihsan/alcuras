package com.alcuras.web.persist.transformer;

import org.apache.commons.beanutils.PropertyUtils;

import com.alcuras.web.negocio.dto.EventoDTO;
import com.alcuras.web.persist.entities.Evento;


public class EventoTransformer {
	private static EventoTransformer transformerEvento =  new EventoTransformer();
	

	public void reset(){
		transformerEvento = null;
	}
	
	public static EventoTransformer getInstance(){
		if (transformerEvento == null){
			transformerEvento = new EventoTransformer();
		}
		return transformerEvento;
	}	
	
	public Evento transformDTOToEntity(EventoDTO evento){
		
		Evento entityEvento = new Evento();
		
		try {
			PropertyUtils.copyProperties(entityEvento, evento);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return entityEvento;
	}
	
	public EventoDTO transformEntityToDTO(Evento entityEvento){
		
		EventoDTO evento = new EventoDTO();
		
		try {
			PropertyUtils.copyProperties(evento, entityEvento);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return evento;
	}
	
}
