<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Kahuu - C&oacute;mo funciona?</title>
</head>
<body>
		<script>
			$(document).ready(function(){
				//Examples of how to assign the Colorbox event to elements
				$(".ajax").colorbox();
				$(".youtube").colorbox({iframe:true, innerWidth:560, innerHeight:315});
			});
		</script>
  	<div class="page-bgtop">	
  		<div class="arriba">	
  			<div class="tituloBox"><h2>&iquest;C&oacute;mo Funciona?</h2></div>
			<div class="cuadroIzq">
				<p align="justify">	
					Kahuu es un portal web donde podr&aacute;s encontrar todo tipo de servicios para el hogar.
					Tenemos en nuestro directorio desde carpinteros y electricistas hasta t&eacute;cnicos de computador y
					profesores. 
				</p>
				<br/>
				<p align="justify">
					Para comenzar a usar Kahuu s&oacute;lo tienes que registrarte <g:link controller="usuario" action="login">aqu&iacute;</g:link>. Una vez ingresado puedes navegar por
					las diferentes categor&iacute;as o usar el buscador para encontrar lo que buscas. Identificada la persona que 
					quieres contratar, la llamas y te pones de acuerdo con ella. Despu&eacute;s de haber recibido el servicio, retornas a Kahuu y lo calificas.
				</p>
			</div>
			<div class="cuadroDer">
				<a href="http://www.youtube.com/embed/vD7LkpPxwhE?rel=0" class="youtube"><img width="350px" height="200px" src="${resource(dir: 'images', file: 'video_principal.jpg')}" /></a>	
			</div>
		</div>
		<div>
			<div class="cuadroIzq">
				<div class="tituloBox2"><h2>Pasos para Usuarios</h2></div>
	  			<a href="http://www.youtube.com/embed/WSHgNMjL8Lc?rel=0" class="youtube"><img width="350px" height="200px" src="${resource(dir: 'images', file: 'video_usuarios.jpg')}" /></a>
	  		</div>
	  		<div class="cuadroDer">
	  			<div class="tituloBox2"><h2>Pasos para Profesionales</h2></div>
				<a href="http://www.youtube.com/embed/BDLVBYaAmfY?rel=0" class="youtube"><img width="350px" height="200px" src="${resource(dir: 'images', file: 'video_profesionales.jpg')}" /></a>	
			</div>
		</div>
  	</div>
</body>
</html>