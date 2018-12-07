<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.EventoDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<% 
List<EventoDTO> vEvento= (List<EventoDTO>)request.getAttribute("vEvento");
EventoDTO eventoBean = null;
%>
                                                             
<%@include file="CabeceraArteAdmin.jsp"%>

<script language="JavaScript">
<!--

function arteComenRemove() {
	if (confirm('¿<spring:message code="arte.eventos.form.confirmdel"/>?')){
		document.ArteEventosListAdmin.action='<%=path%>-<%=ConstantsController.REMOVE.toLowerCase()%>'
		document.ArteEventosListAdmin.submit();
	}
}


//-->
</script>
	
	<div class="textHome art">
 		
          	<p><strong><spring:message code="arte.eventos.list"/></strong></p>
          	<form action="ArteEventosListAdmin" method="post" name="ArteEventosListAdmin" id="messageForm">
				<div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="arteComenRemove()"></div>
          	<ul class="articlesList">
				<%
					for (int i=0;i<vEvento.size();i++){
						eventoBean = (EventoDTO)vEvento.get(i);
				%>
				<li class="message">
					 <input type="checkbox" name="<%=ConstantsController.SELECTOR+eventoBean.getEveId()%>" value="0">
					 <strong><%if (eventoBean.getEveActivado()==1){%><spring:message code="form.enabled"/><% } else { %><spring:message code="form.disabled"/><% } %> </strong>
				(<%=Utils.getFormatHora(eventoBean.getEveFechaDesde())%> - <%=Utils.getFormatHora(eventoBean.getEveFechaHasta())%>) <%=eventoBean.getEveAsunto(RequestContextUtils.getLocale(request).getLanguage())%> <spring:message code="general.by"/> <%=eventoBean.getEveIdUsuHtml()%>

				</li>
				<% } %>
			</ul>
				<div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="arteComenRemove()"></div>
          	</form>	
	    </div>

	</div>	
	
<%@include file="Pie.jsp"%>
