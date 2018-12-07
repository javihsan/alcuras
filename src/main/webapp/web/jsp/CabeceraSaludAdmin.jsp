<%@include file="CabeceraKeys.jsp"%>

<%
String path = (String)request.getAttribute("path");
%>
<body id="health">
<div id="superContainer">
    <div id="header">
      	
      	<%@include file="CabeceraCentroAdmin.jsp"%>  
        
        <div class="rightHeaderContainer">
            <%@include file="Mulidioma.jsp"%>
            <img src="/web/img/healthHeaderPict.jpg" width="115" height="83" class="fotoMain"/>
        </div>
    </div>
    <div id="article">
   	 		
		<div class="titContainer">
	        	<img src="/web/img/<spring:message code="img.titHealth"/>" height="35" alt="<spring:message code="salud.home"/>" />
	        </div>
	        <div class="menuInt">
	        	<ul>
				    <li <% if (path.indexOf("articulos")!=-1){ %> class="selected" <%}%>
	            	><a href="salud-articulos-admin"><span><img src="/web/img/menuPointArticles.png" width="24" height="22" alt="<spring:message code="salud.articulos"/>" /></span><spring:message code="salud.articulos"/></a></li>
				    <li <% if (path.indexOf("comentarios")!=-1){ %> class="selected" <%}%>
	            	><a href="salud-comentarios-admin"><span><img src="/web/img/menuPointComments.png" width="23" height="20" alt="<spring:message code="salud.comentarios"/>" /></span><spring:message code="salud.comentarios"/></a></li>
	            	<li <% if (path.indexOf("web")!=-1){ %> class="selected" <%}%>
	            	><a href="vcard-webadmin"><span><img src="/web/img/menuPointContact.png" width="23" height="20" alt="<spring:message code="salud.contacto"/>" /></span><spring:message code="salud.contacto"/></a></li>
				</ul>
	        </div>