<%@include file="CabeceraKeys.jsp"%>

<%
String path = (String)request.getAttribute("path");
%>
<body id="com">
<div id="superContainer">
    <div id="header">
      	
      	<%@include file="CabeceraCentro.jsp"%>  
        
        <div class="rightHeaderContainer">
            <%@include file="Mulidioma.jsp"%>
            <img src="/web/img/comunHeaderPict.jpg" width="155" height="83" class="fotoMain"/>
        </div>
    </div>
    <div id="article">
   	 		
		<div class="titContainer">
	        	<img src="/web/img/<spring:message code="img.titComun"/>" height="35" alt="<spring:message code="comun.home"/>" />
	        </div>
	        <div class="menuInt">
	        	<ul>
				    <li <% if (path.indexOf("home")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-home"><span><img src="/web/img/menuPointComun.png" width="24" height="22" alt="<spring:message code="comun.home"/>" /></span><spring:message code="comun.home"/></a></li>
				    <li <% if (path.indexOf("faqs")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-faqs"><span><img src="/web/img/menuPointFAQs.png" width="24" height="18" alt="<spring:message code="comun.faqs"/>" /></span><spring:message code="comun.faqs"/></a></li>
				    <li <% if (path.indexOf("interesa")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-interesa"><span><img src="/web/img/menuPointInteresa.png" width="24" height="22" alt="<spring:message code="comun.articulos"/>" /></span><spring:message code="comun.interesa"/></a></li>
				    <li <% if (path.indexOf("links")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-links"><span><img src="/web/img/menuPointLinks.png" width="24" height="18" alt="<spring:message code="comun.links"/>" /></span><spring:message code="comun.links"/></a></li>
	            	<li <% if (path.indexOf("galeria")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-galeria"><span><img src="/web/img/menuPointEvents.png" width="24" height="20" alt="<spring:message code="comun.galeria"/>" /></span><spring:message code="comun.galeria"/></a></li>
				    <li <% if (path.indexOf("articulos")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-articulos"><span><img src="/web/img/menuPointArticles.png" width="24" height="22" alt="<spring:message code="comun.articulos"/>" /></span><spring:message code="comun.articulos"/></a></li>
				    <li <% if (path.indexOf("comentarios")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-comentarios"><span><img src="/web/img/menuPointComments.png" width="23" height="20" alt="<spring:message code="comun.comentarios"/>" /></span><spring:message code="comun.comentarios"/></a></li>
				</ul>
	        </div>