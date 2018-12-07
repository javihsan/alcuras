package com.alcuras.web.negocio.dao;

import java.util.List;

import com.alcuras.web.negocio.dto.WebDTO;

public interface WebDAO {

	public WebDTO create(WebDTO web) throws Exception;

	public WebDTO remove(long id) throws Exception;

	public WebDTO update(WebDTO web) throws Exception;

	public WebDTO getById(long id);

	public List<WebDTO> getWeb();

	public WebDTO getWebByWebParametro(String webParametro);
	
	public WebDTO getWebBBDDByWebParametro(String webParametro);
 }