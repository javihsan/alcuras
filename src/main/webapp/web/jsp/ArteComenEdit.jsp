<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.MensajeDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
<%
MensajeDTO mensajeBean = (MensajeDTO)request.getAttribute("mensaje");
%>

<%@include file="CabeceraArte.jsp"%>

<script type="text/javascript" language="javascript" src="/alcuras/alcuras.nocache.js"></script>
<script language="JavaScript" SRC="/web/util/calendarDateInput.js"></script>

<script language="JavaScript">
<!--
function verifierForm() {

	if (document.ArteComenEdit.menIdUsu.value==""){
		alert ('<spring:message code="arte.comentarios.form.obligUsu"/>')
		document.ArteComenEdit.menIdUsu.focus()
	} else if (document.ArteComenEdit.menAsunto.value==""){
		alert ('<spring:message code="arte.comentarios.form.obligText"/>')
		document.ArteComenEdit.menAsunto.focus()
	} else if (document.ArteComenEdit.menAsunto.value.length>500){
		alert ('<spring:message code="arte.comentarios.form.maxComment"/>')
		document.ArteComenEdit.menAsunto.focus()
	} else {
		document.ArteComenEdit.action='<%=path%>-<%=ConstantsController.NEW.toLowerCase()%>'
		document.ArteComenEdit.submit();
	}
}

//-->
</script>                                                          
		<div class="textHome art">
  			<p>
            	<spring:message code="arte.comentarios.new.text"/>
            </p>
			<form action="ArteComenEdit" method="post" name="ArteComenEdit" id="contactForm">
            	<div class="titForm"> <% if (mensajeBean==null){ %>
						<spring:message code="arte.comentarios.new"/>
				<% } else { %>		     
						<spring:message code="arte.comentarios.edit"/>
				<% }%></div>
            	<div><label for="menIdUsu">*<spring:message code="arte.comentarios.form.usu"/>:</label><input class="formElement" type="text" id="menIdUsu" name="menIdUsu" value="<% if (mensajeBean!=null && mensajeBean.getMenIdUsuHtml()!=null){ %><%= mensajeBean.getMenIdUsuHtml() %><% } %>" /></div>
                <div><label for="menAsunto">*<spring:message code="arte.comentarios.form.comen"/>:</label><textarea class="formElement" id="menAsunto" name="menAsunto"><% if (mensajeBean!=null && mensajeBean.getMenAsuntoHtml()!=null){ %><%= mensajeBean.getMenAsuntoHtml() %><% } %></textarea></div>
                <div class="submitButton"><input type="button" value="<spring:message code="form.cancel"/>" onClick="history.go(-1)" />
                <input type="button" value="<spring:message code="form.accept"/>" onClick="verifierForm()" /></div>
            </form>
          	
	    </div>

	</div>	


<%@include file="Pie.jsp"%>
