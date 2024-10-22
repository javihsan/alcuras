package com.alcuras.web.persist.entities;

import com.alcuras.datastore.data.StorableWithModificationTimestamp;
import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * The persistent class for the web database table.
 * 
 */
@Entity
@Cache
public class Web extends StorableWithModificationTimestamp<Long> { 

	@Id
	private Long webId;
	
	@Index
	private int webActivado;
	
	@Index
	private String webParametro;
 
	private String webValorFile;
	
	private String webValorFileName;
	
	private Text webValorTextEs;
	
	private Text webValorTextEn;
	
	private Text webValorTextDe;

    public Web() {
    }

    /** Para ser compatible con IStorable */
   	public Long getId() {
   		return webId;
   	}

   	public void setId(Long id) {
   		this.webId = id;
   	}
   	/**  */
   	
   	public Long getWebId() {
   		return webId;
   	}

   	public void setWebId(Long webId) {
   		this.webId = webId;
   	}

	public int getWebActivado() {
		return this.webActivado;
	}

	public void setWebActivado(int webActivado) {
		this.webActivado = webActivado;
	}

	public String getWebParametro() {
		return this.webParametro;
	}

	public void setWebParametro(String webParametro) {
		this.webParametro = webParametro;
	}

	public String getWebValorFile() {
		return webValorFile;
	}

	public void setWebValorFile(String webValorFile) {
		this.webValorFile = webValorFile;
	}

	public String getWebValorFileName() {
		return webValorFileName;
	}

	public void setWebValorFileName(String webValorFileName) {
		this.webValorFileName = webValorFileName;
	}
	
	public Text getWebValorTextEs() {
		return webValorTextEs;
	}

	public void setWebValorTextEs(Text webValorTextEs) {
		this.webValorTextEs = webValorTextEs;
	}

	public Text getWebValorTextEn() {
		return webValorTextEn;
	}

	public void setWebValorTextEn(Text webValorTextEn) {
		this.webValorTextEn = webValorTextEn;
	}

	public Text getWebValorTextDe() {
		return webValorTextDe;
	}

	public void setWebValorTextDe(Text webValorTextDe) {
		this.webValorTextDe = webValorTextDe;
	}	

}