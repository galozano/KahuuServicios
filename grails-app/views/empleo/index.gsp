<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title>Bienvenidos!</title>

</head>
<body>
	<div class="body">
		<div id="page">
			<div id="page-bgtop">	
				<div id="sidebar">
					<h1>Categorias</h1>
					<ul>
						<g:each in="${categoriasList}" var="categoriaInstance">
							<li>
								<g:link action="users" id="${categoriaInstance.id}"> ${fieldValue(bean: categoriaInstance, field: "nombre")}</g:link>					
							</li>
						</g:each>
					</ul>
				</div>				
				<div class="content">
					<h1>Bienvenidos</h1>
					
					<p>
						Kahuu Servicios es un portal para buscar servicios en Cartagena!
					</p>
					<br/>
					<p>
						Ingresando tus datos en el formulario nosotros te inscribiremos a la p&aacutegina de Kahuu Servicios donde personas de barrios 
						como Bocagrande, Castillogrande, Manga, etc. podr&aacuten consultar por diferentes categor&iacuteas o por nombre y buscarte en la base 
						de datos para as&iacute poder llamarte y pedirte el servicio que t&uacute ofreces. 
					</p>
					<br/>
					<p>
						Lo mejor es que para ustedes, incribirte, es completamente GRATIS.<br/><br/>
						Aprovecha esta oportunidad y inscr&iacutebete en la base de datos, solo te cuesta unos minutos. 
					</p>
						<br/>
					<p>
						En estos momentos nuestros servicios no ofrecen la inscripci&oacuten  por la p&aacutegina pero estamos trabajando en eso y pronto estar&aacute disponible.
						Si quieres inscribirte a la base de datos por favor ll&aacutemanos al numero 320-572-1687 o env&iacuteanos un email a soporte@kelgal.com 
						con tus datos (nombre, celular, categor&iacuteas, referencia, descripci&oacuten).
					</p>
					
			
				
				</div>
				<!-- end #content -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
</body>
</html>