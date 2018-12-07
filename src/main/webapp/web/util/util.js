/**Reemplaza pattern por repla dentro de la cadena str**/
function replace(str, pattern, repla) {

	s = 0;
	result = "";
    	e = str.indexOf(pattern, s)
	while (e >= 0) {
		result += str.substring(s, e);
		result += repla;
		s = e+pattern.length;
		e = str.indexOf(pattern, s)
	}

	result += str.substring(s);

	return result;
}	

function getValue (sel,num){
	return sel.options[num].value
}

function getText (sel,num){
	return sel.options[num].text
}

function buscarSelect(sel,texto){
	res = -1;
	i = 0
	encontrado = false
	while (i<sel.options.length && !encontrado){
		if (getText(sel,i)==texto){
			res = i
			encontrado = true
		}	
		i++
	}
	return res
}

function buscarArray(sel,texto){
	ress = -1;
	j = 0
	encontrados = false
	while (j<sel.length && !encontrados){
		if (sel[j]==texto){
			ress = j
			encontrados = true
		}	
		j++
	}
	return ress
}

function buscarSelectCoincidencias(sel,texto){
	res = 0;
	i = 0
	encontrado = false
	while (i<sel.options.length && !encontrado){
		if (getText(sel,i).indexOf(texto)!=-1
		   || texto.indexOf(getText(sel,i))!=-1){
			res = i
			encontrado = true
		}	
		i++
	}
	return res
}

function openWindow(ruta,nombre,ventana){
	window.open(ruta,nombre,ventana)
}	

function cerrar(){
	window.opener=self
	window.close()
}	

var focoDes=true
function foco (obj){
	if (focoDes){
		obj.blur()
	}	
}


function custom_print() {
    if (document.all) {
        if (navigator.appVersion.indexOf("MSIE 5.0") == -1) {
            var OLECMDID_PRINT = 6;
            var OLECMDEXECOPT_DONTPROMPTUSER = 2;
            var OLECMDEXECOPT_PROMPTUSER = 1;
            var WebBrowser = "<OBJECT ID=\"WebBrowser1\" WIDTH=0 HEIGHT=0 CLASSID=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></OBJECT>";
            document.body.insertAdjacentHTML("beforeEnd", WebBrowser);
            WebBrowser1.ExecWB(6, 2);
            WebBrowser1.outerHTML = "";
        } 
	else {
            print();
        }
    }
    else {
        print();
    }
}

function contactarPar(who,what){
	parent.location.href='mailto:'+who+'@mesoncastillo.com?subject='+what+'';
}

function contactar(){
	contactarPar('info','')	
}

function irParticipa() {
        document.location = 'ApliServlet?accion=participa';
}

function getFecha() {
	var today = new Date();
	var dia_sem = today.getDay()-1;
	var dias_sem = ['Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo']
	var dia = today.getDate();
	var mes = today.getMonth();
	var meses = ['enero','febrero','marzo','abril','mayo','junio','julio','agosto','septiembre','octubre','noviembre','diciembre']
	var anio = today.getFullYear();
	document.write(dias_sem[dia_sem]+', '+dia+' de '+meses[mes]+' de '+anio)	
}	


/**
* Codigo JavaScript para scrolear una capa arriba y abajo
*@Autor   Alfredo Rodriguez  ( arodriguez@valledelkas.com )
*/
    
    /**
    * variables globales de asignacion de navegador
    */    
    var ie=(document.all) ? 1:0;
    var n4=(document.layers) ? 1:0;
    var n6=(document.getElementById) ? 1:0;  
    var paso=0;
    var valor;
    var tiempo;
    var altura=0;
    var Y;
    var activo=false; 
    var capa="texto";
    var tiempo;
    
    /**
    * funcion para inicializar valores generales en la interfaz
    */
    function iniciaValores(alt,carta)
    {
        altura = -alt
        Y=-240;
        paso=0;        
        if (carta) capa = "textoCarta"
        else capa = "texto"
        
        if(ie)
        {
            document.all[capa].style.top=Y;
            document.all[capa].style.height=250;
            document.all[capa].style.clip="rect(0px,400px,250px,0px)"; 
        }
        else if(n4)
        {
             document.layers[capa].top=Y;
             document.layers[capa].height=500;
        }
        else if(n6)
        {        
            document.getElementById(capa).style.top=Y;
            document.getElementById(capa).style.height=500;
            document.getElementById(capa).style.clip="rect(0px,400px,350px,0px)";
        }
    }
   
    /**
    * funcion para scrolear la capa de textos
    */
    function mover(donde)
    { 
        if(ie)
        {
            clearTimeout(tiempo);
            Y=parseInt(document.all[capa].style.top);
            var H=parseInt(document.all[capa].style.height);
            if((Y>altura)&&(donde==0))
            {
                activo=true;
                valor=6;     
                paso+=valor;
                document.all[capa].style.top = Y-valor;
                document.all[capa].style.height = H+valor;
                eval("document.all['"+capa+"'].style.clip='rect("+paso+",400,"+(H+valor)+",0)'");
                tiempo=setTimeout("mover(0)",50);
            }
            else if((Y<=-250) && (donde==1) && (activo==true))
            { 
                valor=-6;
                paso+=valor;
                document.all[capa].style.top = Y-valor;
                document.all[capa].style.height = H+valor;
                eval("document.all['"+capa+"'].style.clip='rect("+paso+",400,"+(H+valor)+",0)'");
                tiempo=setTimeout("mover(1)",50);
            }
        }
        else if( n4)
        {
            Y=document.layers[capa].top;
            var H=document.layers[capa].height;
            if((Y>altura)&&(donde==0))
            {
                activo=true;
                valor=5;     
                paso+=valor;
                document.layers[capa].top = Y-valor;
                document.layers[capa].height = H+valor;
                document.layers[capa].clip.top=paso;
                document.layers[capa].clip.bottom=(H+valor);
                tiempo=setTimeout("mover(0)",50);
            }
            else if((Y<=-250)&&(donde==1) && (activo==true))
            {         
                valor=-5;
                paso+=valor;
                document.layers[capa].top = Y-valor;
                document.layers[capa].height = H+valor;
                document.layers[capa].clip.top=paso;
                document.layers[capa].clip.bottom=(H+valor);
                tiempo=setTimeout("mover(1)",50);
            }
        }
        else if(n6)
        {
            Y=parseInt(document.getElementById(capa).style.top);
            var H=parseInt(document.getElementById(capa).style.height);
            if((Y>altura)&&(donde==0))
            {
                activo=true;
                valor=10;     
                paso+=valor;
                document.getElementById(capa).style.top = Y-valor;
                document.getElementById(capa).style.height = H+valor;
                eval("document.getElementById('"+capa+"').style.clip='rect("+paso+",400,"+(H+valor)+",0)'");
                tiempo=setTimeout("mover(0)",50);
            }
            else if((Y<=-250)&&(donde==1) && (activo==true))
            {         
                valor=-10;
                paso+=valor;
                document.getElementById(capa).style.top = Y-valor;
                document.getElementById(capa).style.height = H+valor;
                eval("document.getElementById('"+capa+"').style.clip='rect("+paso+",400,"+(H+valor)+",0)'");
                tiempo=setTimeout("mover(1)",50);
            }
        }
    }
    
