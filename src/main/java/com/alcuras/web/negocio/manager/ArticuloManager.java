package com.alcuras.web.negocio.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.ArticuloDTO;
import com.alcuras.web.negocio.utils.NullAwareBeanUtilsBean;
import com.alcuras.web.persist.dao.ArticuloDAO;
import com.alcuras.web.persist.entities.Articulo;
import com.alcuras.web.persist.mapper.ArticuloMapper;



@Component
@Scope(value = "singleton")
public class ArticuloManager implements IArticuloManager {
 
	@Autowired
	private ArticuloDAO articuloDAO;

	@Autowired
	private ArticuloMapper mapper;
	
	public static final String ID_FORO = "artIdForo";
	public static final String ACTIVADO = "artActivado";
	public static final String ORDER_KY_DESC = "-__key__";
	
	public ArticuloManager() {
	
	}
	
	/**
	 * Crea un articulo
	 * 
	 * @param articuloDTO
	 * @return ArticuloDTO
	 */
	public ArticuloDTO create(ArticuloDTO articuloDTO) {
		Articulo articulo = mapper.map(articuloDTO);
		articulo = articuloDAO.create(articulo);
		return mapper.map(articulo);
	}
		
	/**
	 * Elimina un articulo
	 * 
	 * @param id
	 */
	public ArticuloDTO remove(long id) {
		Articulo articulo = articuloDAO.get(id);
		articuloDAO.delete(id);
		if (articulo == null) {
			return null;
		}
		return mapper.map(articulo);
	}
		
	/**
	 * Modifica un articulo
	 * 
	 * @param encuestaDTO
	 * @return EncuestaDTO
	 */
	public ArticuloDTO update(ArticuloDTO articuloDTO) {
		Articulo articulo = mapper.map(articuloDTO);
		Articulo oldArticulo = articuloDAO.get(articuloDTO.getArtId());
		try {
			new NullAwareBeanUtilsBean().copyProperties(articulo, oldArticulo);
		} catch (Exception e) {
		}
		articulo = articuloDAO.update(articulo);
		if (articulo == null) {
			return null;
		}
		return mapper.map(articulo);
	}


	/**
	 * Obtiene un articulo
	 * 
	 * @param id
	 * @return ArticuloDTO
	 */
	public ArticuloDTO getById(long id) {
		Articulo articulo = articuloDAO.get(id);
		if (articulo == null) {
			return null;
		}
		return mapper.map(articulo);
	}

	
	/**
	 * Obtiene todos los articulos activos de un foro
	 * 
	 * @return List<ArticuloDTO>
	 */
	public List<ArticuloDTO> getArticulo(long artIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, artIdForo);
		filters.put(ACTIVADO, 1);
		List<String> orders = new ArrayList<String>();
		orders.add(ORDER_KY_DESC);
		List<Articulo> listArticulo = articuloDAO.listOrderFilter(filters, orders);
		List<ArticuloDTO> list = new ArrayList<ArticuloDTO>();
		for (Articulo articulo : listArticulo) {
			list.add(mapper.map(articulo));
		}
		return list;		
	}

	
	/**
	 * Obtiene todos los articulos de un foro
	 * 
	 * @return List<ArticuloDTO>
	 */
	public List<ArticuloDTO> getArticuloAdmin(long artIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, artIdForo);
		List<String> orders = new ArrayList<String>();
		orders.add(ORDER_KY_DESC);
		List<Articulo> listArticulo = articuloDAO.listOrderFilter(filters, orders);
		List<ArticuloDTO> list = new ArrayList<ArticuloDTO>();
		for (Articulo articulo : listArticulo) {
			list.add(mapper.map(articulo));
		}
		return list;		
	}	

 }