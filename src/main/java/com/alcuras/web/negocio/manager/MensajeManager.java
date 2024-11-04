package com.alcuras.web.negocio.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alcuras.web.negocio.dto.MensajeDTO;
import com.alcuras.web.negocio.utils.NullAwareBeanUtilsBean;
import com.alcuras.web.persist.dao.MensajeDAO;
import com.alcuras.web.persist.entities.Mensaje;
import com.alcuras.web.persist.mapper.MensajeMapper;

@Component
@Scope(value = "singleton")
public class MensajeManager implements IMensajeManager {
	
	@Autowired
	private MensajeDAO mensajeDAO;

	@Autowired
	private MensajeMapper mapper;
	
	public static final String ID_FORO = "menIdForo";
	public static final String ACTIVADO = "menActivado";
	
	public MensajeManager() {
	
	}

	
	/**
	 * Crea un mensaje
	 * 
	 * @param mensajeDTO
	 * @return MensajeDTO
	 */
	public MensajeDTO create(MensajeDTO mensajeDTO) {
		Mensaje mensaje = mapper.map(mensajeDTO);
		mensaje = mensajeDAO.create(mensaje);
		return mapper.map(mensaje);
	}

	
	/**
	 * Elimina un mensaje
	 * 
	 * @param id
	 */
	public MensajeDTO remove(long id) {
		Mensaje mensaje = mensajeDAO.get(id);
		mensajeDAO.delete(id);
		if (mensaje == null) {
			return null;
		}
		return mapper.map(mensaje);
	}
	
	
	/**
	 * Modifica un mensaje
	 * 
	 * @param encuestaDTO
	 * @return EncuestaDTO
	 */
	public MensajeDTO update(MensajeDTO mensajeDTO) {
		Mensaje mensaje = mapper.map(mensajeDTO);
		Mensaje oldMensaje = mensajeDAO.get(mensajeDTO.getMenId());
		try {
			new NullAwareBeanUtilsBean().copyProperties(mensaje, oldMensaje);
		} catch (Exception e) {
		}
		mensaje = mensajeDAO.update(mensaje);
		if (mensaje == null) {
			return null;
		}
		return mapper.map(mensaje);
	}
	
	/**
	 * Obtiene un mensaje
	 * 
	 * @param id
	 * @return MensajeDTO
	 */
	public MensajeDTO getById(long id) {
		Mensaje mensaje = mensajeDAO.get(id);
		if (mensaje == null) {
			return null;
		}
		return mapper.map(mensaje);
	}
	
	
	/**
	 * Obtiene todos los mensajes activos de un foro
	 * 
	 * @return List<MensajeDTO>
	 */
	public List<MensajeDTO> getMensaje(long menIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, menIdForo);
		filters.put(ACTIVADO, 1);
		List<String> orders = new ArrayList<String>();
		orders.add("-menFecha");
		List<Mensaje> listMensaje = mensajeDAO.listOrderFilter(filters, orders);
		List<MensajeDTO> list = new ArrayList<MensajeDTO>();
		for (Mensaje mensaje : listMensaje) {
			list.add(mapper.map(mensaje));
		}
		return list;		
	}
	
	/**
	 * Obtiene todos los mensajes de un foro
	 * 
	 * @return List<MensajeDTO>
	 */
	public List<MensajeDTO> getMensajeAdmin(long menIdForo) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(ID_FORO, menIdForo);
		List<String> orders = new ArrayList<String>();
		orders.add("-menFecha");
		List<Mensaje> listMensaje = mensajeDAO.listOrderFilter(filters, orders);
		List<MensajeDTO> list = new ArrayList<MensajeDTO>();
		for (Mensaje mensaje : listMensaje) {
			list.add(mapper.map(mensaje));
		}
		return list;		
	}	

 }