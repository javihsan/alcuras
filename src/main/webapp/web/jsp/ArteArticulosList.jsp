<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.ArticuloDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
List<ArticuloDTO> vArticulo = (List<ArticuloDTO>)request.getAttribute("vArticulo");
ArticuloDTO articuloBean = null;
%>

<%@include file="CabeceraArte.jsp"%>

<script language="JavaScript">
<!--

function abrirVentana(ruta){
	window.open(ruta,'','scrollbars=yes,width=550,height=450')
}

//-->
</script>                                                              

 		<div class="textHome art">
 		
          	<p><strong><spring:message code="arte.articulos.list"/></strong></p>
          	<ul class="articlesList">
				<%
					for (int i=0;i<vArticulo.size();i++){
						articuloBean = (ArticuloDTO)vArticulo.get(i);
				%>
				<li class="message">
				  <% if (articuloBean.getArtTipoLink()==1){ %>
				      	<a href="../../fileServe?objectId=<%=articuloBean.getArtFichero()%>&fileName=<%=articuloBean.getArtFicheroName()%>">
				      <% } else if (articuloBean.getArtTipoLink()==2){ %>
				      	<a target="_blank" href="<%=articuloBean.getArtLink()%>">
				      <% } else if (articuloBean.getArtTipoLink()==3){ %>
				      	<a href="<%=path%>-<%=ConstantsController.VIEW.toLowerCase()%>?id=<%=articuloBean.getArtId()%>">
				      <% } %>
				(<%=Utils.getFormat(articuloBean.getArtFecha())%>) <%=articuloBean.getArtAsunto(RequestContextUtils.getLocale(request).getLanguage())%> <spring:message code="general.by"/> <%=articuloBean.getArtIdUsuHtml()%>
				</a>
				</li>
				<% } %>
			</ul>
          	
	    </div>

	</div>	

<%@include file="Pie.jsp"%>
