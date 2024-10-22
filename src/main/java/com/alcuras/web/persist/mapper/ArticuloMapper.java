package com.alcuras.web.persist.mapper;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.ArticuloDTO;
import com.alcuras.web.persist.entities.Articulo;

@Component
public class ArticuloMapper {
	private static ArticuloMapper transformerArticulo =  new ArticuloMapper();
	

	public void reset(){
		transformerArticulo = null;
	}
	
	public static ArticuloMapper getInstance(){
		if (transformerArticulo == null){
			transformerArticulo = new ArticuloMapper();
		}
		return transformerArticulo;
	}	
	
	public Articulo map(ArticuloDTO articulo){
		
		Articulo entityArticulo = new Articulo();
		
		try {
			PropertyUtils.copyProperties(entityArticulo, articulo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return entityArticulo;
	}
	
	public ArticuloDTO map(Articulo entityArticulo){
		
		ArticuloDTO articulo = new ArticuloDTO();
		
		try {
			PropertyUtils.copyProperties(articulo, entityArticulo);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return articulo;
	}
	
}
