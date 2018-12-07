package com.alcuras.web.negocio.dto;

import java.io.Serializable;

import org.apache.commons.text.StringEscapeUtils;

import com.alcuras.web.negocio.utils.LangEnum;
import com.google.appengine.api.datastore.Text;


public class WebDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long webId;

	private int webActivado;

	private String webParametro;
 
	private String webValorFile;
	
	private Text webValorTextEs;
	
	private Text webValorTextEn;
	
	private Text webValorTextDe;

    public WebDTO() {
    }

	public Long getWebId() {
		return this.webId;
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
	
	public String getWebValorText(String lang) {
		switch (LangEnum.valueOf(lang.toUpperCase()))
		{
			case ES: return StringEscapeUtils.escapeHtml4(getWebValorTextEs().getValue());
			case EN: return StringEscapeUtils.escapeHtml4(getWebValorTextEn().getValue());
			case DE: return StringEscapeUtils.escapeHtml4(getWebValorTextDe().getValue());
		}
		return null;
	}

}