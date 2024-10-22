package com.alcuras.web.persist.entities;

import java.util.Date;

import com.alcuras.datastore.data.StorableWithModificationTimestamp;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * The persistent class for the Mensaje entity
 * 
 */
@Entity
@Cache
public class Mensaje extends StorableWithModificationTimestamp<Long> { 

	@Id
	private Long menId;

	@Index
	private int menActivado;

	private String menAsunto;

	@Index
	private Date menFecha;

	@Index
	private Long menIdForo;

	private int menIdPadre;

	private String menIdUsu;

	private String menRecorrido;
 
	private String menTexto;

    public Mensaje() {
    	
    }

    /** Para ser compatible con IStorable */
   	public Long getId() {
   		return menId;
   	}

   	public void setId(Long id) {
   		this.menId = id;
   	}
   	/**  */
   	
   	public Long getMenId() {
   		return menId;
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

	public void setMenTexto(String menTexto) {
		this.menTexto = menTexto;
	}

}