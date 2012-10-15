<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Olvido Clave</title>
</head>
<body>
  	<div class="page-bgtop">
  	
  		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
  	
  		<h2>Olvid&oacute; Clave</h2>
  		<g:form action="handleOlvideClave">
  			<div class="fieldcontain  required">
				<label for="email"> <g:message code="user.email.label" default="Email" /></label>
				<span class="required-indicator">*</span>
				<g:field type="email" name="email" value="" />
			</div>
			
			<fieldset class="buttons">
				<g:submitButton name="create" class="save" value="Enviar" />
			</fieldset>
  		</g:form>
  		
  	</div>
</body>
</html>