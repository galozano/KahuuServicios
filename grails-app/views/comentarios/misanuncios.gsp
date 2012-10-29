<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Mis Anuncios</title>
</head>
<body>
<div class="page-bgtop">
	<g:hasErrors bean="${anuncioInstance}">
		<ul class="errors" role="alert">
			<g:eachError bean="${anuncioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
				
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
		
	<h1>Mis Anuncios</h1>
		
  	<div class="box-content">
			<g:each in="${listaAnuncios}" var="anuncio">					
			 	<div id="box">
					 <ul>
						<li class="anuncio">
							<span id="titulo"><a>${anuncio.titulo}</a></span><br/>
							<span id="link">${anuncio.urlWebsite}</span> 
							
							<span id="linea">${anuncio.descripcion}</span>
							
							<div class="infoAnuncio">
								<div id="visitas">Veces Mostrado: ${anuncio.numeroVistas}</div>
								<div id="click">N&uacute;mero de Clicks: ${anuncio.numeroClicks}</div>
							</div>
						</li>
					</ul>
				</div>
		</g:each>	
		
		<g:link>Crear Anuncio</g:link>
	</div>			
</div>
</body>
</html>