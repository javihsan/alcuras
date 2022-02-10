<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.ArticuloDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>

<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%
String urlRedirect = (String)request.getAttribute("urlRedirect");
ArticuloDTO articuloBean = (ArticuloDTO)request.getAttribute("articulo");
String uploadURL = "/fileUpload";
%>

<%@include file="CabeceraArteManager.jsp"%>

<script type="text/javascript" language="javascript" src="/alcuras/alcuras.nocache.js"></script>
<script language="JavaScript" SRC="/web/util/calendarDateInput.js"></script>

<script language="JavaScript">
<!--

window.onload = changeTipoLink;

function changeTipoLink() {
	if (document.ArteArticuloEdit.artTipoLink[0].checked){

		document.ArteArticuloEdit.artFichero.disabled=false	
		
		document.ArteArticuloEdit.artLink.disabled=true

		document.ArteArticuloEdit.artTextoEs.disabled=true
		document.ArteArticuloEdit.artTextoEn.disabled=true
		document.ArteArticuloEdit.artTextoDe.disabled=true

		document.ArteArticuloEdit.artImagen.disabled=true	

	} else if (document.ArteArticuloEdit.artTipoLink[1].checked){

		document.ArteArticuloEdit.artFichero.disabled=true	
 

		document.ArteArticuloEdit.artLink.disabled=false
			
		document.ArteArticuloEdit.artTextoEs.disabled=true
		document.ArteArticuloEdit.artTextoEn.disabled=true
		document.ArteArticuloEdit.artTextoDe.disabled=true

		document.ArteArticuloEdit.artImagen.disabled=true	

	} else if (document.ArteArticuloEdit.artTipoLink[2].checked){

		document.ArteArticuloEdit.artFichero.disabled=true	

		document.ArteArticuloEdit.artLink.disabled=true

		document.ArteArticuloEdit.artTextoEs.disabled=false
		document.ArteArticuloEdit.artTextoEn.disabled=false
		document.ArteArticuloEdit.artTextoDe.disabled=false
		document.ArteArticuloEdit.artImagen.disabled=false
	
			
	}
}

function verifierForm() {
	
	if (document.ArteArticuloEdit.artAsuntoEs.value==""){
		alert ('<spring:message code="arte.articulos.form.obligAsuntoEs"/>')
		document.ArteArticuloEdit.artAsuntoEs.focus()
	} else if (document.ArteArticuloEdit.artAsuntoEn.value==""){
		alert ('<spring:message code="arte.articulos.form.obligAsuntoEn"/>')
		document.ArteArticuloEdit.artAsuntoEn.focus()
	} else if (document.ArteArticuloEdit.artAsuntoDe.value==""){
		alert ('<spring:message code="arte.articulos.form.obligAsuntoDe"/>')
		document.ArteArticuloEdit.artAsuntoDe.focus()
	} else if (document.ArteArticuloEdit.artIdUsu.value==""){
		alert ('<spring:message code="arte.articulos.form.obligUsu"/>')
		document.ArteArticuloEdit.artIdUsu.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[0].checked && document.ArteArticuloEdit.artFicheroHidden.value==""){
		alert ('<spring:message code="arte.articulos.form.obligFile"/>')
		document.ArteArticuloEdit.artFichero.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[0].checked && document.ArteArticuloEdit.artFicheroHidden.value.indexOf(".pdf")==-1 && document.ArteArticuloEdit.artFicheroHidden.value.indexOf(".doc")==-1){
		alert ('<spring:message code="arte.articulos.form.obligFileType"/>')
		document.ArteArticuloEdit.artFichero.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[1].checked && document.ArteArticuloEdit.artLink.value==""){
		alert ('<spring:message code="arte.articulos.form.obligLink"/>')
		document.ArteArticuloEdit.artLink.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[2].checked && document.ArteArticuloEdit.artTextoEs.value==""){
		alert ('<spring:message code="arte.articulos.form.obligTextEs"/>')
		document.ArteArticuloEdit.artTextoEs.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[2].checked && document.ArteArticuloEdit.artTextoEs.value.length>3000){
		alert ('<spring:message code="arte.articulos.form.maxTextEs"/>')
		document.ArteArticuloEdit.artTextoEs.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[2].checked && document.ArteArticuloEdit.artTextoEn.value==""){
		alert ('<spring:message code="arte.articulos.form.obligTextEn"/>')
		document.ArteArticuloEdit.artTextoEn.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[2].checked && document.ArteArticuloEdit.artTextoEn.value.length>3000){
		alert ('<spring:message code="arte.articulos.form.maxTextEn"/>')
		document.ArteArticuloEdit.artTextoEn.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[2].checked && document.ArteArticuloEdit.artTextoDe.value==""){
		alert ('<spring:message code="arte.articulos.form.obligTextDe"/>')
		document.ArteArticuloEdit.artTextoDe.focus()
	} else if (document.ArteArticuloEdit.artTipoLink[2].checked && document.ArteArticuloEdit.artTextoDe.value.length>3000){
		alert ('<spring:message code="arte.articulos.form.maxTextDe"/>')
		document.ArteArticuloEdit.artTextoDe.focus()
	} /*else if (document.ArteArticuloEdit.artTipoLink[2].checked && document.ArteArticuloEdit.artImagenHidden.value==""){
		alert ('<spring:message code="arte.articulos.form.obligImagen"/>')
		document.ArteArticuloEdit.artImagen.focus()
	}  */else {
		if (document.ArteArticuloEdit.artFichero.value ==""){
			document.ArteArticuloEdit.artFichero.disabled=true	
		}
		if (document.ArteArticuloEdit.artImagen.value ==""){
			document.ArteArticuloEdit.artImagen.disabled=true	
		}
		document.ArteArticuloEdit.submit();
	}

}

function putFicheroHidden(){
	document.ArteArticuloEdit.artFicheroHidden.value = document.ArteArticuloEdit.artFichero.value
}

function putImagenHidden(){
	document.ArteArticuloEdit.artImagenHidden.value = document.ArteArticuloEdit.artImagen.value
	document.ArteArticuloEdit.artImagenDelete.checked = false
}

//-->
</script>                                                          

		<div class="textHome health">
          	<p>
          	    <% if (articuloBean==null){ %>
						<spring:message code="arte.articulos.new"/>
				<% } else { %>		     
						<spring:message code="arte.articulos.edit"/>
				<% }%>
			</p>
          	<p>	

				<form action="<%= uploadURL %>" method="post" name="ArteArticuloEdit" id="ArteArticuloEdit"  enctype="multipart/form-data" />
				
				<table align="center" width="90%" cellspacing="2" cellpadding="0" border="0">
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="arte.articulos.form.asuntoEs"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoEs" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoEs()!=null){ %><%= articuloBean.getArtAsuntoEs() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="arte.articulos.form.asuntoEn"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoEn" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoEn()!=null){ %><%= articuloBean.getArtAsuntoEn() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="arte.articulos.form.asuntoDe"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoDe" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoDe()!=null){ %><%= articuloBean.getArtAsuntoDe() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="arte.articulos.form.usu"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="255" name="artIdUsu" value="<% if (articuloBean!=null && articuloBean.getArtIdUsuHtml()!=null){ %><%= articuloBean.getArtIdUsuHtml() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr>
				   	<td CLASS="CABECERA" align="center">  
				     	<spring:message code="arte.articulos.form.fecha"/>
				   	</td>
				    <td class="CELDA" valign="middle" id="artFecha">
				    	<script>DateInput('artFecha', true, 'DD/MM/YYYY'<% if (articuloBean!=null && articuloBean.getArtFecha()!=null){ %>,'<%=Utils.getFormat(articuloBean.getArtFecha()) %>'<% } %>)</script>
				    </td>  
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				    	<spring:message code="arte.articulos.form.file"/> 
				    </td>
				    <td class="CELDA" valign="middle">
				    	<input type="file" name="artFichero" size="35" onChange="putFicheroHidden()">
				    	<%  String artFicheroName = "";
				    		if (articuloBean!=null 
				    				&& articuloBean.getArtFicheroName()!=null
				    					&& articuloBean.getArtFicheroName().length()>0){ 
							artFicheroName = articuloBean.getArtFicheroName();%>
				    		<%= artFicheroName %>
				    	<% } %>
				    	<input type="radio" name="artTipoLink" value="1" onChange="changeTipoLink()" checked />
				    	<input type="hidden" name="artFicheroHidden" value="<%= artFicheroName %>" />
				    </td>	
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				    	<spring:message code="arte.articulos.form.link"/> 
				    </td>
				    <td class="CELDA" valign="middle">
						<input type="text" size="50" maxlength="255" name="artLink" value="<% if (articuloBean!=null && articuloBean.getArtLink()!=null){ %><%= articuloBean.getArtLink() %><% } %>"/>
						<input type="radio" name="artTipoLink" value="2" onChange="changeTipoLink()" <% if (articuloBean!=null && articuloBean.getArtTipoLink()==2){ %> checked<% } %>/>
					</td>	
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="arte.articulos.form.textEs"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoEs" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoEs()!=null){ %><%= articuloBean.getArtTextoEs().getValue() %><% } %></textarea>
				    	<input type="radio" name="artTipoLink" value="3" onChange="changeTipoLink()" <% if (articuloBean!=null && articuloBean.getArtTipoLink()==3){ %>checked<% } %> />
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="arte.articulos.form.textEn"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoEn" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoEn()!=null){ %><%= articuloBean.getArtTextoEn().getValue() %><% } %></textarea>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="arte.articulos.form.textDe"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoDe" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoDe()!=null){ %><%= articuloBean.getArtTextoDe().getValue() %><% } %></textarea>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      	<spring:message code="arte.articulos.form.imagen"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<input type="file" name="artImagen" size="35" onChange="putImagenHidden()">
				    	<%  String artImagenName = "";
				    		if (articuloBean!=null 
				    				&& articuloBean.getArtImagenName()!=null
				    					&& articuloBean.getArtImagenName().length()>0){ 
							artImagenName = articuloBean.getArtImagenName();%>
				    		<br><%= artImagenName %>
				    		<input type="checkbox" name="artImagenDelete" /> <spring:message code="general.deleteImg"/>
				    	<% } %>
				    	<input type="hidden" name="artImagenHidden" value="<%= artImagenName %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
				    <td colspan="2" class="CELDA" align="center"> 
				    	<spring:message code="form.enabled"/>
					    <input type="checkbox" name="artActivado" value="<% if ((articuloBean!=null && articuloBean.getArtActivado()==1) || articuloBean==null){ %>1" checked<% } else { %>" <% } %>>
					    <input type="hidden" name="artId" value="<% if (articuloBean!=null && articuloBean.getArtId()!=null){ %><%= articuloBean.getArtId() %><% } %>"/>
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
