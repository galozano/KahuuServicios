<%@page import="kelgal.empleos.Ciudad"%>
<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="complete" />
<title>Bienvenidos!</title>
</head>

<style type="text/css">



</style>
<body>
	<div class="body">
			<div id="page-index">		
				<div align="center">
					<h2>Bienvenidos</h2>
						<br/><br/>
					<p>
						Kahuu Servicios es un portal para buscar servicios en Colombia!
					</p>
						<br/>
					<p>
						Lo mejor es que es completamente GRATIS.
					</p>
						<br/>
					<p> Para incribirte puedes enviarnos un correo a soporte@kelgal.com.
					</p>
						<br/><br/><br/>
					<h3>Como Funciona: </h3>
				</div>
							
				<div id="cuadroIzq">
					<h3>Ofrecer un Servicio</h3>
					<ul>			
						<li>1. Ofrecer servicio</li>
						<li>2. Esperar llamada.</li>
						<li>3. Ofrecerle servicio.</li>
						<li>4. Obten tu dinero.</li>
					</ul>
					

				</div>
				
				<div id="cuadroDer">
					<h3>Recibir un Servicio</h3>
					<ul>
						<li>1. Buscar un servicio.</li>
						<li>2. Contactar a la persona.</li>
						<li>3. Recibir el servicio.</li>
						<li>4. Pagarle servicio.</li>
					</ul>
				</div>
		
				<div align="center">
					<p>
						Ingresando a la p&aacute;gina de Kahuu Servicios usted acepta las Politicas de Privacidad y T&eacute;rminos y Condiciones
						de Kahuu Servicios.
					</p>
					<br/>
					<g:select id="ciudad.id" name="ciudad.nombre" from="${kelgal.empleos.Ciudad.list()}" optionKey="nombre"  optionValue="nombre" value="${profileInstance?.ciudad?.nombre}" class="many-to-one"/>
								
					<div id="ingresarBoton">
						<g:link controller="perfil" action="users" id="1">Continuar</g:link>
					</div>
				</div>
				<!-- end #content -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
</body>
</html>