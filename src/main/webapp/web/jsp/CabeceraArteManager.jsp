<%@include file="CabeceraKeys.jsp"%>

<%
String path = (String)request.getAttribute("path");
%>
<body id="art">
<div id="superContainer">
    <div id="header">
            	
      	<%@include file="CabeceraCentroManager.jsp"%>  
        

        <div class="rightHeaderContainer">
            <%@include file="Mulidioma.jsp"%>
            <img src="/web/img/artHeaderPict.jpg" width="115" height="83" class="fotoMain"/>
        </div>
    </div>
    <div id="article">
   	 		
		<div class="titContainer">
	        	<img src="/web/img/<spring:message code="img.titArt"/>" height="35" alt="<spring:message code="arte.home"/>" />
	        </div>
	        <div class="menuInt">
	        	<ul>
				    <li <% if (path.indexOf("eventos")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-eventos-manager"><span><img src="/web/img/menuPointEvents.png" width="24" height="18" alt="<spring:message code="arte.eventos"/>" /></span><spring:message code="arte.eventos"/></a></li>
				    <li <% if (path.indexOf("articulos")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-articulos-manager"><span><img src="/web/img/menuPointArticles.png" width="24" height="22" alt="<spring:message code="arte.articulos"/>" /></span><spring:message code="arte.articulos"/></a></li>
				    <li <% if (path.indexOf("comentarios")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-comentarios-manager"><span><img src="/web/img/menuPointComments.png" width="23" height="20" alt="<spring:message code="arte.comentarios"/>" /></span><spring:message code="arte.comentarios"/></a></li>
				</ul>
	        </div>