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
			<div class="page-bgtop">		
				<div class="index-content">
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
					<p> Para ofrecer un servicio puedes inscribirte enviandonos un correo a soporte@kelgal.com.
					</p>
						<br/><br/><br/>
					<h3>Como Funciona: </h3>
				</div>
					
				<table id="tableIndex">		
				<tr>
					<td>
					<h3>Ofrecer un Servicio</h3>
					<ul>			
						<li>1. Publicar servicio</li>
						<li>2. Esperar llamada.</li>
						<li>3. Ofrecer el servicio.</li>
					</ul>
					</td>	
					<td>
					<h3>Recibir un Servicio</h3>
					<ul>
						<li>1. Buscar un servicio.</li>
						<li>2. Contactar a la persona.</li>
						<li>3. Recibir el servicio.</li>

					</ul>
					</td>
				</tr>
				</table>
		
				<div align="center">
					<p>
						Ingresando a la p&aacute;gina de Kahuu Servicios usted acepta las Politicas de Privacidad y T&eacute;rminos y Condiciones
						de Kahuu Servicios.
					</p>
					<br/>
					<g:select id="id" name="nombre" from="${ciudadesLista}" optionKey="nombre"  optionValue="nombre" value="${nombre}" class="many-to-one"/>
								
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