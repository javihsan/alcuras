package com.alcuras.web.persist.transformer;

import org.apache.commons.beanutils.PropertyUtils;

import com.alcuras.web.negocio.dto.ArticuloDTO;
import com.alcuras.web.persist.entities.Articulo;


public class ArticuloTransformer {
	private static ArticuloTransformer transformerArticulo =  new ArticuloTransformer();
	

	public void reset(){
		transformerArticulo = null;
	}
	
	public static ArticuloTransformer getInstance(){
		if (transformerArticulo == null){
			transformerArticulo = new ArticuloTransformer();
		}
		return transformerArticulo;
	}	
	
	public Articulo transformDTOToEntity(ArticuloDTO articulo){
		
		Articulo entityArticulo = new Articulo();
		
		try {
			PropertyUtils.copyProperties(entityArticulo, articulo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return entityArticulo;
	}
	
	public ArticuloDTO transformEntityToDTO(Articulo entityArticulo){
		
		ArticuloDTO articulo = new ArticuloDTO();
		
		try {
			PropertyUtils.copyProperties(articulo, entityArticulo);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return articulo;
	}
	
}
