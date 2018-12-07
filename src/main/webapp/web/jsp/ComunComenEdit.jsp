<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
MensajeDTO mensajeBean = (MensajeDTO)request.getAttribute("mensaje");
%>

<%@include file="CabeceraComun.jsp"%>

<script type="text/javascript" language="javascript" src="/alcuras/alcuras.nocache.js"></script>
<script language="JavaScript" SRC="/web/util/calendarDateInput.js"></script>

<script language="JavaScript">
<!--
function verifierForm() {

	if (document.ComunComenEdit.menIdUsu.value==""){
		alert ('<spring:message code="comun.comentarios.form.obligUsu"/>')
		document.ComunComenEdit.menIdUsu.focus()
	} else if (document.ComunComenEdit.menAsunto.value==""){
		alert ('<spring:message code="comun.comentarios.form.obligText"/>')
		document.ComunComenEdit.menAsunto.focus()
	} else if (document.ComunComenEdit.menAsunto.value.length>500){
		alert ('<spring:message code="comun.comentarios.form.maxComment"/>')
		document.ComunComenEdit.menAsunto.focus()
	} else {
		document.ComunComenEdit.action='<%=path%>-<%=ConstantsController.NEW.toLowerCase()%>'
		document.ComunComenEdit.submit();
	}
}

//-->
</script>                                                          
		<div class="textHome health">
  			<p>
            	<spring:message code="comun.comentarios.new.text"/>
            </p>
			<form action="ComunComenEdit" method="post" name="ComunComenEdit" id="contactForm">
            	<div class="titForm"> <% if (mensajeBean==null){ %>
						<spring:message code="comun.comentarios.new"/>
				<% } else { %>		     
						<spring:message code="comun.comentarios.edit"/>
				<% }%></div>
            	<div><label for="menIdUsu">*<spring:message code="comun.comentarios.form.usu"/>:</label><input class="formElement" type="text" id="menIdUsu" name="menIdUsu" value="<% if (mensajeBean!=null && mensajeBean.getMenIdUsuHtml()!=null){ %><%= mensajeBean.getMenIdUsuHtml() %><% } %>" /></div>
                <div><label for="menAsunto">*<spring:message code="comun.comentarios.form.comen"/>:</label><textarea class="formElement" id="menAsunto" name="menAsunto"><% if (mensajeBean!=null && mensajeBean.getMenAsuntoHtml()!=null){ %><%= mensajeBean.getMenAsuntoHtml() %><% } %></textarea></div>
                <div class="submitButton"><input type="button" value="<spring:message code="form.cancel"/>" onClick="history.go(-1)" />
                <input type="button" value="<spring:message code="form.accept"/>" onClick="verifierForm()" /></div>
            </form>
          	
	    </div>

	</div>	


<%@include file="Pie.jsp"%>
