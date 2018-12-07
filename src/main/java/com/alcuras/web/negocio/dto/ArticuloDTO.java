package com.alcuras.web.negocio.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.text.StringEscapeUtils;

import com.alcuras.web.negocio.utils.LangEnum;
import com.google.appengine.api.datastore.Text;


public class ArticuloDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long artId;

	private int artActivado;

	private String artAsuntoEs;
	
	private String artAsuntoEn;
	
	private String artAsuntoDe;

	private Text artTextoEs;
	
	private Text artTextoEn;
	
	private Text artTextoDe;
	
	private Date artFecha;

	private Long artIdForo;

	private String artIdUsu;
 
	private String artFichero;

	private String artImagen;
	
	private String artLink;
	
	private int artTipoLink;
	
    public ArticuloDTO() {
    }

    public Long getArtId() {
		return artId;
	}

	public void setArtId(Long artId) {
		this.artId = artId;
	}

	public int getArtActivado() {
		return artActivado;
	}

	public void setArtActivado(int artActivado) {
		this.artActivado = artActivado;
	}

	public String getArtAsunto(String lang) {
		switch (LangEnum.valueOf(lang.toUpperCase()))
		{
			case ES: return StringEscapeUtils.escapeHtml4(getArtAsuntoEs());
			case EN: return StringEscapeUtils.escapeHtml4(getArtAsuntoEn());
			case DE: return StringEscapeUtils.escapeHtml4(getArtAsuntoDe());
		}
    		return null;
	}
	
	public String getArtTexto(String lang) {
		switch (LangEnum.valueOf(lang.toUpperCase()))
		{
			case ES: return StringEscapeUtils.escapeHtml4(getArtTextoEs().getValue());
			case EN: return StringEscapeUtils.escapeHtml4(getArtTextoEn().getValue());
			case DE: return StringEscapeUtils.escapeHtml4(getArtTextoDe().getValue());
		}
		return null;
	}
	
	public String getArtTextoNoScape(String lang) {
		switch (LangEnum.valueOf(lang.toUpperCase()))
		{
			case ES: return getArtTextoEs().getValue();
			case EN: return getArtTextoEn().getValue();
			case DE: return getArtTextoDe().getValue();
		}
		return null;
	}

	public String getArtAsuntoEs() {
		return artAsuntoEs;
	}

	public void setArtAsuntoEs(String artAsuntoEs) {
		this.artAsuntoEs = artAsuntoEs;
	}

	public String getArtAsuntoEn() {
		return artAsuntoEn;
	}

	public void setArtAsuntoEn(String artAsuntoEn) {
		this.artAsuntoEn = artAsuntoEn;
	}

	public String getArtAsuntoDe() {
		return artAsuntoDe;
	}

	public void setArtAsuntoDe(String artAsuntoDe) {
		this.artAsuntoDe = artAsuntoDe;
	}

	public Text getArtTextoEs() {
		return artTextoEs;
	}

	public void setArtTextoEs(Text artTextoEs) {
		this.artTextoEs = artTextoEs;
	}

	public Text getArtTextoEn() {
		return artTextoEn;
	}

	public void setArtTextoEn(Text artTextoEn) {
		this.artTextoEn = artTextoEn;
	}

	public Text getArtTextoDe() {
		return artTextoDe;
	}

	public void setArtTextoDe(Text artTextoDe) {
		this.artTextoDe = artTextoDe;
	}

	public Date getArtFecha() {
		return artFecha;
	}

	public void setArtFecha(Date artFecha) {
		this.artFecha = artFecha;
	}

	public Long getArtIdForo() {
		return artIdForo;
	}

	public void setArtIdForo(Long artIdForo) {
		this.artIdForo = artIdForo;
	}

	public String getArtIdUsu() {
		return artIdUsu;
	}
	
	public String getArtIdUsuHtml() {
		return StringEscapeUtils.escapeHtml4(artIdUsu);
	}

	public void setArtIdUsu(String artIdUsu) {
		this.artIdUsu = artIdUsu;
	}

	public String getArtFichero() {
		return artFichero;
	}

	public void setArtFichero(String artFichero) {
		this.artFichero = artFichero;
	}

	public String getArtImagen() {
		return artImagen;
	}

	public void setArtImagen(String artImagen) {
		this.artImagen = artImagen;
	}

	public String getArtLink() {
		return artLink;
	}

	public void setArtLink(String artLink) {
		this.artLink = artLink;
	}

	public int getArtTipoLink() {
		return artTipoLink;
	}

	public void setArtTipoLink(int artTipoLink) {
		this.artTipoLink = artTipoLink;
	}
	
}