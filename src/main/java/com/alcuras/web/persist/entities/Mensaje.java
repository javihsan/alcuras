package com.alcuras.web.persist.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Mensaje entity
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="getMensaje", query = "SELECT t FROM Mensaje t WHERE t.menIdForo = :menIdForo and t.menActivado =1 order by t.menFecha desc"),
	@NamedQuery(name="getMensajeAdmin", query = "SELECT t FROM Mensaje t WHERE t.menIdForo = :menIdForo order by t.menFecha desc"),
})
public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long menId;

	private int menActivado;

	private String menAsunto;

    @Temporal( TemporalType.TIMESTAMP)
	private Date menFecha;

	private Long menIdForo;

	private int menIdPadre;

	private String menIdUsu;

	private String menRecorrido;
 
	private String menTexto;

    public Mensaje() {
    	
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