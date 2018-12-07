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
<body id="art">
<div id="superContainer">
    <div id="header">
      	
      	<%@include file="CabeceraCentro.jsp"%>  
        
        <div class="rightHeaderContainer">
            <%@include file="Mulidioma.jsp"%>
            <img src="/web/img/artHeaderPict.jpg" width="115" height="83" class="fotoMain"/>
        </div>
    </div>
    <div id="article">
   	 		
			<div class="titContainer">
	        	<img src="/web/img/<spring:message code="img.titArt"/>" height="35" alt="<spring:message code="arte.home"/>" />
	        </div>
	        <div class="menuInt">
	        	<ul>
				    <li <% if (path.indexOf("home")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-home"><span><img src="/web/img/menuPointArt.png" width="24" height="18" alt="<spring:message code="arte.home"/>" /></span><spring:message code="arte.home"/></a></li>
				    <!--  <li <% if (path.indexOf("team")!=-1){ %> class="selected" <%}%>
	                ><a href="javascript:void(0);" onclick="showAsoMenu(this);" id="menu3"><span><img src="/web/img/menuPointArtTeam.png" width="26" height="24" alt="<spring:message code="arte.team"/>" /></span><spring:message code="arte.team"/></a>
	               		<div class="subMenuSign"><img src="/web/img/menuSignal.gif" width="16" height="28" /></div>
        	            <div class="subMenuDiv">
    	                	<ul>
                            	<li><a href="arte-team"><spring:message code="arte.team"/></a></li>
                            	<li><a href="arte-marta-knorr"><spring:message code="arte.marta.knorr"/></a></li>
	                        	<li><a href="arte-valeriano-gamghebeli"><spring:message code="arte.valeriano.gamghebeli"/></a></li>
                    	        <li><a href="arte-stefan-dehmelt"><spring:message code="arte.stefan.dehmelt"/></a></li>
                	            <li><a href="arte-aurelio-viribay"><spring:message code="arte.aurelio.viribay"/></a></li>
								<li><a href="arte-karin-gutsche"><spring:message code="arte.karin.gutsche"/></a></li>
            	            </ul>
        	            </div>
        	        </li>-->
        	        <li <% if (path.indexOf("faqs")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-faqs"><span><img src="/web/img/menuPointFAQs.png" width="24" height="18" alt="<spring:message code="arte.faqs"/>" /></span><spring:message code="comun.faqs"/></a></li>
        	        <li <% if (path.indexOf("links")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-links"><span><img src="/web/img/menuPointLinks.png" width="24" height="18" alt="<spring:message code="arte.links"/>" /></span><spring:message code="arte.links"/></a></li>
				    <li <% if (path.indexOf("contacto")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-contacto"><span><img src="/web/img/menuPointContact.png" width="24" height="18" alt="<spring:message code="arte.contacto"/>" /></span><spring:message code="arte.contacto"/></a></li>
				    <li <% if (path.indexOf("eventos")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-eventos"><span><img src="/web/img/menuPointEvents.png" width="24" height="18" alt="<spring:message code="arte.eventos"/>" /></span><spring:message code="arte.eventos"/></a></li>
				    <li <% if (path.indexOf("articulos")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-articulos"><span><img src="/web/img/menuPointArticles.png" width="24" height="22" alt="<spring:message code="arte.articulos"/>" /></span><spring:message code="arte.articulos"/></a></li>
				    <li <% if (path.indexOf("comentarios")!=-1){ %> class="selected" <%}%>
	            	><a href="arte-comentarios"><span><img src="/web/img/menuPointComments.png" width="23" height="20" alt="<spring:message code="arte.comentarios"/>" /></span><spring:message code="arte.comentarios"/></a></li>
				</ul>
	        </div>