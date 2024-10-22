package com.alcuras.web.persist.entities;

import java.util.Date;

import com.alcuras.datastore.data.StorableWithModificationTimestamp;
import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;



 
/**
 * The persistent class for the Articulo entity
 * 
 */
@Entity
@Cache
public class Articulo extends StorableWithModificationTimestamp<Long> { 

	@Id
	private Long artId;

	@Index
	private int artActivado;

	private String artAsuntoEs;
	
	private String artAsuntoEn;
	
	private String artAsuntoDe;

	private Text artTextoEs;
	
	private Text artTextoEn;
	
	private Text artTextoDe;
	
	private Date artFecha;

	@Index
	private Long artIdForo;

	private String artIdUsu;
 
	private String artFichero;
	
	private String artFicheroName;

	private String artImagen;
	
	private String artImagenName;
	
	private String artLink;
	
	private int artTipoLink;
	
    public Articulo() {
    	
    }
    
    /** Para ser compatible con IStorable */
	public Long getId() {
		return artId;
	}

	public void setId(Long id) {
		this.artId = id;
	}
	/**  */
	
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

	public void setArtIdUsu(String artIdUsu) {
		this.artIdUsu = artIdUsu;
	}

	public String getArtFichero() {
		return artFichero;
	}

	public void setArtFichero(String artFichero) {
		this.artFichero = artFichero;
	}

	public String getArtFicheroName() {
		return artFicheroName;
	}

	public void setArtFicheroName(String artFicheroName) {
		this.artFicheroName = artFicheroName;
	}
	
	public String getArtImagen() {
		return artImagen;
	}

	public void setArtImagen(String artImagen) {
		this.artImagen = artImagen;
	}
	
	public String getArtImagenName() {
		return artImagenName;
	}

	public void setArtImagenName(String artImagenName) {
		this.artImagenName = artImagenName;
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