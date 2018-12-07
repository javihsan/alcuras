<%@include file="/web/jsp/CabeceraKeys.jsp"%>
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
<body id="shop">
<div id="superContainer">
    <div id="header">
      	
      	<%@include file="/web/jsp/CabeceraCentro.jsp"%> 
        
        <div class="rightHeaderContainer">
            <%@include file="/web/jsp/Mulidioma.jsp"%>
            <img src="/web/shop/img/shopHeaderPict.jpg" width="115" height="83" class="fotoMain"/>
        </div>
    </div>
    <div id="article">
   	 		<div class="titContainerHome">
	     	</div>
	     	<div class="menuInt">
	        	<ul>
				    <li <% if (path.indexOf("shop")!=-1){ %> class="selected" <%}%>
	            	><a href="shop-home"><span><img src="/web/shop/img/menuPointArt.png" width="24" height="18" alt="<spring:message code="shop.home"/>" /></span><spring:message code="shop.home"/></a></li>
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
				    <li <% if (path.indexOf("product")!=-1){ %> class="selected" <%}%>
	            	><a href="shop-product"><span><img src="/web/shop/img/menuPointLinks.png" width="24" height="18" alt="<spring:message code="shop.product"/>" /></span><spring:message code="shop.product"/></a></li>
				    <li <% if (path.indexOf("contacto")!=-1){ %> class="selected" <%}%>
	            	><a href="shop-info"><span><img src="/web/shop/img/menuPointContact.png" width="24" height="18" alt="<spring:message code="shop.info"/>" /></span><spring:message code="shop.info"/></a></li>
				    <li <% if (path.indexOf("eventos")!=-1){ %> class="selected" <%}%>
	            	><a href="shop-consulta"><span><img src="/web/shop/img/menuPointEvents.png" width="24" height="18" alt="<spring:message code="shop.consulta"/>" /></span><spring:message code="shop.consulta"/></a></li>
				    <li <% if (path.indexOf("articulos")!=-1){ %> class="selected" <%}%>
	            	><a href="shop-second"><span><img src="/web/shop/img/menuPointArticles.png" width="24" height="22" alt="<spring:message code="shop.second"/>" /></span><spring:message code="shop.second"/></a></li>
				</ul>
	        </div>
