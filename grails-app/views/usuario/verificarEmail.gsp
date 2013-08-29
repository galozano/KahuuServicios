<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Kahuu - Verifiar Email</title>
</head>
<body>
  <div class="body">
  		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
  </div>
  
  <g:link controller="Perfil" action="principal">Regresar</g:link>
  
</body>
</html>