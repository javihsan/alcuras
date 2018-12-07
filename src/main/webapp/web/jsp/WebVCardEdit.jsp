<%@ page import="java.util.*"%>
<%@ page import="com.alcuras.web.negocio.dto.WebDTO" %>
<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<%@ page import="com.google.appengine.api.blobstore.*" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%
String urlRedirect = (String)request.getAttribute("urlRedirect");
final BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
final BlobInfoFactory blobInfoFactory = new BlobInfoFactory(DatastoreServiceFactory.getDatastoreService());
WebDTO webBean = (WebDTO)request.getAttribute("web");

String uploadURL = blobstoreService.createUploadUrl("/fileUpload");
//add host if in dev mode
if (com.google.appengine.api.utils.SystemProperty.environment.value() == com.google.appengine.api.utils.SystemProperty.Environment.Value.Production)
{
	uploadURL = /*"http://localhost:8888" + */uploadURL.substring(uploadURL.indexOf("/_ah"));
}
%>

<%@include file="CabeceraSaludAdmin.jsp"%>

<script type="text/javascript" language="javascript" src="/alcuras/alcuras.nocache.js"></script>
<script language="JavaScript" SRC="/web/util/calendarDateInput.js"></script>

<script language="JavaScript">
<!--

function verifierForm() {
	if (document.WebVCardEdit.webValorFile.value ==""){
		document.WebVCardEdit.webValorFile.disabled=true	
	}
	document.WebVCardEdit.submit();

}

function putFileHidden(){
	document.WebVCardEdit.webValorFileHidden.value = document.WebVCardEdit.webValorFile.value
	document.WebVCardEdit.webValorFileDelete.checked = false
}

//-->
</script>                                                          

		<div class="textHome health">
          	<p>
          	    <% if (webBean==null){ %>
						<spring:message code="web.vcard.new"/>
				<% } else { %>		     
						<spring:message code="web.vcard.edit"/>
				<% }%>
			</p>
          	<p>	

				<form action="<%= uploadURL %>" method="post" name="WebVCardEdit" id="WebVCardEdit" enctype="multipart/form-data" />
				
				<table align="center" width="90%" cellspacing="2" cellpadding="0" border="0">
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
					<td width="20%" CLASS="CABECERA" align="center"> 
				      	<spring:message code="web.vcard.form.webValorFile"/>
				    </td>
				    <td class="CELDA" valign="middle">
				    	<input type="file" name="webValorFile" size="35" onChange="putFileHidden()">
				    	<%  String webValorFileName = "";
				    		if (webBean!=null 
				    				&& webBean.getWebValorFile()!=null
				    					&& webBean.getWebValorFile().length()>0){ 
				    		BlobKey blobKey = new BlobKey(webBean.getWebValorFile());
							BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
							webValorFileName = blobInfo.getFilename();%>
				    		<br><%= webValorFileName %>
				    		<input type="checkbox" name="webValorFileDelete" /> <spring:message code="general.deleteFile"/>
				    	<% } %>
				    	<input type="hidden" name="webValorFileHidden" value="<%= webValorFileName %>"/>
				    </td>	
				</tr>
				<tr><td height="10px"><SPACER type="block"></td></tr>
				<tr valign="top"> 
				    <td colspan="2" class="CELDA" align="center"> 
				    	<spring:message code="form.enabled"/>
					    <input type="checkbox" name="webActivado" value="<% if ((webBean!=null && webBean.getWebActivado()==1) || webBean==null){ %>1" checked<% } else { %>" <% } %>>
					    <input type="hidden" name="webId" value="<% if (webBean!=null && webBean.getWebId()!=null){ %><%= webBean.getWebId() %><% } %>"/>
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
