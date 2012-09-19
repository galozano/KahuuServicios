<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Contactenos</title>
</head>
<body>
  <div class="page">
  	<div class="page-bgtop">
  	
  		 <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
  		<h2>Cont&aacute;ctenos</h2>
  		
  		<g:form action="handleContactenos">
  			<div class="fieldcontain  required">
				<label for="email"> <g:message code="user.email.label" default="Email" /></label>
				<span class="required-indicator">*</span>
				<g:field type="email" name="email" value="" />
			</div>
		
			<div class="fieldcontain required">
				<label for="asunto">Asunto</label>
				<span class="required-indicator">*</span>
				<g:textField name="asunto"  value="${userInstance?.password}" />
			</div> 
			
			<div class="fieldcontain required">
				<label for="texto">Texto</label>
				<span class="required-indicator">*</span>
				<g:textArea name="texto"  value="${userInstance?.password}" />
			</div> 
						
						
			<fieldset class="buttons">
				<g:submitButton name="contactar"  value="Enviar" />
			</fieldset>
  		
  		</g:form>	
  	</div>
  </div>
</body>
</html>