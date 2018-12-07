
	<%@include file="CabeceraSalud.jsp"%>
	
	        <div class="textHome health inst">
	            <div class="imgCaption"></div>
	        		
        		<spring:message code="salud.home.text"/>
				<div class="divFondoSalud"></div>
				
				<div class="divArbol">					
					<a href="salud-servicios-voz" class="voz <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.servicios.vozImg"/>
					</a>
					<a href="salud-servicios-music" class="music <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.servicios.music"/>
					</a>
					<a href="salud-talleres" class="talleres <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.talleres"/>
					</a>
					<a href="salud-servicios-preven" class="preven <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.servicios.prevenImg"/>
					</a>
					<a href="salud-servicios-ferti" class="ferti <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.servicios.ferti"/>
					</a>
					 <a href="salud-servicios-homeop" class="homeo <%=RequestContextUtils.getLocale(request).getLanguage()%>">
	        	    	<spring:message code="salud.servicios.homeop"/>
	        	    </a>
	        	    <a href="salud-servicios-antro" class="antro <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						 <spring:message code="salud.servicios.antroImg"/>
					</a>
	        	    <a href="salud-servicios-pediatria" class="pediatria <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.servicios.pediatria"/>
					</a>
					<a href="salud-servicios-psicos" class="psicosomatica <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.servicios.psicosImg"/>
					</a>
					<a href="salud-servicios-orto" class="orto <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.servicios.ortoImg"/>
					</a>
					<a href="salud-servicios-interna" class="interna <%=RequestContextUtils.getLocale(request).getLanguage()%>">
						<spring:message code="salud.servicios.internaImg"/>
					</a>
				</div>
			</div>
	    		
		</div>	
	
	<%@include file="Pie.jsp"%>