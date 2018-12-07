<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.EventoDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%
EventoDTO eventoBean = (EventoDTO)request.getAttribute("evento");
%>

<%@include file="CabeceraArte.jsp"%>

		<div class="textHome art">
	            <p><strong><%=Utils.getFormatHora(eventoBean.getEveFechaDesde())%> - <%=Utils.getFormatHora(eventoBean.getEveFechaHasta())%> <%=eventoBean.getEveAsunto(RequestContextUtils.getLocale(request).getLanguage())%> <spring:message code="general.by"/> <%=eventoBean.getEveIdUsuHtml()%></strong></p>
	            <% if (eventoBean.getEveImagen()!=null && eventoBean.getEveImagen()!=""){ %><div class="imgCaption"><img src="../../imageServe?blob-key=<%=eventoBean.getEveImagen()%>&x=230&y=171" class="sectionMainPict" /></div><% } %>
	        	<p><%=eventoBean.getEveTextoNoScape(RequestContextUtils.getLocale(request).getLanguage())%></p>	        	
	    </div>

	</div>	

<%@include file="Pie.jsp"%>
