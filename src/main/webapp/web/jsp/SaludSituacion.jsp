
	<%@include file="CabeceraSalud.jsp"%>
	
	        <div class="textHome health">
	         <iframe width="635" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?ie=UTF8&q=Clínica Luso Española&gl=US&hl=<%=RequestContextUtils.getLocale(request).getLanguage()%>&hq=Clínica Luso Española&hnear=&t=h&z=16&vpsrc=0&iwloc=addr&amp;output=embed&cid=6929459537613732936"></iframe><br />
			 <a target="_blank" href="https://www.google.com/maps?ll=40.429973,-3.68111&z=16&t=h&hl=<%=RequestContextUtils.getLocale(request).getLanguage()%>&gl=US&cid=6929459537613732936" style="color: #0000FF; text-align: center; display:block;font-size:10px;"><spring:message code="salud.situacion.vermapa"/></a>
	        </div>
	    </div>		
			
	
	<%@include file="Pie.jsp"%>