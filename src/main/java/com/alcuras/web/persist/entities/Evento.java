package com.alcuras.web.persist.entities;

import java.util.Date;

import com.alcuras.datastore.data.StorableWithModificationTimestamp;
import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * The persistent class for the Evento entity
 * 
 */
@Entity
@Cache
public class Evento extends StorableWithModificationTimestamp<Long> { 

	@Id
	private Long eveId;
	
	@Index
	private int eveActivado;

	private String eveAsuntoEs;
	
	private String eveAsuntoEn;
	
	private String eveAsuntoDe;

	private Text eveTextoEs;
	
	private Text eveTextoEn;
	
	private Text eveTextoDe;
	
	 @Index
    private Date eveFechaDesde;

    private Date eveFechaHasta;

	@Index
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

    /** Para ser compatible con IStorable */
   	public Long getId() {
   		return eveId;
   	}

   	public void setId(Long id) {
   		this.eveId = id;
   	}
   	/**  */
   	
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