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
		<div id="page">
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
						<li>1. Ofrecer tu servicio en la p&aacute;gina.</li>
						<li>2. Esperar a que te contacten.</li>
						<li>3. Cuadrar con la persona para ofrecerle el servicio.</li>
						<li>4. Obtener tu dinero por el servicio que ofreciste.</li>
					</ul>
					

				</div>
				
				<div id="cuadroDer">
					<h3>Recibir un Servicio</h3>
					<ul>
						<li>1. Buscar un servicio que te interese.</li>
						<li>2. Contactar a la persona del servicio.</li>
						<li>3. Coordinar con &eacute;l para recibir el servicio que tu necesitas.</li>
						<li>4. Pagarle a la persona por su servicio.</li>
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
						<g:link controller="empleo" action="users" id="1">Ingresar</g:link>
					</div>
				</div>
				<!-- end #content -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
</body>
</html>