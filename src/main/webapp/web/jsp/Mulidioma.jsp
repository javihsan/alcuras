<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="java.util.Enumeration"%>
<div class="languageBar">
<% 
   	String paramsPath = "";
   	String param = "";
   	for(Enumeration e = request.getParameterNames();e.hasMoreElements(); ){
   		param = (String)e.nextElement();
  		if (!param.equals("lang")){
			paramsPath = "&"+param+"="+request.getParameter(param);
	   	}
   	}
   	if (RequestContextUtils.getLocale(request).getLanguage().equals("en")){ %>
		<a href="?lang=es<%=paramsPath%>"><img src="/web/img/icoEsp.gif" width="26" height="13" alt="Español" /></a>
		<a href="?lang=de<%=paramsPath%>"><img src="/web/img/icoDe.gif" width="26" height="13" alt="Deustch" /></a>
   	<% } else if (RequestContextUtils.getLocale(request).getLanguage().equals("es")){ %>
		<a href="?lang=en<%=paramsPath%>"><img src="/web/img/icoUK.gif" width="26" height="13" alt="English" /></a>
		<a href="?lang=de<%=paramsPath%>"><img src="/web/img/icoDe.gif" width="26" height="13" alt="Deustch" /></a>
   	<% } else if (RequestContextUtils.getLocale(request).getLanguage().equals("de")){ %>
		<a href="?lang=es<%=paramsPath%>"><img src="/web/img/icoEsp.gif" width="26" height="13" alt="Español" /></a>
		<a href="?lang=en<%=paramsPath%>"><img src="/web/img/icoUK.gif" width="26" height="13" alt="English" /></a>
   	<% } %>
   	
</div>

   	 		
