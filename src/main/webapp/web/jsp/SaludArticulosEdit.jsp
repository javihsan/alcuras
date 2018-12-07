<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.ArticuloDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.google.appengine.api.blobstore.*" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%
String urlRedirect = (String)request.getAttribute("urlRedirect");
final BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
final BlobInfoFactory blobInfoFactory = new BlobInfoFactory(DatastoreServiceFactory.getDatastoreService());
ArticuloDTO articuloBean = (ArticuloDTO)request.getAttribute("articulo");

String uploadURL = blobstoreService.createUploadUrl("/fileUpload");
//add host if in dev mode
if (com.google.appengine.api.utils.SystemProperty.environment.value() == com.google.appengine.api.utils.SystemProperty.Environment.Value.Production)
{
	uploadURL = /*"http://localhost:8888" + */uploadURL.substring(uploadURL.indexOf("/_ah"));
}
%>

<%@include file="CabeceraSaludManager.jsp"%>

<script type="text/javascript" language="javascript" src="/alcuras/alcuras.nocache.js"></script>
<script language="JavaScript" SRC="/web/util/calendarDateInput.js"></script>

<script language="JavaScript">
<!--

window.onload = changeTipoLink;

function changeTipoLink() {
	if (document.SaludArticuloEdit.artTipoLink[0].checked){

		document.SaludArticuloEdit.artFichero.disabled=false	
		
		document.SaludArticuloEdit.artLink.disabled=true

		document.SaludArticuloEdit.artTextoEs.disabled=true
		document.SaludArticuloEdit.artTextoEn.disabled=true
		document.SaludArticuloEdit.artTextoDe.disabled=true

		document.SaludArticuloEdit.artImagen.disabled=true	

	} else if (document.SaludArticuloEdit.artTipoLink[1].checked){

		document.SaludArticuloEdit.artFichero.disabled=true	
 

		document.SaludArticuloEdit.artLink.disabled=false
			
		document.SaludArticuloEdit.artTextoEs.disabled=true
		document.SaludArticuloEdit.artTextoEn.disabled=true
		document.SaludArticuloEdit.artTextoDe.disabled=true

		document.SaludArticuloEdit.artImagen.disabled=true	

	} else if (document.SaludArticuloEdit.artTipoLink[2].checked){

		document.SaludArticuloEdit.artFichero.disabled=true	

		document.SaludArticuloEdit.artLink.disabled=true

		document.SaludArticuloEdit.artTextoEs.disabled=false
		document.SaludArticuloEdit.artTextoEn.disabled=false
		document.SaludArticuloEdit.artTextoDe.disabled=false
		document.SaludArticuloEdit.artImagen.disabled=false
	
			
	}
}

function verifierForm() {
	
	if (document.SaludArticuloEdit.artAsuntoEs.value==""){
		alert ('<spring:message code="salud.articulos.form.obligAsuntoEs"/>')
		document.SaludArticuloEdit.artAsuntoEs.focus()
	} else if (document.SaludArticuloEdit.artAsuntoEn.value==""){
		alert ('<spring:message code="salud.articulos.form.obligAsuntoEn"/>')
		document.SaludArticuloEdit.artAsuntoEn.focus()
	} else if (document.SaludArticuloEdit.artAsuntoDe.value==""){
		alert ('<spring:message code="salud.articulos.form.obligAsuntoDe"/>')
		document.SaludArticuloEdit.artAsuntoDe.focus()
	} else if (document.SaludArticuloEdit.artIdUsu.value==""){
		alert ('<spring:message code="salud.articulos.form.obligUsu"/>')
		document.SaludArticuloEdit.artIdUsu.focus()
	} else if (document.SaludArticuloEdit.artTipoLink[0].checked && document.SaludArticuloEdit.artFicheroHidden.value==""){
		alert ('<spring:message code="salud.articulos.form.obligFile"/>')
		document.SaludArticuloEdit.artFichero.focus()
	} /*else if (document.SaludArticuloEdit.artTipoLink[0].checked && document.SaludArticuloEdit.artFicheroHidden.value.indexOf(".pdf")==-1 && document.SaludArticuloEdit.artFicheroHidden.value.indexOf(".doc")==-1){
		alert ('<spring:message code="salud.articulos.form.obligFileType"/>')
		document.SaludArticuloEdit.artFichero.focus()
	} */else if (document.SaludArticuloEdit.artTipoLink[1].checked && document.SaludArticuloEdit.artLink.value==""){
		alert ('<spring:message code="salud.articulos.form.obligLink"/>')
		document.SaludArticuloEdit.artLink.focus()
	} else if (document.SaludArticuloEdit.artTipoLink[2].checked && document.SaludArticuloEdit.artTextoEs.value==""){
		alert ('<spring:message code="salud.articulos.form.obligTextEs"/>')
		document.SaludArticuloEdit.artTextoEs.focus()
	} else if (document.SaludArticuloEdit.artTipoLink[2].checked && document.SaludArticuloEdit.artTextoEs.value.length>3000){
		alert ('<spring:message code="salud.articulos.form.maxTextEs"/>')
		document.SaludArticuloEdit.artTextoEs.focus()
	} else if (document.SaludArticuloEdit.artTipoLink[2].checked && document.SaludArticuloEdit.artTextoEn.value==""){
		alert ('<spring:message code="salud.articulos.form.obligTextEn"/>')
		document.SaludArticuloEdit.artTextoEn.focus()
	} else if (document.SaludArticuloEdit.artTipoLink[2].checked && document.SaludArticuloEdit.artTextoEn.value.length>3000){
		alert ('<spring:message code="salud.articulos.form.maxTextEn"/>')
		document.SaludArticuloEdit.artTextoEn.focus()
	} else if (document.SaludArticuloEdit.artTipoLink[2].checked && document.SaludArticuloEdit.artTextoDe.value==""){
		alert ('<spring:message code="salud.articulos.form.obligTextDe"/>')
		document.SaludArticuloEdit.artTextoDe.focus()
	} else if (document.SaludArticuloEdit.artTipoLink[2].checked && document.SaludArticuloEdit.artTextoDe.value.length>3000){
		alert ('<spring:message code="salud.articulos.form.maxTextDe"/>')
		document.SaludArticuloEdit.artTextoDe.focus()
	} /*else if (document.SaludArticuloEdit.artTipoLink[2].checked && document.SaludArticuloEdit.artImagenHidden.value==""){
		alert ('<spring:message code="salud.articulos.form.obligImagen"/>')
		document.SaludArticuloEdit.artImagen.focus()
	}  */else {
		if (document.SaludArticuloEdit.artFichero.value ==""){
			document.SaludArticuloEdit.artFichero.disabled=true	
		}
		if (document.SaludArticuloEdit.artImagen.value ==""){
			document.SaludArticuloEdit.artImagen.disabled=true	
		}
		document.SaludArticuloEdit.submit();
	}

}

function putFicheroHidden(){
	document.SaludArticuloEdit.artFicheroHidden.value = document.SaludArticuloEdit.artFichero.value
}

function putImagenHidden(){
	document.SaludArticuloEdit.artImagenHidden.value = document.SaludArticuloEdit.artImagen.value
	document.SaludArticuloEdit.artImagenDelete.checked = false
}

//-->
</script>                                                          

		<div class="textHome health">
          	<p>
          	    <% if (articuloBean==null){ %>
						<spring:message code="salud.articulos.new"/>
				<% } else { %>		     
						<spring:message code="salud.articulos.edit"/>
				<% }%>
			</p>
          	<p>	

				<form action="<%= uploadURL %>" method="post" name="SaludArticuloEdit" id="SaludArticuloEdit" enctype="multipart/form-data" />
				
				<table align="center" width="90%" cellspacing="2" cellpadding="0" border="0">
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="salud.articulos.form.asuntoEs"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoEs" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoEs()!=null){ %><%= articuloBean.getArtAsuntoEs() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="salud.articulos.form.asuntoEn"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoEn" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoEn()!=null){ %><%= articuloBean.getArtAsuntoEn() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="salud.articulos.form.asuntoDe"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoDe" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoDe()!=null){ %><%= articuloBean.getArtAsuntoDe() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="salud.articulos.form.usu"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="255" name="artIdUsu" value="<% if (articuloBean!=null && articuloBean.getArtIdUsuHtml()!=null){ %><%= articuloBean.getArtIdUsuHtml() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr>
				   	<td CLASS="CABECERA" align="center">  
				     	<spring:message code="salud.articulos.form.fecha"/>
				   	</td>
				    <td class="CELDA" valign="middle" id="artFecha">
				    	<script>DateInput('artFecha', true, 'DD/MM/YYYY'<% if (articuloBean!=null && articuloBean.getArtFecha()!=null){ %>,'<%=Utils.getFormat(articuloBean.getArtFecha()) %>'<% } %>)</script>
				    </td>  
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				    	<spring:message code="salud.articulos.form.file"/> 
				    </td>
				    <td class="CELDA" valign="middle">
				    	<input type="file" name="artFichero" size="35" onChange="putFicheroHidden()">
				    	<%  String artFicheroName = "";
				    		if (articuloBean!=null 
				    				&& articuloBean.getArtFichero()!=null
				    					&& articuloBean.getArtFichero().length()>0){ 
				    		BlobKey blobKey = new BlobKey(articuloBean.getArtFichero());
							BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
							artFicheroName = blobInfo.getFilename();%>
				    		<%= artFicheroName %>
				    	<% } %>
				    	<input type="radio" name="artTipoLink" value="1" onChange="changeTipoLink()" checked />
				    	<input type="hidden" name="artFicheroHidden" value="<%= artFicheroName %>" />
				    </td>	
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				    	<spring:message code="salud.articulos.form.link"/> 
				    </td>
				    <td class="CELDA" valign="middle">
						<input type="text" size="50" maxlength="255" name="artLink" value="<% if (articuloBean!=null && articuloBean.getArtLink()!=null){ %><%= articuloBean.getArtLink() %><% } %>"/>
						<input type="radio" name="artTipoLink" value="2" onChange="changeTipoLink()" <% if (articuloBean!=null && articuloBean.getArtTipoLink()==2){ %> checked<% } %>/>
					</td>	
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="salud.articulos.form.textEs"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoEs" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoEs()!=null){ %><%= articuloBean.getArtTextoEs().getValue() %><% } %></textarea>
				    	<input type="radio" name="artTipoLink" value="3" onChange="changeTipoLink()" <% if (articuloBean!=null && articuloBean.getArtTipoLink()==3){ %>checked<% } %> />
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="salud.articulos.form.textEn"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoEn" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoEn()!=null){ %><%= articuloBean.getArtTextoEn().getValue() %><% } %></textarea>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="salud.articulos.form.textDe"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoDe" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoDe()!=null){ %><%= articuloBean.getArtTextoDe().getValue() %><% } %></textarea>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      	<spring:message code="salud.articulos.form.imagen"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<input type="file" name="artImagen" size="35" onChange="putImagenHidden()">
				    	<%  String artImagenName = "";
				    		if (articuloBean!=null 
				    				&& articuloBean.getArtImagen()!=null
				    					&& articuloBean.getArtImagen().length()>0){ 
				    		BlobKey blobKey = new BlobKey(articuloBean.getArtImagen());
							BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
							artImagenName = blobInfo.getFilename();%>
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
