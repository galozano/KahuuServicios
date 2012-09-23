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
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
<g:layoutHead />
<r:layoutResources />
<ga:trackPageview />
</head>
<body>
	<div id="kahuuLogo" role="banner">
		<h2><a href="${createLink(uri: '/')}">Kahuu Servicios</a><span>beta</span></h2> 
	</div>
	
	<div id="userLogin">
		<g:if test="${session.user}">
			Hola, ${session.user.nombre} [<g:link controller="usuario" action="logout"> Cerrar Sesi&oacute;n</g:link> ]
		</g:if>
		<g:else>
			Hola Visitante, [<g:link controller="usuario" action="login"> Ingresar/Registrar </g:link>]
		</g:else>
	</div>
	
	<div class="nav">
		<ul>
			<li><g:link class="home" controller="perfil" action="users" id="1"><g:message code="default.home.label"/></g:link> </li> 
			<li><g:link class="comments" controller="comentarios" action="misComentarios">Mis Comentarios</g:link></li>
			<li><g:link class="contact" controller="informacion" action="contactenos">Cont&aacute;ctenos</g:link></li>		
			<li id="derecha">
				<div id="searchLargo">
					<g:form name="searchForm" url="[controller:'perfil',action:'buscar']">
						<div>
							<g:textField  class="input_buscador" name="buscador" value="busca tu servicio..." onfocus="if(this.value==this.defaultValue)this.value='';" onblur="if(this.value=='')this.value=this.defaultValue;" />
							<g:submitButton name="buscar"  value="Buscar"/>
						</div>
					</g:form>
				</div>
			</li>
		</ul>	
	</div>
	<g:layoutBody />
	<div class="footer" role="contentinfo">
		<div align="center">Usar este sitio web implica la aceptacion de los <g:link controller="informacion" action="terminos">T&eacute;rminos y Condiciones</g:link> y <g:link controller="informacion" action="privacidad">Politicas de Privacidad</g:link> de Kahuu Servicios</div>
		<div align="center">COPYRIGHT (C) 2012 SERVICIOSKAHUU.COM. TODOS LOS DERECHOS RESERVADOS</div>
	</div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />
</body>
</html>