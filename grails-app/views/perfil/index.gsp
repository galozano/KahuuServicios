<%@page import="kahuu.general.Ciudad"%>
<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="complete" />
<title><g:message code="kahuu.index.bienvenidos" default="Bienvenidos!"/></title>
</head>
<body>
		<div class="page-bgtop">		
				<div class="index-content">
					<h2><g:message code="kahuu.index.bienvenidos" default="Bienvenidos!"/></h2>
						<br/><br/>
					<p>
						Kahuu Servicios es un portal para buscar servicios en Colombia!
					</p>
						<br/>
					<p>
						Lo mejor es que es completamente GRATIS.
					</p>
						<br/>
					<p> Para ofrecer un servicio puedes inscribirte enviandonos un correo a soporte@kahuu.co
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
					
					<g:form action="principal">
						<g:select id="id" name="nombreCiudad" from="${ciudadesLista}" optionKey="nombre"  optionValue="nombre" value="${nombre}" class="many-to-one"/>		
						<div id="ingresarBoton">
							<g:submitButton name="continuar" value="Continuar"/>
						</div>
					</g:form>
				</div>
				<!-- end #content -->
				<div style="clear: both;">&nbsp;</div>
			</div>
</body>
</html>