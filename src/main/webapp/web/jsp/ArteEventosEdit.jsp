<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.EventoDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>

<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%
String urlRedirect = (String)request.getAttribute("urlRedirect");
EventoDTO eventoBean = (EventoDTO)request.getAttribute("evento");
String uploadURL = "/fileUpload";
%>

<%@include file="CabeceraArteManager.jsp"%>

<script type="text/javascript" language="javascript" src="/alcuras/alcuras.nocache.js"></script>
<script language="JavaScript" SRC="/web/util/calendarDateInput.js"></script>

<script language="JavaScript">
<!--

window.onload = changeTipoLink;

function changeTipoLink() {
	if (document.ArteEventoEdit.eveTipoLink[0].checked){

		document.ArteEventoEdit.eveFichero.disabled=false	
		
		document.ArteEventoEdit.eveLink.disabled=true

		document.ArteEventoEdit.eveTextoEs.disabled=true
		document.ArteEventoEdit.eveTextoEn.disabled=true
		document.ArteEventoEdit.eveTextoDe.disabled=true

		document.ArteEventoEdit.eveImagen.disabled=true	

	} else if (document.ArteEventoEdit.eveTipoLink[1].checked){

		document.ArteEventoEdit.eveFichero.disabled=true	
 

		document.ArteEventoEdit.eveLink.disabled=false
			
		document.ArteEventoEdit.eveTextoEs.disabled=true
		document.ArteEventoEdit.eveTextoEn.disabled=true
		document.ArteEventoEdit.eveTextoDe.disabled=true

		document.ArteEventoEdit.eveImagen.disabled=true	

	} else if (document.ArteEventoEdit.eveTipoLink[2].checked){

		document.ArteEventoEdit.eveFichero.disabled=true	

		document.ArteEventoEdit.eveLink.disabled=true

		document.ArteEventoEdit.eveTextoEs.disabled=false
		document.ArteEventoEdit.eveTextoEn.disabled=false
		document.ArteEventoEdit.eveTextoDe.disabled=false
		document.ArteEventoEdit.eveImagen.disabled=false
	
			
	}
}

function verifierForm() {
	
	if (document.ArteEventoEdit.eveAsuntoEs.value==""){
		alert ('<spring:message code="arte.eventos.form.obligAsuntoEs"/>')
		document.ArteEventoEdit.eveAsuntoEs.focus()
	} else if (document.ArteEventoEdit.eveAsuntoEn.value==""){
		alert ('<spring:message code="arte.eventos.form.obligAsuntoEn"/>')
		document.ArteEventoEdit.eveAsuntoEn.focus()
	} else if (document.ArteEventoEdit.eveAsuntoDe.value==""){
		alert ('<spring:message code="arte.eventos.form.obligAsuntoDe"/>')
		document.ArteEventoEdit.eveAsuntoDe.focus()
	} else if (document.ArteEventoEdit.eveIdUsu.value==""){
		alert ('<spring:message code="arte.eventos.form.obligUsu"/>')
		document.ArteEventoEdit.eveIdUsu.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[0].checked && document.ArteEventoEdit.eveFicheroHidden.value==""){
		alert ('<spring:message code="arte.eventos.form.obligFile"/>')
		document.ArteEventoEdit.eveFichero.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[0].checked && document.ArteEventoEdit.eveFicheroHidden.value.indexOf(".pdf")==-1 && document.ArteEventoEdit.eveFicheroHidden.value.indexOf(".doc")==-1){
		alert ('<spring:message code="arte.eventos.form.obligFileType"/>')
		document.ArteEventoEdit.eveFichero.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[1].checked && document.ArteEventoEdit.eveLink.value==""){
		alert ('<spring:message code="arte.eventos.form.obligLink"/>')
		document.ArteEventoEdit.eveLink.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[2].checked && document.ArteEventoEdit.eveTextoEs.value==""){
		alert ('<spring:message code="arte.eventos.form.obligTextEs"/>')
		document.ArteEventoEdit.eveTextoEs.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[2].checked && document.ArteEventoEdit.eveTextoEs.value.length>3000){
		alert ('<spring:message code="arte.eventos.form.maxTextEs"/>')
		document.ArteEventoEdit.eveTextoEs.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[2].checked && document.ArteEventoEdit.eveTextoEn.value==""){
		alert ('<spring:message code="arte.eventos.form.obligTextEn"/>')
		document.ArteEventoEdit.eveTextoEn.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[2].checked && document.ArteEventoEdit.eveTextoEn.value.length>3000){
		alert ('<spring:message code="arte.eventos.form.maxTextEn"/>')
		document.ArteEventoEdit.eveTextoEn.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[2].checked && document.ArteEventoEdit.eveTextoDe.value==""){
		alert ('<spring:message code="arte.eventos.form.obligTextDe"/>')
		document.ArteEventoEdit.eveTextoDe.focus()
	} else if (document.ArteEventoEdit.eveTipoLink[2].checked && document.ArteEventoEdit.eveTextoDe.value.length>3000){
		alert ('<spring:message code="arte.eventos.form.maxTextDe"/>')
		document.ArteEventoEdit.eveTextoDe.focus()
	} /*else if (document.ArteEventoEdit.eveTipoLink[2].checked && document.ArteEventoEdit.eveImagenHidden.value==""){
		alert ('<spring:message code="arte.eventos.form.obligImagen"/>')
		document.ArteEventoEdit.eveImagen.focus()
	}  */else {
		if (document.ArteEventoEdit.eveFichero.value ==""){
			document.ArteEventoEdit.eveFichero.disabled=true	
		}
		if (document.ArteEventoEdit.eveImagen.value ==""){
			document.ArteEventoEdit.eveImagen.disabled=true	
		}
		document.ArteEventoEdit.submit();
	}

}

function putFicheroHidden(){
	document.ArteEventoEdit.eveFicheroHidden.value = document.ArteEventoEdit.eveFichero.value
}

function putImagenHidden(){
	document.ArteEventoEdit.eveImagenHidden.value = document.ArteEventoEdit.eveImagen.value
	document.ArteEventoEdit.eveImagenDelete.checked = false
}


//-->
</script>                                                          

		<div class="textHome health">
          	<p>
          	    <% if (eventoBean==null){ %>
						<spring:message code="arte.eventos.new"/>
				<% } else { %>		     
						<spring:message code="arte.eventos.edit"/>
				<% }%>
			</p>
          	<p>	

				<form action="<%= uploadURL %>" method="post" name="ArteEventoEdit" id="ArteEventoEdit" enctype="multipart/form-data" />
				
				<table align="center" width="90%" cellspacing="2" cellpadding="0" border="0">
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="arte.eventos.form.asuntoEs"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="eveAsuntoEs" value="<% if (eventoBean!=null && eventoBean.getEveAsuntoEs()!=null){ %><%= eventoBean.getEveAsuntoEs() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="arte.eventos.form.asuntoEn"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="eveAsuntoEn" value="<% if (eventoBean!=null && eventoBean.getEveAsuntoEn()!=null){ %><%= eventoBean.getEveAsuntoEn() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="arte.eventos.form.asuntoDe"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="eveAsuntoDe" value="<% if (eventoBean!=null && eventoBean.getEveAsuntoDe()!=null){ %><%= eventoBean.getEveAsuntoDe() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="arte.eventos.form.usu"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="255" name="eveIdUsu" value="<% if (eventoBean!=null && eventoBean.getEveIdUsuHtml()!=null){ %><%= eventoBean.getEveIdUsuHtml() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr>
				   	<td CLASS="CABECERA" align="center">  
				     	<spring:message code="arte.eventos.form.fecha"/><spring:message code="general.desde"/>
				   	</td>
				    <td class="CELDA" valign="middle" id="artFechaDesde"> 
				    	<script>DateInput('eveFechaDesde', true, 'DD/MM/YYYY'<% if (eventoBean!=null && eventoBean.getEveFechaDesde()!=null){ %>,'<%=Utils.getFormat(eventoBean.getEveFechaDesde()) %>'<% } %>)</script>
				    	<select name="eveFechaDesdeHH">
		                <% for (int i=0;i<=24;i++){ %>
		                	<% if (i<10){%><option value="0<%=i%>">0<%=i%></option>
		                	<% }else{%><option value="<%=i%>"><%=i%></option>
		                	<% } %>
                		<% } %>
                		</select>&nbsp;:
                		<select name="eveFechaDesdeMM">
		                <% for (int i=0;i<=60;i++){ %>
		                	<% if (i<10){%><option value="0<%=i%>">0<%=i%></option>
		                	<% }else{%><option value="<%=i%>"><%=i%></option>
		                	<% } %>
                		<% } %>
                		</select>
				    </td>  
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr>
				   	<td CLASS="CABECERA" align="center">  
				     	<spring:message code="arte.eventos.form.fecha"/><spring:message code="general.hasta"/>
				   	</td>
				    <td class="CELDA" valign="middle" id="artFechaHasta"> 
				    	<script>DateInput('eveFechaHasta', true, 'DD/MM/YYYY'<% if (eventoBean!=null && eventoBean.getEveFechaHasta()!=null){ %>,'<%=Utils.getFormat(eventoBean.getEveFechaHasta()) %>'<% } %>)</script>
   						<select name="eveFechaHastaHH">
		                <% for (int i=0;i<=24;i++){ %>
		                	<% if (i<10){%><option value="0<%=i%>">0<%=i%></option>
		                	<% }else{%><option value="<%=i%>"><%=i%></option>
		                	<% } %>
                		<% } %>
                		</select>&nbsp;:
                		<select name="eveFechaHastaMM">
		                <% for (int i=0;i<=60;i++){ %>
		                	<% if (i<10){%><option value="0<%=i%>">0<%=i%></option>
		                	<% }else{%><option value="<%=i%>"><%=i%></option>
		                	<% } %>
                		<% } %>
                		</select>
                			<SCRIPT>
			               		<% if (eventoBean!=null && eventoBean.getEveFechaDesde()!=null){ %>document.ArteEventoEdit.eveFechaDesdeHH.options[<%=Utils.getFormat(eventoBean.getEveFechaDesde(),Utils.formatoFechaHHJava)%>].selected = true;<% } %>
			               		<% if (eventoBean!=null && eventoBean.getEveFechaDesde()!=null){ %>document.ArteEventoEdit.eveFechaDesdeMM.options[<%=Utils.getFormat(eventoBean.getEveFechaDesde(),Utils.formatoFechaMMJava)%>].selected = true;<% } %>
			               		<% if (eventoBean!=null && eventoBean.getEveFechaHasta()!=null){ %>document.ArteEventoEdit.eveFechaHastaHH.options[<%=Utils.getFormat(eventoBean.getEveFechaHasta(),Utils.formatoFechaHHJava)%>].selected = true;<% } %>
			               		<% if (eventoBean!=null && eventoBean.getEveFechaHasta()!=null){ %>document.ArteEventoEdit.eveFechaHastaMM.options[<%=Utils.getFormat(eventoBean.getEveFechaHasta(),Utils.formatoFechaMMJava)%>].selected = true;<% } %>
			           		</SCRIPT>
                	 </td>  
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				    	<spring:message code="arte.eventos.form.file"/> 
				    </td>
				    <td class="CELDA" valign="middle">
				    	<input type="file" name="eveFichero" size="35" onChange="putFicheroHidden()">
				    	<%  String artFicheroName = "";
				    		if (eventoBean!=null 
				    				&& eventoBean.getEveFicheroName()!=null
				    					&& eventoBean.getEveFicheroName().length()>0){ 
				    			artFicheroName = eventoBean.getEveFicheroName();%>
				    		<%= artFicheroName %>
				    	<% } %>
				    	<input type="radio" name="eveTipoLink" value="1" onChange="changeTipoLink()" checked />
				    	<input type="hidden" name="eveFicheroHidden" value="<%= artFicheroName %>" />
				    </td>	
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				    	<spring:message code="arte.eventos.form.link"/> 
				    </td>
				    <td class="CELDA" valign="middle">
						<input type="text" size="50" maxlength="255" name="eveLink" value="<% if (eventoBean!=null && eventoBean.getEveLink()!=null){ %><%= eventoBean.getEveLink() %><% } %>"/>
						<input type="radio" name="eveTipoLink" value="2" onChange="changeTipoLink()" <% if (eventoBean!=null && eventoBean.getEveTipoLink()==2){ %> checked<% } %>/>
					</td>	
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="arte.eventos.form.textEs"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="eveTextoEs" cols="38" rows="6"><% if (eventoBean!=null && eventoBean.getEveTextoEs()!=null){ %><%= eventoBean.getEveTextoEs().getValue() %><% } %></textarea>
				    	<input type="radio" name="eveTipoLink" value="3" onChange="changeTipoLink()" <% if (eventoBean!=null && eventoBean.getEveTipoLink()==3){ %>checked<% } %> />
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="arte.eventos.form.textEn"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="eveTextoEn" cols="38" rows="6"><% if (eventoBean!=null && eventoBean.getEveTextoEn()!=null){ %><%= eventoBean.getEveTextoEn().getValue() %><% } %></textarea>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="arte.eventos.form.textDe"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="eveTextoDe" cols="38" rows="6"><% if (eventoBean!=null && eventoBean.getEveTextoDe()!=null){ %><%= eventoBean.getEveTextoDe().getValue() %><% } %></textarea>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      	<spring:message code="arte.eventos.form.imagen"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<input type="file" name="eveImagen" size="35" onChange="putImagenHidden()">
				    	<%  String artImagenName = "";
				    		if (eventoBean!=null 
				    				&& eventoBean.getEveImagenName()!=null
				    					&& eventoBean.getEveImagenName().length()>0){ 
				    			artImagenName = eventoBean.getEveImagenName();%>
				    		<br><%= artImagenName %>
				    		<input type="checkbox" name="eveImagenDelete" /> <spring:message code="general.deleteImg"/>
				    	<% } %>
				    	<input type="hidden" name="eveImagenHidden" value="<%= artImagenName %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
				    <td colspan="2" class="CELDA" align="center"> 
				    	<spring:message code="form.enabled"/>
					    <input type="checkbox" name="eveActivado" value="<% if ((eventoBean!=null && eventoBean.getEveActivado()==1) || eventoBean==null){ %>1" checked<% } else { %>" <% } %>>
					    <input type="hidden" name="eveId" value="<% if (eventoBean!=null && eventoBean.getEveId()!=null){ %><%= eventoBean.getEveId() %><% } %>"/>
				    </td>
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
				    <td colspan="2" class="CELDA" align="center"> 
				       	<input type="button" id="cancelar" value="<spring:message code="form.cancel"/>" onClick="history.go(-1)">
				      	<input type="button" id="aceptar" value="<spring:message code="form.accept"/>" onClick="verifierForm()">
				      	<input type="hidden" name="urlRedirect" value="<%=urlRedirect%>">
				    </td>
				</tr>
				</table>
				
				</form>
			</p>
          	
	    </div>

	</div>	

<%@include file="Pie.jsp"%>
