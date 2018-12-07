package com.alcuras.weblogin.aut;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthenticationFilter extends GenericFilterBean {

	protected AuthenticationManager authenticationManager;
	protected AppAccessDeniedHandler deniedHandler;

	private final static String	MANAGER = "manager";
	private final static String ADMIN = "admin";
	private final static String CONTINUE = "continue";
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		((HttpServletResponse)response).setHeader("Access-Control-Max-Age", "3600");
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "x-requested-with");
		
		String path = ((HttpServletRequest)request).getServletPath();
				
		if (path.indexOf(CONTINUE)==-1 && (
					path.toLowerCase().indexOf(MANAGER.toLowerCase())!=-1||
					path.toLowerCase().indexOf(ADMIN.toLowerCase())!=-1
				)				
	    ){
			String serverName = ((HttpServletRequest)request).getServerName();
			String pathKey = serverName;
			if (path.toLowerCase().indexOf(MANAGER.toLowerCase())!=-1){
				pathKey += MANAGER;
			} else if (path.toLowerCase().indexOf(ADMIN.toLowerCase())!=-1){
				pathKey += ADMIN;
			} 					
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null && ((String)authentication.getDetails()).indexOf(pathKey)==-1) { // Si tenemos autorización pero no para esta url, reseteamos el SecurityContextHolder
				authentication = null;
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
			if (authentication == null) {
				// User isn't authenticated. Check if there is a Google Accounts user
				User googleUser = UserServiceFactory.getUserService().getCurrentUser();
	
				if (googleUser != null) {
					// User has returned after authenticating through Gwt. Need to
					// authenticate to Spring Security.
					PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(googleUser, null);
					token.setDetails(pathKey);
					try {
						authentication = authenticationManager.authenticate(token);
						// Setup the security context
						SecurityContextHolder.getContext().setAuthentication(authentication);
	
					} catch (AuthenticationException e) {
						deniedHandler.handle(((HttpServletRequest)request), ((HttpServletResponse)response), new AccessDeniedException(""));
					}
				}	
			} 
		}	
		chain.doFilter(request, response);
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public AppAccessDeniedHandler getDeniedHandler() {
		return deniedHandler;
	}

	public void setDeniedHandler(AppAccessDeniedHandler deniedHandler) {
		this.deniedHandler = deniedHandler;
	}

	
}
