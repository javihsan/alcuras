<%@ page import="com.alcuras.web.controllers.ConstantsController" %>

	<%@include file="CabeceraArte.jsp"%>

<script language="JavaScript">
<!--
function verifierForm() {
	
	if (document.AteContacto.name.value==""){
		alert ('<spring:message code="arte.contacto.form.obligName"/>')
		document.AteContacto.name.focus()
	} else if (document.AteContacto.surname.value==""){
		alert ('<spring:message code="arte.contacto.form.obligSurname"/>')
		document.AteContacto.surname.focus()
	} else if (document.AteContacto.email.value==""){
		alert ('<spring:message code="arte.contacto.form.obligEmail"/>')
		document.AteContacto.email.focus()
	} else if (document.AteContacto.telf.value==""){
		alert ('<spring:message code="arte.contacto.form.obligTelf"/>')
		document.AteContacto.telf.focus()
	} else if (document.AteContacto.comment.value==""){
		alert ('<spring:message code="arte.contacto.form.obligComment"/>')
		document.AteContacto.comment.focus()
	} else if (document.AteContacto.comment.value.length>500){
		alert ('<spring:message code="arte.contacto.form.maxComment"/>')
		document.AteContacto.comment.focus()
	} else {
		document.AteContacto.action='<%=path%>-<%=ConstantsController.SEND.toLowerCase()%>'
		document.AteContacto.submit();
	} 
}

//-->
</script>   	
	        <div class="textHome art">
	           	<p>
	           		<spring:message code="arte.contacto.text1"/>
	           	</p>
	        	
	        	<font class="required"><spring:message code="form.required"/></font>
	        	
      	       	<form action="AteContacto" method="post" name="AteContacto" id="contactForm">
        	    	<div class="titForm"><spring:message code="arte.contacto.form.cabe"/>:</div>
            		<div><label for="name">*<spring:message code="arte.contacto.form.name"/>:</label><input type="text" name="name" id="name" /></div>
                	<div><label for="surname">*<spring:message code="arte.contacto.form.surname"/>:</label><input type="text" name="surname" id="surname" /></div>
	                <div><label for="email">*<spring:message code="form.mail"/>:</label><input type="text" name="email" id="email" /></div>	
	                <div><label for="telf">*<spring:message code="form.telf"/>:</label><input type="text" name="telf" id="telf" /></div>
    	            <div><label for="comment">*<spring:message code="arte.contacto.form.text"/>:</label><textarea name="comment" id="comment"></textarea></div>
			      	<div class="submitButton"><input type="button" value="<spring:message code="form.send"/>" onclick="verifierForm()" /></div>
        	   	</form>
        		
        		<%@include file="Contacto.jsp"%>
        		
	        </div>
	    </div>		
			
	
	<%@include file="Pie.jsp"%>