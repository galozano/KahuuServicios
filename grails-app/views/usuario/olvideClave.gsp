<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Kahuu - Olvido Clave</title>
</head>
<body>
  	<div class="page-bgtop">
  	
  		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
  	
  		<h2>Olvid&oacute; Clave</h2>
  		<g:form action="handleOlvideClave" class="azul">
  			<div class="fieldcontain  required">
				<label for="email"> <g:message code="user.email.label" default="Email" /></label>
				<g:field type="email" name="email" value="" />
			</div>
			
			<div class="buttons">
				<g:submitButton name="create" class="save" value="Recuperar" />
			</div>
  		</g:form>
  		
  	</div>
</body>
</html>