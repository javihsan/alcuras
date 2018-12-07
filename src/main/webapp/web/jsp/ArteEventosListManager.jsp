<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.EventoDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<% 
List<EventoDTO> vEvento= (List<EventoDTO>)request.getAttribute("vEvento");
EventoDTO eventoBean = null;
%>
                                                             
<%@include file="CabeceraArteManager.jsp"%>

<script language="JavaScript">
<!--

function arteEventosNew() {
	document.ArteEventosListManager.action='<%=path%>-<%=ConstantsController.NEW.toLowerCase()%>'
	document.ArteEventosListManager.submit();
}

//-->
</script>
	
	<div class="textHome art">
 		
          	<p><strong><spring:message code="arte.eventos.list"/></strong></p>
          	<form action="ArteEventosListManager" method="post" name="ArteEventosListManager" id="messageForm">
				<div class="submitButton"><input type="button" id="nuevo" value="<spring:message code="form.add"/>" onClick="arteEventosNew()"></div>
          	<ul class="articlesList">
				<%
					for (int i=0;i<vEvento.size();i++){
						eventoBean = (EventoDTO)vEvento.get(i);
				%>
				<li class="message">
				<a href="<%=path%>-<%=ConstantsController.EDIT.toLowerCase()%>?id=<%=eventoBean.getEveId()%>">
				<strong><%if (eventoBean.getEveActivado()==1){%><spring:message code="form.enabled"/><% } else { %><spring:message code="form.disabled"/><% } %></strong>
				(<%=Utils.getFormatHora(eventoBean.getEveFechaDesde())%> - <%=Utils.getFormatHora(eventoBean.getEveFechaHasta())%>) <%=eventoBean.getEveAsunto(RequestContextUtils.getLocale(request).getLanguage())%> <spring:message code="general.by"/> <%=eventoBean.getEveIdUsuHtml()%>
				</a>
				</li>
				<% } %>
			</ul>
				<div class="submitButton"><input type="button" id="nuevo" value="<spring:message code="form.add"/>" onClick="arteEventosNew()"></div>
          	</form>	
	    </div>

	</div>	
	
<%@include file="Pie.jsp"%>
