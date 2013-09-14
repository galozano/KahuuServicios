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
	<title><g:layoutTitle default="Kahuu-Guia de Profesional para el Hogar" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Portal web para buscar servicios (electricista, plomeros, pintores, etc.) en Colombia" />
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">	
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'colorbox.css')}" type="text/css">
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/themes/smoothness/jquery-ui.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.css')}" type="text/css">
	<link rel="image_src" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
	<script src="${resource(dir: 'js', file: 'jquery.colorbox-min.js')}" type="text/javascript"></script>
	<script src="${resource(dir: 'js', file: 'application.js')}" type="text/javascript"></script>
	
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" media="screen">
	<script src="${resource(dir: 'js', file: 'bootstrap.min.js')}" type="text/javascript"></script>
	<g:layoutHead />
	<r:layoutResources/>
	<ga:trackPageview/>
	<!--Start of Zopim Live Chat Script-->
	<%--<script type="text/javascript">
		window.$zopim||(function(d,s){var z=$zopim=function(c){z._.push(c)},$=z.s=
		d.createElement(s),e=d.getElementsByTagName(s)[0];z.set=function(o){z.set.
		_.push(o)};z._=[];z.set._=[];$.async=!0;$.setAttribute('charset','utf-8');
		$.src='//v2.zopim.com/?1OLCwksxjIgwwYz7HuNBu5qEonwbHNVB';z.t=+new Date;$.
		type='text/javascript';e.parentNode.insertBefore($,e)})(document,'script');
	</script>
	--%><!--End of Zopim Live Chat Script-->
</head>
<body>
	<facebook:initJS appId="${facebookContext.app.id}" xfbml="${true}" />

		<g:layoutBody/>

</body>
</html>