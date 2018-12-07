<%@ page import="com.alcuras.web.controllers.ConstantsController" %>
	<%@include file="CabeceraComun.jsp"%>

<script language="JavaScript">
<!--
function verifierForm() {
	
	if (document.ComunInteresa.name.value==""){
		alert ('<spring:message code="comun.interesa.form.obligName"/>')
		document.ComunInteresa.name.focus()
	} else if (document.ComunInteresa.surname.value==""){
		alert ('<spring:message code="comun.interesa.form.obligSurname"/>')
		document.ComunInteresa.surname.focus()
	} else if (document.ComunInteresa.email.value==""){
		alert ('<spring:message code="comun.interesa.form.obligEmail"/>')
		document.ComunInteresa.email.focus()
	} else if (document.ComunInteresa.telf.value==""){
		alert ('<spring:message code="comun.interesa.form.obligTelf"/>')
		document.ComunInteresa.telf.focus()
	} else if (document.ComunInteresa.comment.value==""){
		alert ('<spring:message code="comun.interesa.form.obligComment"/>')
		document.ComunInteresa.comment.focus()
	} else if (document.ComunInteresa.comment.value.length>500){
		alert ('<spring:message code="comun.interesa.form.maxComment"/>')
		document.ComunInteresa.comment.focus()
	} else {
		document.ComunInteresa.action='<%=path%>-<%=ConstantsController.SEND.toLowerCase()%>'
		document.ComunInteresa.submit();
	}
}

//-->
</script>                                                          


	        <div class="textHome health">

	        	 <!--<spring:message code="comun.interesa.text"/>-->
	        	 
	        	 <font class="required"><spring:message code="form.required"/></font>

	        	 <form action="ComunInteresa" method="post" name="ComunInteresa" id="contactForm">
        	    	<div class="titForm"><spring:message code="comun.interesa.form.cabe"/>:</div>
            		<div><label for="name">*<spring:message code="comun.interesa.form.name"/>:</label><input type="text" name="name" id="name" /></div>
                	<div><label for="surname">*<spring:message code="comun.interesa.form.surname"/>:</label><input type="text" name="surname" id="surname" /></div>
	                <div><label for="email">*<spring:message code="form.mail"/>:</label><input type="text" name="email" id="email" /></div>	
	                <div><label for="telf">*<spring:message code="form.telf"/>:</label><input type="text" name="telf" id="telf" /></div>
    	            <div><label for="comment">*<spring:message code="comun.interesa.form.text"/>:</label><textarea name="comment" id="comment"></textarea></div>
			      	<div class="submitButton"><input type="button" value="<spring:message code="form.send"/>" onclick="verifierForm()" /></div>
			      	<br><br>
        	    </form>
        	    
        	    <%@include file="Contacto.jsp"%>
        	    
	        </div>
	    </div>		
	
	<%@include file="Pie.jsp"%>