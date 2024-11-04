<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
List<MensajeDTO> vMensaje = (List<MensajeDTO>)request.getAttribute("vMensaje");
MensajeDTO mensajeBean = null;
%>
                                                             
 
<%@include file="CabeceraComun.jsp"%>

<script language="JavaScript">
<!--

function ComunComenNew() {
	document.ComunComenList.action='<%=path%>-<%=ConstantsController.NEW.toLowerCase()%>'
	document.ComunComenList.submit();
}

//-->
</script>

		<div class="textHome health">
          	<div class="imgCaption"><img src="/web/img/comments.jpg" width="230" height="171" class="sectionMainPict" /><p><spring:message code="comun.comentarios.list.imgpie"/></p></div>
	        	<spring:message code="comun.comentarios.list.text"/>
          	
     		<!-- <img src="/web/img/titMessages.png" alt="<spring:message code="comun.comentarios"/>" /><strong><spring:message code="comun.comentarios.list"/></strong>-->
     		<form action="ComunComenList" method="post" name="ComunComenList" id="messageForm">
			   
			 <div class="submitButton"><input type="button" id="nuevo" value="<spring:message code="comun.comentarios.new"/>" onClick="ComunComenNew()"></div>
	   
	      	
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
