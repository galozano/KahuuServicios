<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main"/>
<title>Kahuu - ${categoria?.nombre}</title>
<meta name="description" content="${categoria?.descripcion}" />
</head>
<body>
	<facebook:initJS appId="${facebookContext.app.id}" xfbml="${true}" />
	<g:render template="sidebar"/>
	<div class="content">
					
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
		<div id="tabs">
			<div class="tituloBox">${categoria?.nombre}</div>
			<div class="descripcionBox">${categoria?.descripcion}</div>
			<div id="tab-1">
				<g:render template="listaPerfil"  model="['listaPerfiles':profileInstanceList]"/>
			</div>
				
		</div>
	</div>
	<!-- end #content -->
	<div style="clear: both;">&nbsp;</div>
</body>
</html>
