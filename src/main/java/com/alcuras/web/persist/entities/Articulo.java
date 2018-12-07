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
 * The persistent class for the Articulo entity
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="getArticulo", query = "SELECT t FROM Articulo t WHERE t.artIdForo = :artIdForo and t.artActivado =1 order by t.artId desc"),
	@NamedQuery(name="getArticuloAdmin", query = "SELECT t FROM Articulo t WHERE t.artIdForo = :artIdForo order by t.artId desc"),
})
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long artId;

	private int artActivado;

	private String artAsuntoEs;
	
	private String artAsuntoEn;
	
	private String artAsuntoDe;

	private Text artTextoEs;
	
	private Text artTextoEn;
	
	private Text artTextoDe;
	
    @Temporal( TemporalType.DATE)
	private Date artFecha;

	private Long artIdForo;

	private String artIdUsu;
 
	private String artFichero;

	private String artImagen;
	
	private String artLink;
	
	private int artTipoLink;
	
    public Articulo() {
    	
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