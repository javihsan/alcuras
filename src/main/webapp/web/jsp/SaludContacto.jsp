<%@ page import="com.alcuras.web.controllers.ConstantsController" %>

	<%@include file="CabeceraSalud.jsp"%>

<script language="JavaScript">
<!--
function verifierForm() {
	
	if (document.SaludContacto.name.value==""){
		alert ('<spring:message code="salud.contacto.form.obligName"/>')
		document.SaludContacto.name.focus()
	} else if (document.SaludContacto.surname.value==""){
		alert ('<spring:message code="salud.contacto.form.obligSurname"/>')
		document.SaludContacto.surname.focus()
	} else if (document.SaludContacto.email.value==""){
		alert ('<spring:message code="salud.contacto.form.obligEmail"/>')
		document.SaludContacto.email.focus()
	}  else if (document.SaludContacto.telf.value==""){
		alert ('<spring:message code="salud.contacto.form.obligTelf"/>')
		document.SaludContacto.telf.focus()
	} else if (document.SaludContacto.comment.value==""){
		alert ('<spring:message code="salud.contacto.form.obligComment"/>')
		document.SaludContacto.comment.focus()
	} else if (document.SaludContacto.comment.value.length>500){
		alert ('<spring:message code="salud.contacto.form.maxComment"/>')
		document.SaludContacto.comment.focus()
	} else {
		document.SaludContacto.action='<%=path%>-<%=ConstantsController.SEND.toLowerCase()%>'
		document.SaludContacto.submit();
	} 
}

//-->
</script>   	
	        <div class="textHome health">
	           	<p>
	           		<spring:message code="salud.contacto.text1"/>
	           	</p>
	        
	        	<font class="required"><spring:message code="form.required"/></font>
	        
      	       	<form action="SaludContacto" method="post" name="SaludContacto" id="contactForm">
        	    	<div class="titForm"><spring:message code="salud.contacto.form.cabe"/>:</div>
            		<div><label for="name">*<spring:message code="salud.contacto.form.name"/>:</label><input type="text" name="name" id="name" /></div>
                	<div><label for="surname">*<spring:message code="salud.contacto.form.surname"/>:</label><input type="text" name="surname" id="surname" /></div>
	                <div><label for="email">*<spring:message code="form.mail"/>:</label><input type="text" name="email" id="email" /></div>	
	                <div><label for="telf">*<spring:message code="form.telf"/>:</label><input type="text" name="telf" id="telf" /></div>
    	            <div><label for="comment">*<spring:message code="salud.contacto.form.text"/>:</label><textarea name="comment" id="comment"></textarea></div>
			      	<div class="submitButton"><input type="button" value="<spring:message code="form.send"/>" onclick="verifierForm()" /></div>
        	   	</form>
        		        		
        		<%@include file="Contacto.jsp"%>
        		
	        </div>
	    </div>		
			
	
	<%@include file="Pie.jsp"%>