<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title><g:message code="kahuu.kahuu" default="Kahuu" /></title>
</head>
<body>

<g:render template="sidebar"/>
<div class="content">
	<g:if test="${flash.message}">
		<div class="message" role="status">
			${flash.message}
		</div>
	</g:if>
	
	<div id="tabs">
		<div id="tituloBox">Destacados</div>
		<div id="tab-1">
			<g:render template="listaPerfil"  model="['listaPerfiles':listaDestacados]"/>
		</div>
	</div>
</div>
<!-- end #content -->
<div style="clear: both;">&nbsp;</div>
</body>
</html>
