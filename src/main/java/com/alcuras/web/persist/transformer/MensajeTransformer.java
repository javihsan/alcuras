package com.alcuras.web.persist.transformer;

import org.apache.commons.beanutils.PropertyUtils;

import com.alcuras.web.negocio.dto.MensajeDTO;
import com.alcuras.web.persist.entities.Mensaje;


public class MensajeTransformer {
	private static MensajeTransformer transformerMensaje =  new MensajeTransformer();
	

	public void reset(){
		transformerMensaje = null;
	}
	
	public static MensajeTransformer getInstance(){
		if (transformerMensaje == null){
			transformerMensaje = new MensajeTransformer();
		}
		return transformerMensaje;
	}	
	
	public Mensaje transformDTOToEntity(MensajeDTO mensaje){
		
		Mensaje entityMensaje = new Mensaje();
		
		try {
			PropertyUtils.copyProperties(entityMensaje, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return entityMensaje;
	}
	
	public MensajeDTO transformEntityToDTO(Mensaje entityMensaje){
		
		MensajeDTO mensaje = new MensajeDTO();
		
		try {
			PropertyUtils.copyProperties(mensaje, entityMensaje);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return mensaje;
	}
	
}
