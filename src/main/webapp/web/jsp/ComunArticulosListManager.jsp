<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.ArticuloDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<% 
List<ArticuloDTO> vArticulo = (List<ArticuloDTO>)request.getAttribute("vArticulo");
ArticuloDTO articuloBean = null;
%>
                                                             
<%@include file="CabeceraComunManager.jsp"%>

<script language="JavaScript">
<!--

function ComunArticulosNew() {
	document.ComunArticulosListManager.action='<%=path%>-<%=ConstantsController.NEW.toLowerCase()%>'
	document.ComunArticulosListManager.submit();
}

//-->
</script>
	
	<div class="textHome health">
 		
          	<p><strong><spring:message code="comun.articulos.list"/></strong></p>
          	<form action="ComunArticulosListManager" method="post" name="ComunArticulosListManager" id="messageForm">
				<div class="submitButton"><input type="button" id="nuevo" value="<spring:message code="form.add"/>" onClick="ComunArticulosNew()"></div>
          	<ul class="articlesList">
				<%
					for (int i=0;i<vArticulo.size();i++){
						articuloBean = (ArticuloDTO)vArticulo.get(i);
				%>
				<li class="message">
				 <a href="<%=path%>-<%=ConstantsController.EDIT.toLowerCase()%>?id=<%=articuloBean.getArtId()%>">
				<strong><%if (articuloBean.getArtActivado()==1){%><spring:message code="form.enabled"/><% } else { %><spring:message code="form.disabled"/><% } %></strong>
				(<%=Utils.getFormat(articuloBean.getArtFecha())%>) <%=articuloBean.getArtAsunto(RequestContextUtils.getLocale(request).getLanguage())%> <spring:message code="general.by"/> <%=articuloBean.getArtIdUsuHtml()%>
				</a>
				</li>
				<% } %>
			</ul>
				<div class="submitButton"><input type="button" id="nuevo" value="<spring:message code="form.add"/>" onClick="ComunArticulosNew()"></div>
          	</form>	
	    </div>

	</div>	
	
<%@include file="Pie.jsp"%>
