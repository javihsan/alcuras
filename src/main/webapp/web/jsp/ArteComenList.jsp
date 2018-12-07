<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
List<MensajeDTO> vMensaje = (List<MensajeDTO>)request.getAttribute("vMensaje");
MensajeDTO mensajeBean = null;
%>
                                                             
 
<%@include file="CabeceraArte.jsp"%>

<script language="JavaScript">
<!--

function arteComenNew() {
	document.ArteComenList.action='<%=path%>-<%=ConstantsController.NEW.toLowerCase()%>'
	document.ArteComenList.submit();
}

//-->
</script>

		<div class="textHome art">
          	<div class="imgCaption"><img src="/web/img/comments.jpg" width="230" height="171" class="sectionMainPict" /><p><spring:message code="arte.comentarios.list.imgpie"/></p></div>
	        <spring:message code="arte.comentarios.list.text"/>

     		<!-- <img src="/web/img/titMessages.png" alt="<spring:message code="arte.comentarios"/>" /><strong><spring:message code="arte.comentarios.list"/></strong>-->
     		<form action="ArteComenList" method="post" name="ArteComenList" id="messageForm">
			   <%  if (vMensaje.size()>6){ %> 
			   	<div class="submitButton"><input type="button" id="nuevo" value="<spring:message code="arte.comentarios.new"/>" onClick="arteComenNew()"></div>
	      	  <%  } %>
	      	
     		<ol style="list-style-type:decimal;float:left;">
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
			      	<div class="submitButton"><input type="button" id="nuevo" value="<spring:message code="arte.comentarios.new"/>" onClick="arteComenNew()"></div>
				</form>				
          	
	    </div>

	</div>	

<%@include file="Pie.jsp"%>
