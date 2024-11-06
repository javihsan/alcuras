package com.alcuras.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	  
	private String view;
	   
	@RequestMapping("")
	private ModelAndView initDefault(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		arg1.sendRedirect("/inicio");
		return null;
	}
	 
	@RequestMapping(value={"inicio", "inicioAdmin", "inicioManager", "inicioExternal"})
	private ModelAndView inicioInit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView(getView());
	
		String path = arg0.getRequestURI();
		if (path.indexOf(ConstantsController.ADMIN)!=-1){
			mav = new ModelAndView(getView()+ConstantsController.ADMIN);
		} else if (path.indexOf(ConstantsController.MANAGER)!=-1){
			mav = new ModelAndView(getView()+ConstantsController.MANAGER);
		} else if (path.indexOf(ConstantsController.EXTERNAL)!=-1){
			mav = new ModelAndView(getView()+ConstantsController.EXTERNAL);
		}
				
		return mav;
	}
	
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	
}
