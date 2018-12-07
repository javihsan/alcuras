<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
MensajeDTO mensajeBean = (MensajeDTO)request.getAttribute("mensaje");
%>

<%@include file="CabeceraSalud.jsp"%>

<script type="text/javascript" language="javascript" src="/alcuras/alcuras.nocache.js"></script>
<script language="JavaScript" SRC="/web/util/calendarDateInput.js"></script>

<script language="JavaScript">
<!--
function verifierForm() {

	if (document.SaludComenEdit.menIdUsu.value==""){
		alert ('<spring:message code="salud.comentarios.form.obligUsu"/>')
		document.SaludComenEdit.menIdUsu.focus()
	} else if (document.SaludComenEdit.menAsunto.value==""){
		alert ('<spring:message code="salud.comentarios.form.obligText"/>')
		document.SaludComenEdit.menAsunto.focus()
	} else if (document.SaludComenEdit.menAsunto.value.length>500){
		alert ('<spring:message code="salud.comentarios.form.maxComment"/>')
		document.SaludComenEdit.menAsunto.focus()
	} else {
		document.SaludComenEdit.action='<%=path%>-<%=ConstantsController.NEW.toLowerCase()%>'
		document.SaludComenEdit.submit();
	}
}

//-->
</script>                                                          
		<div class="textHome health">
  			<p>
            	<spring:message code="salud.comentarios.new.text"/>
            </p>
			<form action="SaludComenEdit" method="post" name="SaludComenEdit" id="contactForm">
            	<div class="titForm"> <% if (mensajeBean==null){ %>
						<spring:message code="salud.comentarios.new"/>
				<% } else { %>		     
						<spring:message code="salud.comentarios.edit"/>
				<% }%></div>
            	<div><label for="menIdUsu">*<spring:message code="salud.comentarios.form.usu"/>:</label><input class="formElement" type="text" id="menIdUsu" name="menIdUsu" value="<% if (mensajeBean!=null && mensajeBean.getMenIdUsuHtml()!=null){ %><%= mensajeBean.getMenIdUsuHtml() %><% } %>" /></div>
                <div><label for="menAsunto">*<spring:message code="salud.comentarios.form.comen"/>:</label><textarea class="formElement" id="menAsunto" name="menAsunto"><% if (mensajeBean!=null && mensajeBean.getMenAsuntoHtml()!=null){ %><%= mensajeBean.getMenAsuntoHtml() %><% } %></textarea></div>
                <div class="submitButton"><input type="button" value="<spring:message code="form.cancel"/>" onClick="history.go(-1)" />
                <input type="button" value="<spring:message code="form.accept"/>" onClick="verifierForm()" /></div>
            </form>
          	
	    </div>

	</div>	


<%@include file="Pie.jsp"%>
