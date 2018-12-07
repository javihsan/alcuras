package com.alcuras.web.persist.transformer;

import org.apache.commons.beanutils.PropertyUtils;

import com.alcuras.web.negocio.dto.WebDTO;
import com.alcuras.web.persist.entities.Web;


public class WebTransformer {
	private static WebTransformer transformerWeb =  new WebTransformer();

	public void reset(){
		transformerWeb = null;
	}
	
	public static WebTransformer getInstance(){
		if (transformerWeb == null){
			transformerWeb = new WebTransformer();
		}
		return transformerWeb;
	}	
	
	public Web transformDTOToEntity(WebDTO web){
		
		Web entityWeb = new Web();
		
		try {
			PropertyUtils.copyProperties(entityWeb, web);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return entityWeb;
	}
	
	public WebDTO transformEntityToDTO(Web entityWeb){
		
		WebDTO web = new WebDTO();
		
		try {
			PropertyUtils.copyProperties(web, entityWeb);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return web;
	}
	
}
