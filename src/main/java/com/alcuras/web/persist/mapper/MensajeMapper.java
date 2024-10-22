package com.alcuras.web.persist.mapper;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.MensajeDTO;
import com.alcuras.web.persist.entities.Mensaje;

@Component
public class MensajeMapper {
	private static MensajeMapper transformerMensaje =  new MensajeMapper();
	

	public void reset(){
		transformerMensaje = null;
	}
	
	public static MensajeMapper getInstance(){
		if (transformerMensaje == null){
			transformerMensaje = new MensajeMapper();
		}
		return transformerMensaje;
	}	
	
	public Mensaje map(MensajeDTO mensaje){
		
		Mensaje entityMensaje = new Mensaje();
		
		try {
			PropertyUtils.copyProperties(entityMensaje, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return entityMensaje;
	}
	
	public MensajeDTO map(Mensaje entityMensaje){
		
		MensajeDTO mensaje = new MensajeDTO();
		
		try {
			PropertyUtils.copyProperties(mensaje, entityMensaje);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return mensaje;
	}
	
}
