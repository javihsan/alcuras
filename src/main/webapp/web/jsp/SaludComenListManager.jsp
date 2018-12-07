<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<% 
List<MensajeDTO> vMensaje = (List<MensajeDTO>)request.getAttribute("vMensaje");
MensajeDTO mensajeBean = null;
%>
                                                             
 
<%@include file="CabeceraSaludManager.jsp"%>

<script language="JavaScript">
<!--

function SaludComenDisable() {
	if (confirm('¿<spring:message code="salud.comentarios.form.confirmdis"/>?')){
		document.SaludComenListManager.action='<%=path%>-<%=ConstantsController.UPDATE.toLowerCase()%>'
		document.SaludComenListManager.submit();
	}
}

//-->
</script>
		<div class="textHome health">
          	<div class="imgCaption"><img src="/web/img/comments.jpg" width="230" height="171" class="sectionMainPict" /><p><spring:message code="salud.comentarios.list.imgpie"/></p></div>
	        <spring:message code="salud.comentarios.list.text"/>
          	
     		<img src="/web/img/titMessages.png" alt="<spring:message code="salud.comentarios"/>" /><strong><spring:message code="salud.comentarios.list"/></strong>
     		<form action="SaludComenListManager" method="post" name="SaludComenListManager" id="messageForm">
			   	<div class="submitButton"><input type="button" id="aceptar" value="<spring:message code="form.enable_disable"/>" onClick="SaludComenDisable()"></div>
	      	
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
			     <div class="submitButton"><input type="button" id="aceptar" value="<spring:message code="form.enable_disable"/>" onClick="SaludComenDisable()"></div>
			</form>				
          	
	    </div>


	</div>	
	
<%@include file="Pie.jsp"%>
