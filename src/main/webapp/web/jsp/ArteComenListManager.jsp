<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<% 
List<MensajeDTO> vMensaje = (List<MensajeDTO>)request.getAttribute("vMensaje");
MensajeDTO mensajeBean = null;
%>
                                                             
 
<%@include file="CabeceraArteManager.jsp"%>

<script language="JavaScript">
<!--

function arteComenDisable() {
	if (confirm('¿<spring:message code="arte.comentarios.form.confirmdis"/>?')){
		document.ArteComenListManager.action='<%=path%>-<%=ConstantsController.UPDATE.toLowerCase()%>'
		document.ArteComenListManager.submit();
	}
}

//-->
</script>
		<div class="textHome art">
          	<div class="imgCaption"><img src="/web/img/comments.jpg" width="230" height="171" class="sectionMainPict" /><p><spring:message code="arte.comentarios.list.imgpie"/></p></div>
	        <spring:message code="arte.comentarios.list.text"/>
          	
     		<img src="/web/img/titMessages.png" alt="<spring:message code="arte.comentarios"/>" /><strong><spring:message code="arte.comentarios.list"/></strong>
     		<form action="ArteComenListManager" method="post" name="ArteComenListManager" id="messageForm">
			   	<div class="submitButton"><input type="button" id="aceptar" value="<spring:message code="form.enable_disable"/>" onClick="arteComenDisable()"></div>
	      	
     		<ol style="list-style-type:decimal;">
            	<%
					for (int i=0;i<vMensaje.size();i++){
						mensajeBean = (MensajeDTO)vMensaje.get(i);
				%>

                <li class="message">
                	<input type="checkbox" name="menActivado<%=mensajeBean.getMenId()%>" value="<% if (mensajeBean!=null && mensajeBean.getMenActivado()==1){ %>1" checked<% } else { %>" <% } %>>
				    <input type="hidden" name="idMensaje<%=mensajeBean.getMenId()%>" value="<%=mensajeBean.getMenId()%>"/>
                    <spring:message code="general.escritoel"/> <strong><%=Utils.getFormatHora(mensajeBean.getMenFecha())%></strong> <spring:message code="general.by"/> <strong> <%=mensajeBean.getMenIdUsuHtml()%></strong><br />
                    <%=mensajeBean.getMenAsuntoHtml()%>
                </li>
         
				<% } %>
   			</ol>
			     <div class="submitButton"><input type="button" id="aceptar" value="<spring:message code="form.enable_disable"/>" onClick="arteComenDisable()"></div>
			</form>				
          	
	    </div>


	</div>	
	
<%@include file="Pie.jsp"%>
