
	<%@include file="CabeceraHome.jsp"%>
		
			<div style="margin-top:57px !important;" class="menuHome">
	        	<!--<a target="_blank" href="/web/img/home/TratamientoFXMayr_<%=RequestContextUtils.getLocale(request).getLanguage()%>.pdf">
	        		<img style="margin-top:27px !important; margin-bottom:10px; margin-lefts:100px; margin-left:200px;" src="/web/img/home/AbdomenFxTipos.png" width="220px" height="290px" alt="" />
	        	</a>-->
	        	<img style="margin-top:27px !important; margin-bottom:10px; margin-lefts:100px; margin-left:200px;" src="/web/img/home/AbdomenFxTipos.png" width="220px" height="290px" alt="" />
	        	<!--<img style="margin-bottom:10px; margin-lefts:100px; margin-left:200px;" src="/web/img/home/Flores.jpg" width="366px" widths="486px" height="200px" heights="288px" alt="" />-->
	        	<!--<iframe style="margin-bottom:10px; margin-left:100px;" src="https://player.vimeo.com/video/157274249" width="486" height="315" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>-->
	        </div>
	        <div style="margin-top:37px !important;" class="textHome">
	        	<!--<p style="margin-bottoms:28px; font-size: 17px; color:#006eb3; line-height:15px; margin-left:100px;"><a target="_blank" href="/web/img/home/AlcurasDisfrutaVerano.pdf"><spring:message code="home.textPlane"/></a><br><br><br></p> -->
	        	<!--<p class="textTitle" style="margin-bottom:6px; font-size: 12px;"><spring:message code="home.cabPlane"/></p>-->
	        	<p style="margin-top:100px; margin-bottom:-150px; font-size: 14px; margin-left:10px;" class_ss="message"><spring:message code="home.cabPlane"/></p>
	        	<!-- add esta linea cuando la img es anormalmente supr-alta para no descuadrar--><p><br><br><br><br><br><br></p>
	        	<!-- add esta linea cuando la img es anormalmente alta para no descuadrar--><p><br><br><br><br><br><br><br><br><br><br><br><br><br></p>
	        	<!--<p style="margin-bottoms:58px; font-size: 12px; margin-left:10px;"><spring:message code="home.textPlane"/><br><br><br></p>-->
	        	<!--<p style="margin-bottom:5px; margin-left:10px; font-size: 15px; color:#006eb3; line-height:15px;"><a target="_blank" href="/web/img/home/TratamientoFXMayr_<%=RequestContextUtils.getLocale(request).getLanguage()%>.pdf"><spring:message code="home.textPlane"/></a><br><br></p>
	        	<p style="margin-left:10px; font-size: 15px; color:#006eb3; line-height:15px;"><a target="_blank" href="/web/img/home/LanzaroteFXMCitas2022.jpg"><spring:message code="home.textPlane2"/></a><br><br><br><br><br><br><br><br></p>-->
	         </div> 
	       	 <div style="margin-top:130px !important;" class="menuHome">
	        	<a href="salud-home"><img src="/web/img/<spring:message code="img.menuSalud"/>" width="346" height="79" alt="<spring:message code="salud.home"/>" /></a>
		        <a href="arte-home"><img src="/web/img/<spring:message code="img.menuArte"/>" width="346" height="79" alt="<spring:message code="arte.home"/>" /></a>
	    	    <a href="comun-home"><img src="/web/img/<spring:message code="img.menuComun"/>" width="346" height="79" alt="<spring:message code="comun.home"/>" /></a>
	         </div>
	         <div style="margin-top:7px !important;" class="textHome">
	        	<p class="textTitle" style="font-size: 18px;"><spring:message code="home.text.cab"/></p>
	        	<p style="margin-bottom:40px;"><spring:message code="home.text.ini"/></p>
	        	<p style="margin-bottom:58px;"><spring:message code="home.text.salud"/></p>
	        	<p style="margin-bottom:60px;"><spring:message code="home.text.arte"/></p>
	        	<p style="margin-bottom:60px;"><spring:message code="home.text.comun"/></p>
	            <p class="textBye"><spring:message code="home.text.sal"/></p>
	        </div>
	       
	    </div>

	<%@include file="Pie.jsp"%>
