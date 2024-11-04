<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
List<MensajeDTO> vMensaje = (List<MensajeDTO>)request.getAttribute("vMensaje");
MensajeDTO mensajeBean = null;
%>
                                                             
 
<%@include file="CabeceraSaludAdmin.jsp"%>

<script language="JavaScript">
<!--

function SaludComenRemove() {
	if (confirm('¿<spring:message code="salud.comentarios.form.confirmdel"/>?')){
		document.SaludComenListAdmin.action='<%=path%>-<%=ConstantsController.REMOVE.toLowerCase()%>'
		document.SaludComenListAdmin.submit();
	}
}

//-->
</script>
		
		<div class="textHome health">
          	<div class="imgCaption"><img src="/web/img/comments.jpg" width="230" height="171" class="sectionMainPict" /><p><spring:message code="salud.comentarios.list.imgpie"/></p></div>
	        <spring:message code="salud.comentarios.list.text"/>
          	
     		<!-- <img src="/web/img/titMessages.png" alt="<spring:message code="salud.comentarios"/>" /><strong><spring:message code="salud.comentarios.list"/></strong>-->
     		<form action="SaludComenListAdmin" method="post" name="SaludComenListAdmin" id="messageForm">
			   	<div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="SaludComenRemove()"></div>
	      	
     		<ol style="list-style-type:decimal;">
            	<%
					for (int i=0;i<vMensaje.size();i++){
						mensajeBean = (MensajeDTO)vMensaje.get(i);
				%>

                <li class="message">
                	<input type="checkbox" name="<%=ConstantsController.SELECTOR+mensajeBean.getMenId()%>" value="0">
                    <strong><%if (mensajeBean.getMenActivado()==1){%><spring:message code="form.enabled"/><% } else { %><spring:message code="form.disabled"/><% } %> </strong> 
                    <spring:message code="general.escritoel"/> <strong><%=Utils.getFormatHora(mensajeBean.getMenFecha())%></strong> <spring:message code="general.by"/> <strong> <%=mensajeBean.getMenIdUsuHtml()%></strong><br />
                    <%=mensajeBean.getMenAsuntoHtml()%>
                </li>
         
				<% } %>
   			</ol>
			     <div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="SaludComenRemove()"></div>
			</form>				
          	
	    </div>

	</div>	
	
<%@include file="Pie.jsp"%>
