<%@include file="CabeceraKeys.jsp"%>

<%
String path = (String)request.getAttribute("path");
%>
<body id="com">
<div id="superContainer">
   <div id="header">
            	
      	<%@include file="CabeceraCentroAdmin.jsp"%>  
        
   
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
					<li <% if (path.indexOf("articulos")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-articulos-admin"><span><img src="/web/img/menuPointArticles.png" width="24" height="22" alt="<spring:message code="comun.articulos"/>" /></span><spring:message code="comun.articulos"/></a></li>
				    <li <% if (path.indexOf("comentarios")!=-1){ %> class="selected" <%}%>
	            	><a href="comun-comentarios-admin"><span><img src="/web/img/menuPointComments.png" width="23" height="20" alt="<spring:message code="comun.comentarios"/>" /></span><spring:message code="comun.comentarios"/></a></li>
				</ul>
	        </div>