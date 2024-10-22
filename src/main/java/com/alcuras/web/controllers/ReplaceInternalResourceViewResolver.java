package com.alcuras.web.controllers;
import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class ReplaceInternalResourceViewResolver extends InternalResourceViewResolver {

	protected String prefixWEB;
	protected String sufixWEB;
	protected String identSHOP;
	protected String prefixSHOP;
	protected String sufixSHOP;
	     
    @Override
    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
    	
		int indx = viewName.indexOf(getIdentSHOP());
		if (indx>=0){
			viewName = getPrefixSHOP() + viewName;
			viewName += getSufixSHOP();
		} else{
			viewName = getPrefixWEB() + viewName;
			viewName += getSufixWEB();
		}
    	
        return super.resolveViewName(viewName, locale);
    }

	public String getPrefixWEB() {
		return prefixWEB;
	}

	public void setPrefixWEB(String prefixWEB) {
		this.prefixWEB = prefixWEB;
	}

	public String getSufixWEB() {
		return sufixWEB;
	}

	public void setSufixWEB(String sufixWEB) {
		this.sufixWEB = sufixWEB;
	}
    
    
	public String getIdentSHOP() {
		return identSHOP;
	}

	public void setIdentSHOP(String identSHOP) {
		this.identSHOP = identSHOP;
	}

	public String getPrefixSHOP() {
		return prefixSHOP;
	}

	public void setPrefixSHOP(String prefixSHOP) {
		this.prefixSHOP = prefixSHOP;
	}

	public String getSufixSHOP() {
		return sufixSHOP;
	}

	public void setSufixSHOP(String sufixSHOP) {
		this.sufixSHOP = sufixSHOP;
	}
    
    
    
}
