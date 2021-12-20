<%@include file="CabeceraKeys.jsp"%>

<script src="/web/js/jQuery.js" type="text/javascript"></script>
<script type="text/javascript">
	function showAsoMenu(obj){
		var o = $(obj);
		var oT = o.attr('id');
		if(o.next().css('display') == 'block'){
			$('.subMenuSign').css('display','none');
			$('.subMenuDiv').css('display','none');
			return;
		}
		$('.subMenuSign').css('display','none');
		$('.subMenuDiv').css('display','none');
		o.next().css('top',o.offset().top - ((parseInt(o.next().css('height'))/2)) + (parseInt(o.css('height'))/2));
		o.next().css('left',o.offset().left + parseInt(o.css('width')) + 15);
		o.next().css('display','inline');
		o.next().next().css('top',o.offset().top - ((parseInt(o.next().next().css('height'))/2)) + (parseInt(o.css('height'))/2));
		o.next().next().css('left',o.next().offset().left + parseInt(o.next().css('width'))-1);
		o.next().next().css('display','inline');
		
	}
</script>
<%
String path = (String)request.getAttribute("path");
%>
<body id="health">
<div id="superContainer">
    <div id="header">
      	
      	<%@include file="CabeceraCentro.jsp"%>  
        
        <div class="rightHeaderContainer">
            <%@include file="Mulidioma.jsp"%>
            <img src="/web/img/healthHeaderPict.jpg" width="115" height="83" class="fotoMain"/>
        </div>
    </div>
    <div id="article">
   	 		
		<div class="titContainer">
	        	<img src="/web/img/<spring:message code="img.titHealth"/>" height="35" alt="<spring:message code="salud.home"/>" />
	    </div>
	     	<div class="menuInt">
    	    	<ul>
	            	<li <% if (path.indexOf("home")!=-1 && path.indexOf("homeop")==-1){ %> class="selected" <%}%>
	            	><a href="salud-home"><span><img src="/web/img/menuPointHealth.png" width="22" height="30" alt="<spring:message code="salud.home"/>" /></span><spring:message code="salud.home"/></a></li>
	               	<li <% if (path.indexOf("servicios")!=-1){ %> class="selected" <%}%>
	            	><a href="javascript:void(0);" onclick="showAsoMenu(this);" id="menu2"><span><img src="/web/img/menuPoitnServices.png" width="26" height="27" alt="<spring:message code="salud.servicios"/>" /></span><spring:message code="salud.servicios"/></a>
            	    	<div class="subMenuSign"><img src="/web/img/menuSignal.gif" width="16" height="28" /></div>
        	            <div class="subMenuDiv">
    	                	<ul>
    	                		<!--<li><a href="salud-servicios-pediatria"><spring:message code="salud.servicios.pediatria"/></a></li>-->
                            	<li><a href="salud-servicios-interna"><spring:message code="salud.servicios.interna"/></a></li>
	                        	<!--<li><a href="salud-servicios-homeop"><spring:message code="salud.servicios.homeop"/></a></li>-->
	                        	<!--<li><a href="salud-servicios-ferti"><spring:message code="salud.servicios.ferti"/></a></li>-->
                    	        <!--<li><a href="salud-servicios-music"><spring:message code="salud.servicios.music"/></a></li>-->
                    	        <!--<li><a href="salud-servicios-voz"><spring:message code="salud.servicios.voz"/></a></li>-->
                    	        <!--<li><a href="salud-servicios-preven"><spring:message code="salud.servicios.preven"/></a></li>-->
								<li><a href="salud-servicios-antro"><spring:message code="salud.servicios.antro"/></a></li>
                	            <li><a href="salud-servicios-orto"><spring:message code="salud.servicios.orto"/></a></li>
                	            <li><a href="salud-servicios-psicos"><spring:message code="salud.servicios.psicos"/></a></li>
                	            <li><a href="salud-talleres"><spring:message code="salud.talleres"/></a></li>
                	            <li><a href="salud-servicios-buceo"><spring:message code="salud.servicios.buceo"/></a></li>
            	            </ul>
        	            </div>
    	            </li>
	                <li <% if (path.indexOf("info")!=-1){ %> class="selected" <%}%>
	            	><a href="javascript:void(0);" onclick="showAsoMenu(this);" id="menu3"><span><img src="/web/img/menuPointInfo.png" width="25" height="20" alt="<spring:message code="salud.info"/>" /></span><spring:message code="salud.info"/></a>
                		<div class="subMenuSign"><img src="/web/img/menuSignal.gif" width="16" height="28" /></div>
            	        <div class="subMenuDiv">
            	        	<ul>
    	                        <li><a href="salud-info-guia"><spring:message code="salud.info.guia"/></a></li>
    	                        <li><a href="salud-info-citas"><spring:message code="salud.info.citas"/></a></li>
    	                        <li><a href="salud-info-citassegui"><spring:message code="salud.info.citassegui"/></a></li>
    	                        <li><a href="salud-info-internacion"><spring:message code="salud.info.internacion"/></a></li>
    	                        <li><a href="salud-info-segunda"><spring:message code="salud.info.segunda"/></a></li>
    	                        <li><a href="salud-info-diag"><spring:message code="salud.info.diag"/></a></li>
    	                        <li><a href="salud-info-cheque"><spring:message code="salud.info.cheque"/></a></li>
    	                        <li><a href="salud-info-reme"><spring:message code="salud.info.reme"/></a></li>
    	                        <li><a href="salud-info-pagos"><spring:message code="salud.info.pagos"/></a></li>
    	                        <li><a href="salud-info-seguros"><spring:message code="salud.info.seguros"/></a></li>
    	                        <li><a href="salud-info-cancela"><spring:message code="salud.info.cancela"/></a></li>
                    	    </ul>
                	    </div>
            	    </li>
            	    <li <% if (path.indexOf("instala")!=-1){ %> class="selected" <%}%>
            	    ><a href="salud-instala"><span><img src="/web/img/menuPointInstala.png" width="24" height="20" alt="<spring:message code="salud.instala"/>" /></span><spring:message code="salud.instala"/></a></li>
            	    <li <% if (path.indexOf("situacion")!=-1){ %> class="selected" <%}%>
	            	><a href="salud-situacion"><span><img src="/web/img/menuPointWhere.png" width="24" height="20" alt="<spring:message code="salud.situacion"/>" /></span><spring:message code="salud.situacion"/></a></li>
				    <li <% if (path.indexOf("contacto")!=-1){ %> class="selected" <%}%>
	            	><a href="salud-contacto"><span><img src="/web/img/menuPointContact.png" width="24" height="18" alt="<spring:message code="salud.contacto"/>" /></span><spring:message code="salud.contacto"/></a></li>
				    <li <% if (path.indexOf("articulos")!=-1){ %> class="selected" <%}%>
	            	><a href="salud-articulos"><span><img src="/web/img/menuPointArticles.png" width="24" height="22" alt="<spring:message code="salud.articulos"/>" /></span><spring:message code="salud.articulos"/></a></li>
				    <li <% if (path.indexOf("comentarios")!=-1){ %> class="selected" <%}%>
	            	><a href="salud-comentarios"><span><img src="/web/img/menuPointComments.png" width="23" height="20" alt="<spring:message code="salud.comentarios"/>" /></span><spring:message code="salud.comentarios"/></a></li>
				</ul>
	        </div>