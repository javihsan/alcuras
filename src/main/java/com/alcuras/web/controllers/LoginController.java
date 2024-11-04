package com.alcuras.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value={"/*login*"})
public class LoginController {
	 
	private String view;
	     
	@RequestMapping("")
	private ModelAndView inicioInit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView(getView());
	
		return mav;
	}
	
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	
}
