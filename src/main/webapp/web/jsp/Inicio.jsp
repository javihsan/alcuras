
	<%@include file="CabeceraHome.jsp"%>
		
			<div style="margin-top:57px !important;" class="menuHome">
	        	<img style="margin-bottom:10px; margin-left:100px;" src="/web/img/home/Hamaca-Palmera.jpg" widths="366px" width="486px" height="200px" heights="288px" alt="" />
	        	<!--<iframe style="margin-bottom:10px; margin-left:100px;" src="https://player.vimeo.com/video/157274249" width="486" height="315" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>-->
	        </div>
	        <div style="margin-top:37px !important;" class="textHome">
	        	<p style="margin-bottoms:28px; font-size: 17px; color:#006eb3; line-height:15px; margin-left:100px;"><a target="_blank" href="/web/img/home/AlcurasDisfrutaVerano.pdf"><spring:message code="home.textPlane"/></a><br><br><br></p>
	        	<!--<p style="margin-bottoms:58px; font-size: 14px; margin-left:100px;" class_ss="message"><spring:message code="home.cabPlane"/></p>-->

	        	<!--<p class="textTitle" style="margin-bottom:6px; font-size: 12px;"><spring:message code="home.cabPlane"/></p>-->
	        	<!-- add esta linea cuando la img es anormalmente supr-alta para no descuadrar--><p><br><br><br><br><br><br></p>
	        	<!-- add esta linea cuando la img es anormalmente alta para no descuadrar--><p><br><br><br></p>
	        	<!-- add esta linea cuando la linea de arriba vaya sola para no descuadrar<p><br></p>-->
	        	<!--<p style="margin-bottoms:58px; font-size: 12px; margin-left:100px;">Foto: Veronika Schroecker - Salzburg<br><br><br></p>-->
	        	<!--<p style="margin-bottoms:28px; font-size: 15px; color:#006eb3; line-height:15px;"><a target="_blank" href="/web/img/home/GrenzZeitenCartel.jpg"><spring:message code="home.textPlane"/></a><br><br><br><br><br><br><br><br></p>--> 
	           	<!--<button style="margin-bottom:58px;" id="playMusic" onclick="playVid()" type="button">Music</button>-->      
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
<script language="JavaScript" type="text/javascript">
<!--

var mp3snd = "/web/img/mp3/CastelnuovoTedesco_Fantasia_Op.mp3";

document.write('<audio id="musicBG" controls autoplay preload="auto">');
//document.write('<audio id="musicBG" controls preload="auto">');
document.write('<source src="'+mp3snd+'" type="audio/mpeg">');
document.write('<!--[if lt IE 9]>');
document.write('<bgsound src="'+mp3snd+'" loop="1">');
document.write('<![endif]-->');
document.write('</audio>');

var vid = document.getElementById("musicBG"); 
var playMusic = document.getElementById("playMusic");
var playing = true;
var sesStorage = true;
try {
	sessionStorage.getItem("prueba");
	sessionStorage.setItem("prueba", "true");
} catch (e) {
	sesStorage = true;
}	
if (sesStorage) {
	if (sessionStorage.getItem("playing")==null) {
		sessionStorage.setItem("playing", "true");
	}
	if (sessionStorage.getItem("playing")=="true") {
		vid.play();
		playMusic.innerHTML='Stop music';
	} else {
		vid.pause();
		playMusic.innerHTML='Play music';
	}
} else {
	playing = true;
	vid.play();
	playMusic.innerHTML='Stop music';
}

function playVid() { 
	if (sesStorage) {
		if (sessionStorage.getItem("playing")=="false") {
			vid.play();
			sessionStorage.setItem("playing", "true");
			playMusic.innerHTML='Stop music';
		} else {
			vid.pause();
			sessionStorage.setItem("playing", "false");
			playMusic.innerHTML='Play music';
		}
	} else {
		if (!playing) {
			vid.play();
			playMusic.innerHTML='Stop music';
			playing = true;
		} else {
			vid.pause();
			playMusic.innerHTML='Play music';
			playing = false;
		}
	}	
} 

//-->
</script>