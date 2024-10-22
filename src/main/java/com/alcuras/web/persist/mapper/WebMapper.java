package com.alcuras.web.persist.mapper;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.WebDTO;
import com.alcuras.web.persist.entities.Web;

@Component
public class WebMapper {
	private static WebMapper transformerWeb =  new WebMapper();

	public void reset(){
		transformerWeb = null;
	}
	
	public static WebMapper getInstance(){
		if (transformerWeb == null){
			transformerWeb = new WebMapper();
		}
		return transformerWeb;
	}	
	
	public Web map(WebDTO web){
		
		Web entityWeb = new Web();
		
		try {
			PropertyUtils.copyProperties(entityWeb, web);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return entityWeb;
	}
	
	public WebDTO map(Web entityWeb){
		
		WebDTO web = new WebDTO();
		
		try {
			PropertyUtils.copyProperties(web, entityWeb);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return web;
	}
	
}
