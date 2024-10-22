package com.alcuras.web.negocio.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.text.StringEscapeUtils;

import com.alcuras.web.negocio.utils.LangEnum;
import com.google.appengine.api.datastore.Text;


public class EventoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long eveId;
 
	private int eveActivado;

	private String eveAsuntoEs;
	
	private String eveAsuntoEn;
	
	private String eveAsuntoDe;

	private Text eveTextoEs;
	
	private Text eveTextoEn;
	
	private Text eveTextoDe;
	
	private Date eveFechaDesde;
	
	private Date eveFechaHasta;

	private Long eveIdForo;

	private String eveIdUsu;
 
	private String eveFichero;
	
	private String eveFicheroName;

	private String eveImagen;
	
	private String eveImagenName;
	
	private String eveLink;
	
	private int eveTipoLink;
	
    public EventoDTO() {
    }

    public Long getEveId() {
		return eveId;
	}

	public void setEveId(Long eveId) {
		this.eveId = eveId;
	}

	public int getEveActivado() {
		return eveActivado;
	}

	public void setEveActivado(int eveActivado) {
		this.eveActivado = eveActivado;
	}

	public String getEveAsunto(String lang) {
		switch (LangEnum.valueOf(lang.toUpperCase()))
		{
			case ES: return StringEscapeUtils.escapeHtml4(getEveAsuntoEs());
			case EN: return StringEscapeUtils.escapeHtml4(getEveAsuntoEn());
			case DE: return StringEscapeUtils.escapeHtml4(getEveAsuntoDe());
		}
    		return null;
	}
	
	public String getEveTexto(String lang) {
		switch (LangEnum.valueOf(lang.toUpperCase()))
		{
			case ES: return StringEscapeUtils.escapeHtml4(getEveTextoEs().getValue());
			case EN: return StringEscapeUtils.escapeHtml4(getEveTextoEn().getValue());
			case DE: return StringEscapeUtils.escapeHtml4(getEveTextoDe().getValue());
		}
		return null;
	}
	
	public String getEveTextoNoScape(String lang) {
		switch (LangEnum.valueOf(lang.toUpperCase()))
		{
			case ES: return getEveTextoEs().getValue();
			case EN: return getEveTextoEn().getValue();
			case DE: return getEveTextoDe().getValue();
		}
		return null;
	}

	public String getEveAsuntoEs() {
		return eveAsuntoEs;
	}

	public void setEveAsuntoEs(String eveAsuntoEs) {
		this.eveAsuntoEs = eveAsuntoEs;
	}

	public String getEveAsuntoEn() {
		return eveAsuntoEn;
	}

	public void setEveAsuntoEn(String eveAsuntoEn) {
		this.eveAsuntoEn = eveAsuntoEn;
	}

	public String getEveAsuntoDe() {
		return eveAsuntoDe;
	}

	public void setEveAsuntoDe(String eveAsuntoDe) {
		this.eveAsuntoDe = eveAsuntoDe;
	}

	public Text getEveTextoEs() {
		return eveTextoEs;
	}

	public void setEveTextoEs(Text eveTextoEs) {
		this.eveTextoEs = eveTextoEs;
	}

	public Text getEveTextoEn() {
		return eveTextoEn;
	}

	public void setEveTextoEn(Text eveTextoEn) {
		this.eveTextoEn = eveTextoEn;
	}

	public Text getEveTextoDe() {
		return eveTextoDe;
	}

	public void setEveTextoDe(Text eveTextoDe) {
		this.eveTextoDe = eveTextoDe;
	}

	public Date getEveFechaDesde() {
		return eveFechaDesde;
	}

	public void setEveFechaDesde(Date eveFechaDesde) {
		this.eveFechaDesde = eveFechaDesde;
	}

	public Date getEveFechaHasta() {
		return eveFechaHasta;
	}

	public void setEveFechaHasta(Date eveFechaHasta) {
		this.eveFechaHasta = eveFechaHasta;
	}

	public Long getEveIdForo() {
		return eveIdForo;
	}

	public void setEveIdForo(Long eveIdForo) {
		this.eveIdForo = eveIdForo;
	}

	public String getEveIdUsu() {
		return eveIdUsu;
	}
	
	public String getEveIdUsuHtml() {
		return StringEscapeUtils.escapeHtml4(eveIdUsu);
	}

	public void setEveIdUsu(String eveIdUsu) {
		this.eveIdUsu = eveIdUsu;
	}

	public String getEveFichero() {
		return eveFichero;
	}

	public void setEveFichero(String eveFichero) {
		this.eveFichero = eveFichero;
	}

	public String getEveFicheroName() {
		return eveFicheroName;
	}

	public void setEveFicheroName(String eveFicheroName) {
		this.eveFicheroName = eveFicheroName;
	}
	
	public String getEveImagen() {
		return eveImagen;
	}

	public void setEveImagen(String eveImagen) {
		this.eveImagen = eveImagen;
	}

	public String getEveImagenName() {
		return eveImagenName;
	}

	public void setEveImagenName(String eveImagenName) {
		this.eveImagenName = eveImagenName;
	}
	
	public String getEveLink() {
		return eveLink;
	}

	public void setEveLink(String eveLink) {
		this.eveLink = eveLink;
	}

	public int getEveTipoLink() {
		return eveTipoLink;
	}

	public void setEveTipoLink(int eveTipoLink) {
		this.eveTipoLink = eveTipoLink;
	}
	
}