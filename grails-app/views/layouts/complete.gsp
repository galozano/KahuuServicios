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
	<title><g:layoutTitle default="Kahuu Servicios" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Portal web para buscar servicios (electricista, plomeros, pintores, etc.) en Colombia" />
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/themes/smoothness/jquery-ui.css" type="text/css" media="all" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
	<g:layoutHead />
	<r:layoutResources/>
</head>
<body>
	<div id="kahuuLogo" role="banner">
		<h2><a href="${createLink(uri: '/')}">Kahuu Servicios</a><span>beta</span></h2> 
	</div>
	
	<g:layoutBody/>
	
	<div class="footer" role="contentinfo">
		<div align="center">Usar este sitio web implica la aceptacion de los <g:link controller="informacion" action="terminos">T&eacute;rminos y Condiciones</g:link> y <g:link controller="informacion" action="privacidad">Politicas de Privacidad</g:link> de Kahuu Servicios</div>
		<div align="center">COPYRIGHT &copy; 2012 KAHUUSERVICIOS.COM. TODOS LOS DERECHOS RESERVADOS</div>
	</div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />
</body>
</html>