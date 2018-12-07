<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.ArticuloDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<% 
List<ArticuloDTO> vArticulo = (List<ArticuloDTO>)request.getAttribute("vArticulo");
ArticuloDTO articuloBean = null;
%>
                                                             
<%@include file="CabeceraArteAdmin.jsp"%>

<script language="JavaScript">
<!--

function arteComenRemove() {
	if (confirm('¿<spring:message code="arte.articulos.form.confirmdel"/>?')){
		document.ArteArticulosListAdmin.action='<%=path%>-<%=ConstantsController.REMOVE.toLowerCase()%>'
		document.ArteArticulosListAdmin.submit();
	}
}


//-->
</script>
	
	<div class="textHome art">
 		
          	<p><strong><spring:message code="arte.articulos.list"/></strong></p>
          	<form action="ArteArticulosListAdmin" method="post" name="ArteArticulosListAdmin" id="messageForm">
				<div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="arteComenRemove()"></div>
          	<ul class="articlesList">
				<%
					for (int i=0;i<vArticulo.size();i++){
						articuloBean = (ArticuloDTO)vArticulo.get(i);
				%>
				<li class="message">
					 <input type="checkbox" name="<%=ConstantsController.SELECTOR+articuloBean.getArtId()%>" value="0">
				  	<strong> <%if (articuloBean.getArtActivado()==1){%><spring:message code="form.enabled"/><% } else { %><spring:message code="form.disabled"/><% } %> </strong>
					(<%=Utils.getFormat(articuloBean.getArtFecha())%>) <%=articuloBean.getArtAsunto(RequestContextUtils.getLocale(request).getLanguage())%> <spring:message code="general.by"/> <%=articuloBean.getArtIdUsuHtml()%>
	
				</li>
				<% } %>
			</ul>
				<div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="arteComenRemove()"></div>
          	</form>	
	    </div>

	</div>	
	
<%@include file="Pie.jsp"%>
