package com.alcuras.web.negocio.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.text.StringEscapeUtils;


public class MensajeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long menId;

	private int menActivado;
 
	private String menAsunto;

	private Date menFecha;

	private Long menIdForo;

	private int menIdPadre;

	private String menIdUsu;

	private String menRecorrido;

	private String menTexto;
	
    public MensajeDTO() {
    }

	public Long getMenId() {
		return this.menId;
	}

	public void setMenId(Long menId) {
		this.menId = menId;
	}

	public int getMenActivado() {
		return this.menActivado;
	}

	public void setMenActivado(int menActivado) {
		this.menActivado = menActivado;
	}

	public String getMenAsunto() {
		return this.menAsunto;
	}

	public String getMenAsuntoHtml() {
		return StringEscapeUtils.escapeHtml4(this.menAsunto);
	}
	
	public void setMenAsunto(String menAsunto) {
		this.menAsunto = menAsunto;
	}
	
	public Date getMenFecha() {
		return this.menFecha;
	}

	public void setMenFecha(Date menFecha) {
		this.menFecha = menFecha;
	}

	public Long getMenIdForo() {
		return menIdForo;
	}

	public void setMenIdForo(Long menIdForo) {
		this.menIdForo = menIdForo;
	}

	public int getMenIdPadre() {
		return menIdPadre;
	}

	public void setMenIdPadre(int menIdPadre) {
		this.menIdPadre = menIdPadre;
	}

	public String getMenIdUsu() {
		return menIdUsu;
	}

	public String getMenIdUsuHtml() {
		return StringEscapeUtils.escapeHtml4(menIdUsu);
	}
	
	public void setMenIdUsu(String menIdUsu) {
		this.menIdUsu = menIdUsu;
	}
	public String getMenRecorrido() {
		return this.menRecorrido;
	}

	public void setMenRecorrido(String menRecorrido) {
		this.menRecorrido = menRecorrido;
	}

	public String getMenTexto() {
		return this.menTexto;
	}

	public String getMenTextoHtml() {
		return StringEscapeUtils.escapeHtml4(menTexto);
	}
	
	public void setMenTexto(String menTexto) {
		this.menTexto = menTexto;
	}

	
	
}