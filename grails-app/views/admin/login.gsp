<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="complete" />
<title>Login</title>
</head>
<body>
	<div class="body">
		<div id="page">	
			<div class="index-content">
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:form action="handleLogin">
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'login', 'error')} required">
						<label for="login"> Login: </label><span class="required-indicator">*</span>
						<g:field type="login" name="login" value="${userInstance?.login}" />
					</div>
	
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
						<label for="password"><g:message code="user.password.label" default="Password" /></label>
						<span class="required-indicator">*</span>
						<g:passwordField name="password"  value="${userInstance?.password}" />
					</div>
					
					<g:submitButton name="create" class="save" value="Iniciar" />
				</g:form>
			</div>
		</div>
	</div>
</body>
</html>



