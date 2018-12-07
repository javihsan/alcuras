package com.alcuras.web.negocio.dao;

import java.util.List;

import com.alcuras.web.negocio.dto.MensajeDTO;

public interface MensajeDAO {

	public MensajeDTO create(MensajeDTO mensaje) throws Exception;

	public MensajeDTO remove(long id) throws Exception;

	public MensajeDTO update(MensajeDTO mensaje) throws Exception;

	public MensajeDTO getById(long id);

	public List<MensajeDTO> getMensaje(long menIdForo);

	public List<MensajeDTO> getMensajeAdmin(long menIdForo);
	
 }