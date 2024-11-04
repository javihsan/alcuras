package com.alcuras.weblogin.aut;


import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.GenericFilterBean;

import com.google.appengine.api.users.UserServiceFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends GenericFilterBean {

	private static final Logger LOG = Logger.getLogger(AuthenticationFilter.class.getSimpleName());
	
	protected AuthenticationManager authenticationManager;
	
	protected AppAccessDeniedHandler deniedHandler;
	
	protected SecurityContextRepository securityContextRepository;

	public final static String MANAGER = "manager";
	public final static String ADMIN = "admin";
	public final static String EXTERNAL = "external";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", "x-requested-with");

		String path = ((HttpServletRequest) request).getServletPath();

		if ((path.toLowerCase().indexOf(MANAGER.toLowerCase()) != -1
				|| path.toLowerCase().indexOf(ADMIN.toLowerCase()) != -1
				|| path.toLowerCase().indexOf(EXTERNAL.toLowerCase()) != -1)) {
			String serverName = ((HttpServletRequest) request).getServerName();
			String pathKey = serverName;
			if (path.toLowerCase().indexOf(MANAGER.toLowerCase()) != -1) {
				pathKey += MANAGER;
			} else if (path.toLowerCase().indexOf(ADMIN.toLowerCase()) != -1) {
				pathKey += ADMIN;
			} else if (path.toLowerCase().indexOf(EXTERNAL.toLowerCase()) != -1) {
				pathKey += EXTERNAL;
			}
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			// Si tenemos autorización pero no para esta url, reseteamos el SecurityContextHolder
			if (authentication != null
					&& (authentication.getDetails() == null || !(authentication.getDetails() instanceof String)
							|| ((String) authentication.getDetails()).indexOf(pathKey) == -1)) {
				authentication = null;
				// Setup the security context
				setContextAuthentication(authentication, (HttpServletRequest)request, (HttpServletResponse)response);
			}

			if (authentication == null) {
				Object googleUser = null;
				if (path.toLowerCase().indexOf(EXTERNAL.toLowerCase()) != -1) {
					// User isn't authenticated. Check if there is a Firebase Accounts user
					// idToken comes from the client app (shown above)
					// UserRecord googleUser = null;
					String tokenFront = request.getParameter("token");
					if (tokenFront != null) {
						try {
							if(FirebaseApp.getApps().isEmpty()) {
								FirebaseApp.initializeApp();
							}
							googleUser = FirebaseAuth.getInstance().verifyIdToken(tokenFront);
						} catch (FirebaseAuthException e) {
							LOG.severe("AuthenticationFilter: Token not valid o expired");
							googleUser = null;
						}
					}
				} else {
					// User isn't authenticated. Check if there is a Google Accounts user
					googleUser = UserServiceFactory.getUserService().getCurrentUser();
				}
				if (googleUser != null) {
					// User has returned after authenticating through Gwt. Need to
					// authenticate to Spring Security.
					PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(googleUser,
							null);
					token.setDetails(pathKey);
					try {
						authentication = authenticationManager.authenticate(token);
						// Setup the security context
						setContextAuthentication(authentication, (HttpServletRequest)request, (HttpServletResponse)response);
					} catch (AuthenticationException e) {
						LOG.severe("AuthenticationFilter: User not Authenticated");
						deniedHandler.handle(((HttpServletRequest) request), ((HttpServletResponse) response),
								new AccessDeniedException(""));
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	// Setup the security context
		private void setContextAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(authentication);
			securityContextRepository.saveContext(context, request, response);
			SecurityContextHolder.setContext(context);
			//SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		public void setAuthenticationManager(AuthenticationManager authenticationManager) {
			this.authenticationManager = authenticationManager;
		}

		public void setDeniedHandler(AppAccessDeniedHandler deniedHandler) {
			this.deniedHandler = deniedHandler;
		}

		public void setSecurityContextRepository(SecurityContextRepository securityContextRepository) {
			this.securityContextRepository = securityContextRepository;
		}	
		
	}
