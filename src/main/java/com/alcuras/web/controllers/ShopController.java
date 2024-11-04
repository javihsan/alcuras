package com.alcuras.web.controllers;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/*shop*") 
public class ShopController {
	
	private Map viewMap;
	private String view;
		   
	@RequestMapping("")
	private ModelAndView inicioInit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView();
	 
		String path = arg0.getRequestURI().toLowerCase();
		
		Set setKeys = getViewMap().keySet();
		for (Object key : setKeys) {
			if (path.indexOf((String)key)!=-1){
				setView((String)getViewMap().get(key));
				mav = new ModelAndView(getView());
				mav.addObject("path", path);
				break;
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

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	
}
