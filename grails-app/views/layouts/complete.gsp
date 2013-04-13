<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="es" class="no-js">
<!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="Kahuu-Guia Profesional Para El Hogar" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Portal web para buscar servicios (electricista, plomeros, pintores, etc.) en Colombia" />
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'colorbox.css')}" type="text/css">
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/themes/smoothness/jquery-ui.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.css')}" type="text/css">
	<link rel="image_src" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
	<script src="${resource(dir: 'js', file: 'jquery.colorbox-min.js')}"></script>
	<script src="${resource(dir: 'js', file: 'application.js')}" type="text/javascript"></script>
	<g:layoutHead />
	<r:layoutResources/>
	<ga:trackPageview/>
</head>
<body>
	<div class="wrapper">
	<div class="header">
		<div class="nav">
			<ul>
				<li><g:link class="home" controller="perfil" action="principal"><g:message code="default.home.label"/></g:link> </li> 
				<li><g:link class="comments" controller="comentarios" action="misComentarios">Mis Comentarios</g:link></li>	
				<li><g:link class="quees" controller="informacion" action="comoFunciona">C&oacute;mo funciona?</g:link></li>
				<li><g:link class="publicar" controller="informacion" action="publicarServicio">P&uacute;blica tu Servicio</g:link></li>
				<li><g:link class="contact" controller="informacion" action="contactenos">Cont&aacute;ctenos</g:link></li>
				<li id="derecha">
					<g:if test="${session.user}">
						<g:link controller="usuario" action="logout">Cerrar Sesi&oacute;n</g:link>
					</g:if>
					<g:else>
						<g:link controller="usuario" action="login">Ingresar/Registrar</g:link>
					</g:else>
				</li>
				
			</ul>	
		</div>
		<div class="subheader">
			<div class="kahuuLogo" role="banner">
				<a href="${createLink(uri: '/')}"><img src="${resource(dir: 'images', file: 'logoKahuu.png')}" /></a>
			</div>
			<div class="searchLargo">
				<g:form name="searchForm" url="[controller:'perfil',action:'buscar']" method="get">
					<div>
						<g:textField  class="input_buscador" name="q" value="Estas buscando por un..." onfocus="if(this.value==this.defaultValue)this.value='';" onblur="if(this.value=='')this.value=this.defaultValue;" />
						<g:submitButton name="buscar" value="" />
					</div>
				</g:form>
			</div>
		</div>
	</div>	
	
	<g:layoutBody/>
	
	<div class="footer" role="contentinfo">
		<div align="center">Usar este sitio web implica la aceptacion de los <g:link controller="informacion" action="terminos">T&eacute;rminos y Condiciones</g:link> y <g:link controller="informacion" action="privacidad">Politicas de Privacidad</g:link> de Kahuu Servicios</div>
		<div align="center">COPYRIGHT &copy; 2013 KAHUU.CO. TODOS LOS DERECHOS RESERVADOS</div>
	</div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />
	</div>
</body>
</html>