package com.alcuras.weblogin.aut;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.google.appengine.api.users.UserServiceFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AppAccessDeniedHandler implements AccessDeniedHandler {
	private String accessDeniedUrl;

	public AppAccessDeniedHandler() {
	}

	public AppAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {

		SecurityContextHolder.getContext().setAuthentication(null);
		
		String redirectURL = UserServiceFactory.getUserService().createLogoutURL(request.getRequestURI());
		response.sendRedirect(redirectURL);
	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
}
