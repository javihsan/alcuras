	<%@include file="CabeceraAdmin.jsp"%>
		
	<div class="menuHome">
	        	<a href="salud-articulos-admin"><img src="/web/img/<spring:message code="img.menuSalud"/>" width="346" height="79" alt="<spring:message code="salud.articulos.admin"/>" /></a>
		        <a href="arte-eventos-admin"><img src="/web/img/<spring:message code="img.menuArte"/>" width="346" height="79" alt="<spring:message code="arte.eventos.admin"/>" /></a>
	    	    <a href="comun-articulos-admin"><img src="/web/img/<spring:message code="img.menuComun"/>" width="346" height="79" alt="<spring:message code="comun.articulos.admin"/>" /></a>
	        </div>
	       <div class="textHome">
	        	<p class="textTitle"><spring:message code="home.text.cab"/></p>
	        	<p style="margin-bottom:40px;"><spring:message code="home.text.ini"/></p>
	        	<p style="margin-bottom:58px;"><spring:message code="home.text.salud"/></p>
	        	<p style="margin-bottom:60px;"><spring:message code="home.text.arte"/></p>
	        	<p style="margin-bottom:60px;"><spring:message code="home.text.comun"/></p>
	            <p class="textBye"><spring:message code="home.text.sal"/></p>
	        </div>
	    </div>


	<%@include file="Pie.jsp"%>