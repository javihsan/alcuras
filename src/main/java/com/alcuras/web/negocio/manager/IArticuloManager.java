package com.alcuras.web.negocio.manager;

import java.util.List;

import com.alcuras.web.negocio.dto.ArticuloDTO;

public interface IArticuloManager {
 
	public ArticuloDTO create(ArticuloDTO articulo) throws Exception;

	public ArticuloDTO remove(long id) throws Exception;

	public ArticuloDTO update(ArticuloDTO articulo) throws Exception;

	public ArticuloDTO getById(long id);

	public List<ArticuloDTO> getArticulo(long artIdForo);

	public List<ArticuloDTO> getArticuloAdmin(long artIdForo);
	

 }