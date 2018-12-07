<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
List<MensajeDTO> vMensaje = (List<MensajeDTO>)request.getAttribute("vMensaje");
MensajeDTO mensajeBean = null;
%>
                                                             
 
<%@include file="CabeceraComunAdmin.jsp"%>

<script language="JavaScript">
<!--

function ComunComenRemove() {
	if (confirm('¿<spring:message code="comun.comentarios.form.confirmdel"/>?')){
		document.ComunComenListAdmin.action='<%=path%>-<%=ConstantsController.REMOVE.toLowerCase()%>'
		document.ComunComenListAdmin.submit();
	}
}

//-->
</script>
		
		<div class="textHome health">
          	<div class="imgCaption"><img src="/web/img/comments.jpg" width="230" height="171" class="sectionMainPict" /><p><spring:message code="comun.comentarios.list.imgpie"/></p></div>
	        <spring:message code="comun.comentarios.list.text"/>
          	
     		<img src="/web/img/titMessages.png" alt="<spring:message code="comun.comentarios"/>" /><strong><spring:message code="comun.comentarios.list"/></strong>
     		<form action="ComunComenListAdmin" method="post" name="ComunComenListAdmin" id="messageForm">
			   	<div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="ComunComenRemove()"></div>
	      	
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
			     <div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="ComunComenRemove()"></div>
			</form>				
          	
	    </div>

	</div>	
	
<%@include file="Pie.jsp"%>
