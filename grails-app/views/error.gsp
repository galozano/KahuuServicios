<!doctype html>
<html>
	<head>
		<title>Runtime Exception</title>
		<meta name="layout" content="main">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
	</head>
	<body>
		<g:if env="production">
			<p>Hubo un Error inesperado.</p>

			<p>Lo sentimos. Estamos trabajando para darle un mejor servicio.</p>
		</g:if>
		<g:else>		
			<g:renderException exception="${exception}"/>
		</g:else>
		
	</body>
</html>