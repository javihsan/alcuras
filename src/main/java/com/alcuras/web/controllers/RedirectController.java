package com.alcuras.web.controllers;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/*-*")
public class RedirectController {
	 
	private Map viewMap;
	  
	@RequestMapping("")
	private ModelAndView inicioInit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		  
		String path = arg0.getRequestURI().toLowerCase();
		String view = null;
		ModelAndView mav = new ModelAndView();
		
		Set setKeys = getViewMap().keySet();
		for (Object key : setKeys) {
			if (path.indexOf((String)key)!=-1){
				view = (String)getViewMap().get(key);
				mav = new ModelAndView(view);
				mav.addObject("path", path);
				//break;
			}
		}
		
		return mav;
	}

	public Map getViewMap() {
		return viewMap;
	}

	public void setViewMap(Map viewMap) {
		this.viewMap = viewMap;
	}
	
	
	
}
