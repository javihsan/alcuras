<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
List<MensajeDTO> vMensaje = (List<MensajeDTO>)request.getAttribute("vMensaje");
MensajeDTO mensajeBean = null;
%>
                                                             
 
<%@include file="CabeceraArteAdmin.jsp"%>

<script language="JavaScript">
<!--

function arteComenRemove() {
	if (confirm('¿<spring:message code="arte.comentarios.form.confirmdel"/>?')){
		document.ArteComenListAdmin.action='<%=path%>-<%=ConstantsController.REMOVE.toLowerCase()%>'
		document.ArteComenListAdmin.submit();
	}
}

//-->
</script>
		
		<div class="textHome art">
          	<div class="imgCaption"><img src="/web/img/comments.jpg" width="230" height="171" class="sectionMainPict" /><p><spring:message code="arte.comentarios.list.imgpie"/></p></div>
	        <spring:message code="arte.comentarios.list.text"/>
          	
     		<img src="/web/img/titMessages.png" alt="<spring:message code="arte.comentarios"/>" /><strong><spring:message code="arte.comentarios.list"/></strong>
     		<form action="ArteComenListAdmin" method="post" name="ArteComenListAdmin" id="messageForm">
			   	<div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="arteComenRemove()"></div>
	      	
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
			     <div class="submitButton"><input type="button" id="eliminar" value="<spring:message code="form.delete"/>" onClick="arteComenRemove()"></div>
			</form>				
          	
	    </div>

	</div>	
	
<%@include file="Pie.jsp"%>
