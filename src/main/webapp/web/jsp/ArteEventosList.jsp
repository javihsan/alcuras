<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.EventoDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
List<EventoDTO> vEvento= (List<EventoDTO>)request.getAttribute("vEvento");
EventoDTO eventoBean = null;
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
 		
          	<p><strong><spring:message code="arte.eventos.list"/></strong></p>
          	<ul class="articlesList">
				<%
					for (int i=0;i<vEvento.size();i++){
						eventoBean = (EventoDTO)vEvento.get(i);
				%>
				<li class="message">
				  <% if (eventoBean.getEveTipoLink()==1){ %>
				      	<a href="../../fileServe?blob-key=<%=eventoBean.getEveFichero()%>">
				      <% } else if (eventoBean.getEveTipoLink()==2){ %>
				      	<a target="_blank" href="<%=eventoBean.getEveLink()%>">
				      <% } else if (eventoBean.getEveTipoLink()==3){ %>
				      	<a href="<%=path%>-<%=ConstantsController.VIEW.toLowerCase()%>?id=<%=eventoBean.getEveId()%>">
				      <% } %>
				(<%=Utils.getFormatHora(eventoBean.getEveFechaDesde())%> - <%=Utils.getFormatHora(eventoBean.getEveFechaHasta())%>) <%=eventoBean.getEveAsunto(RequestContextUtils.getLocale(request).getLanguage())%>
				</a>
				</li>
				<% } %>
			</ul>
          	
	    </div>

	</div>	

<%@include file="Pie.jsp"%>
