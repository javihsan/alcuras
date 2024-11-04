package com.alcuras.weblogin.aut;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.appengine.api.users.UserServiceFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class AccountsAuthenticationEntryPoint implements
		AuthenticationEntryPoint {
	
	protected String redirectUrl;
	
	protected final static String ACCEPT_HEADER = "Accept";
	protected final static String ACCEPT_APP_JSON = "application/json";
	
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		String path = ((HttpServletRequest)request).getServletPath();
		String redirectURL = null;
		if (path.toLowerCase().indexOf(AuthenticationFilter.EXTERNAL.toLowerCase())!=-1){
			redirectURL = "/login";
		} else {
			redirectURL = UserServiceFactory.getUserService().createLoginURL(request.getRequestURI());
		}
		response.sendRedirect(redirectURL);
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	
	
}
