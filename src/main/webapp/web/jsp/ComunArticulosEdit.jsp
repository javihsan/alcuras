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

<%@include file="CabeceraComunManager.jsp"%>

<script type="text/javascript" language="javascript" src="/alcuras/alcuras.nocache.js"></script>
<script language="JavaScript" SRC="/web/util/calendarDateInput.js"></script>

<script language="JavaScript">
<!--

window.onload = changeTipoLink;

function changeTipoLink() {
	if (document.ComunArticuloEdit.artTipoLink[0].checked){

		document.ComunArticuloEdit.artFichero.disabled=false	
		
		document.ComunArticuloEdit.artLink.disabled=true

		document.ComunArticuloEdit.artTextoEs.disabled=true
		document.ComunArticuloEdit.artTextoEn.disabled=true
		document.ComunArticuloEdit.artTextoDe.disabled=true

		document.ComunArticuloEdit.artImagen.disabled=true	

	} else if (document.ComunArticuloEdit.artTipoLink[1].checked){

		document.ComunArticuloEdit.artFichero.disabled=true	
 

		document.ComunArticuloEdit.artLink.disabled=false
			
		document.ComunArticuloEdit.artTextoEs.disabled=true
		document.ComunArticuloEdit.artTextoEn.disabled=true
		document.ComunArticuloEdit.artTextoDe.disabled=true

		document.ComunArticuloEdit.artImagen.disabled=true	

	} else if (document.ComunArticuloEdit.artTipoLink[2].checked){

		document.ComunArticuloEdit.artFichero.disabled=true	

		document.ComunArticuloEdit.artLink.disabled=true

		document.ComunArticuloEdit.artTextoEs.disabled=false
		document.ComunArticuloEdit.artTextoEn.disabled=false
		document.ComunArticuloEdit.artTextoDe.disabled=false
		document.ComunArticuloEdit.artImagen.disabled=false
	
			
	}
}

function verifierForm() {
	
	if (document.ComunArticuloEdit.artAsuntoEs.value==""){
		alert ('<spring:message code="comun.articulos.form.obligAsuntoEs"/>')
		document.ComunArticuloEdit.artAsuntoEs.focus()
	} else if (document.ComunArticuloEdit.artAsuntoEn.value==""){
		alert ('<spring:message code="comun.articulos.form.obligAsuntoEn"/>')
		document.ComunArticuloEdit.artAsuntoEn.focus()
	} else if (document.ComunArticuloEdit.artAsuntoDe.value==""){
		alert ('<spring:message code="comun.articulos.form.obligAsuntoDe"/>')
		document.ComunArticuloEdit.artAsuntoDe.focus()
	} else if (document.ComunArticuloEdit.artIdUsu.value==""){
		alert ('<spring:message code="comun.articulos.form.obligUsu"/>')
		document.ComunArticuloEdit.artIdUsu.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[0].checked && document.ComunArticuloEdit.artFicheroHidden.value==""){
		alert ('<spring:message code="comun.articulos.form.obligFile"/>')
		document.ComunArticuloEdit.artFichero.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[0].checked && document.ComunArticuloEdit.artFicheroHidden.value.indexOf(".pdf")==-1 && document.ComunArticuloEdit.artFicheroHidden.value.indexOf(".doc")==-1){
		alert ('<spring:message code="comun.articulos.form.obligFileType"/>')
		document.ComunArticuloEdit.artFichero.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[1].checked && document.ComunArticuloEdit.artLink.value==""){
		alert ('<spring:message code="comun.articulos.form.obligLink"/>')
		document.ComunArticuloEdit.artLink.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[2].checked && document.ComunArticuloEdit.artTextoEs.value==""){
		alert ('<spring:message code="comun.articulos.form.obligTextEs"/>')
		document.ComunArticuloEdit.artTextoEs.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[2].checked && document.ComunArticuloEdit.artTextoEs.value.length>3000){
		alert ('<spring:message code="comun.articulos.form.maxTextEs"/>')
		document.ComunArticuloEdit.artTextoEs.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[2].checked && document.ComunArticuloEdit.artTextoEn.value==""){
		alert ('<spring:message code="comun.articulos.form.obligTextEn"/>')
		document.ComunArticuloEdit.artTextoEn.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[2].checked && document.ComunArticuloEdit.artTextoEn.value.length>3000){
		alert ('<spring:message code="comun.articulos.form.maxTextEn"/>')
		document.ComunArticuloEdit.artTextoEn.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[2].checked && document.ComunArticuloEdit.artTextoDe.value==""){
		alert ('<spring:message code="comun.articulos.form.obligTextDe"/>')
		document.ComunArticuloEdit.artTextoDe.focus()
	} else if (document.ComunArticuloEdit.artTipoLink[2].checked && document.ComunArticuloEdit.artTextoDe.value.length>3000){
		alert ('<spring:message code="comun.articulos.form.maxTextDe"/>')
		document.ComunArticuloEdit.artTextoDe.focus()
	} /*else if (document.ComunArticuloEdit.artTipoLink[2].checked && document.ComunArticuloEdit.artImagenHidden.value==""){
		alert ('<spring:message code="comun.articulos.form.obligImagen"/>')
		document.ComunArticuloEdit.artImagen.focus()
	}  */else {
		if (document.ComunArticuloEdit.artFichero.value ==""){
			document.ComunArticuloEdit.artFichero.disabled=true	
		}
		if (document.ComunArticuloEdit.artImagen.value ==""){
			document.ComunArticuloEdit.artImagen.disabled=true	
		}
		document.ComunArticuloEdit.submit();
	}

}

function putFicheroHidden(){
	document.ComunArticuloEdit.artFicheroHidden.value = document.ComunArticuloEdit.artFichero.value
}

function putImagenHidden(){
	document.ComunArticuloEdit.artImagenHidden.value = document.ComunArticuloEdit.artImagen.value
	document.ComunArticuloEdit.artImagenDelete.checked = false
}

//-->
</script>                                                          

		<div class="textHome health">
          	<p>
          	    <% if (articuloBean==null){ %>
						<spring:message code="comun.articulos.new"/>
				<% } else { %>		     
						<spring:message code="comun.articulos.edit"/>
				<% }%>
			</p>
          	<p>	

				<form action="<%= uploadURL %>" method="post" name="ComunArticuloEdit" id="ComunArticuloEdit"  enctype="multipart/form-data" />
				
				<table align="center" width="90%" cellspacing="2" cellpadding="0" border="0">
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="comun.articulos.form.asuntoEs"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoEs" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoEs()!=null){ %><%= articuloBean.getArtAsuntoEs() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="comun.articulos.form.asuntoEn"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoEn" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoEn()!=null){ %><%= articuloBean.getArtAsuntoEn() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="comun.articulos.form.asuntoDe"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="400" name="artAsuntoDe" value="<% if (articuloBean!=null && articuloBean.getArtAsuntoDe()!=null){ %><%= articuloBean.getArtAsuntoDe() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      		<spring:message code="comun.articulos.form.usu"/>
				    	</td>
				    <td class="CELDA" valign="middle">
				    	<input type="text" size="50" maxlength="255" name="artIdUsu" value="<% if (articuloBean!=null && articuloBean.getArtIdUsuHtml()!=null){ %><%= articuloBean.getArtIdUsuHtml() %><% } %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>    
				<tr>
				   	<td CLASS="CABECERA" align="center">  
				     	<spring:message code="comun.articulos.form.fecha"/>
				   	</td>
				    <td class="CELDA" valign="middle" id="artFecha">
				    	<script>DateInput('artFecha', true, 'DD/MM/YYYY'<% if (articuloBean!=null && articuloBean.getArtFecha()!=null){ %>,'<%=Utils.getFormat(articuloBean.getArtFecha()) %>'<% } %>)</script>
				    </td>  
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				    	<spring:message code="comun.articulos.form.file"/> 
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
				    	<spring:message code="comun.articulos.form.link"/> 
				    </td>
				    <td class="CELDA" valign="middle">
						<input type="text" size="50" maxlength="255" name="artLink" value="<% if (articuloBean!=null && articuloBean.getArtLink()!=null){ %><%= articuloBean.getArtLink() %><% } %>"/>
						<input type="radio" name="artTipoLink" value="2" onChange="changeTipoLink()" <% if (articuloBean!=null && articuloBean.getArtTipoLink()==2){ %> checked<% } %>/>
					</td>	
				</tr>
				<tr><td height="40px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="comun.articulos.form.textEs"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoEs" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoEs()!=null){ %><%= articuloBean.getArtTextoEs().getValue() %><% } %></textarea>
				    	<input type="radio" name="artTipoLink" value="3" onChange="changeTipoLink()" <% if (articuloBean!=null && articuloBean.getArtTipoLink()==3){ %>checked<% } %> />
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="comun.articulos.form.textEn"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoEn" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoEn()!=null){ %><%= articuloBean.getArtTextoEn().getValue() %><% } %></textarea>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				     	<spring:message code="comun.articulos.form.textDe"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<textarea name="artTextoDe" cols="38" rows="6"><% if (articuloBean!=null && articuloBean.getArtTextoDe()!=null){ %><%= articuloBean.getArtTextoDe().getValue() %><% } %></textarea>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      	<spring:message code="comun.articulos.form.imagen"/>
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
