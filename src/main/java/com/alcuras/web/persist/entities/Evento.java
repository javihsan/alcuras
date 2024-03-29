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

import com.google.appengine.api.datastore.Text;


/**
 * The persistent class for the Evento entity
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="getEvento", query = "SELECT t FROM Evento t WHERE t.eveIdForo = :eveIdForo and t.eveActivado =1 order by t.eveFechaDesde desc"),
	@NamedQuery(name="getEventoAdmin", query = "SELECT t FROM Evento t WHERE t.eveIdForo = :eveIdForo order by t.eveFechaDesde desc"),
})
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long eveId;

	private int eveActivado;

	private String eveAsuntoEs;
	
	private String eveAsuntoEn;
	
	private String eveAsuntoDe;

	private Text eveTextoEs;
	
	private Text eveTextoEn;
	
	private Text eveTextoDe;
	
    @Temporal( TemporalType.TIMESTAMP)
    private Date eveFechaDesde;

    @Temporal( TemporalType.TIMESTAMP)
	private Date eveFechaHasta;

	private Long eveIdForo;

	private String eveIdUsu;
 
	private String eveFichero;
	
	private String eveFicheroName;

	private String eveImagen;
	
	private String eveImagenName;
	
	private String eveLink;
	
	private int eveTipoLink;
	
    public Evento() {
    	
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