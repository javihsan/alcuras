<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.ArticuloDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%
ArticuloDTO articuloBean = (ArticuloDTO)request.getAttribute("articulo");
%>

<%@include file="CabeceraComun.jsp"%>

		<div class="textHome health">
	            <p><strong><%=Utils.getFormat(articuloBean.getArtFecha()) %> <%=articuloBean.getArtAsunto(RequestContextUtils.getLocale(request).getLanguage())%> <spring:message code="general.by"/> <%=articuloBean.getArtIdUsuHtml()%></strong></p>
	            <% if (articuloBean.getArtImagen()!=null && articuloBean.getArtImagen()!=""){ %><div class="imgCaption"><img src="../../imageServe?blob-key=<%=articuloBean.getArtImagen()%>&x=230&y=171" class="sectionMainPict" /></div><% } %>
	        	<p><%=articuloBean.getArtTextoNoScape(RequestContextUtils.getLocale(request).getLanguage())%></p>     	
          	
	    </div>

	</div>	

<%@include file="Pie.jsp"%>
