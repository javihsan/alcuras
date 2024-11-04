<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
List<MensajeDTO> vMensaje = (List<MensajeDTO>)request.getAttribute("vMensaje");
MensajeDTO mensajeBean = null;
%>
                                                             
 
<%@include file="CabeceraSalud.jsp"%>

<script language="JavaScript">
<!--

function SaludComenNew() {
	document.SaludComenList.action='<%=path%>-<%=ConstantsController.NEW.toLowerCase()%>'
	document.SaludComenList.submit();
}

//-->
</script>

		<div class="textHome health">
          	<div class="imgCaption"><img src="/web/img/comments.jpg" width="230" height="171" class="sectionMainPict" /><p><spring:message code="salud.comentarios.list.imgpie"/></p></div>
	        <spring:message code="salud.comentarios.list.text"/>
          	
     		<!-- <img src="/web/img/titMessages.png" alt="<spring:message code="salud.comentarios"/>" /><strong><spring:message code="salud.comentarios.list"/></strong> -->
     		<form action="SaludComenList" method="post" name="SaludComenList" id="messageForm">
			
			 <div class="submitButton"><input type="button" id="nuevo" value="<spring:message code="salud.comentarios.new"/>" onClick="SaludComenNew()"></div>
	     
     		 <ol style="list-style-type:decimal;">
            	<%
					for (int i=0;i<vMensaje.size();i++){
						mensajeBean = (MensajeDTO)vMensaje.get(i);
				%>
            
                <li class="message">
                    <spring:message code="general.escritoel"/> <strong><%=Utils.getFormatHora(mensajeBean.getMenFecha())%></strong> <spring:message code="general.by"/> <strong> <%=mensajeBean.getMenIdUsuHtml()%></strong><br />
                    <%=mensajeBean.getMenAsuntoHtml()%>
                </li>
				<% } %>
             </ol>
			      
		    </form>				
          	
	    </div>

	</div>	

<%@include file="Pie.jsp"%>
