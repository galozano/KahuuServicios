<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title>Login</title>
</head>
<body>
	<div class="body">
		<div id="page">
			<div id="sidebar">
				<h1>Categorias</h1>
				<ul>
					<g:each in="${categoriasList}" var="categoriaInstance">
						<li><g:link action="users" id="${categoriaInstance.id}">
								${fieldValue(bean: categoriaInstance, field: "nombre")}
							</g:link></li>
					</g:each>
				</ul>
			</div>		
			<div class="content">
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:form action="handleLogin">
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
						<label for="email"> <g:message code="user.email.label"
								default="Email" />
						</label>
						<g:field type="email" name="email" value="${userInstance?.email}" />
					</div>
	
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
						<label for="password"> <g:message code="user.password.label" default="Password" /></label>
						<g:passwordField name="password"  value="${userInstance?.password}" />
					</div>
					
					<g:submitButton name="create" class="save" value="Iniciar" />
				</g:form>
			</div>
		</div>
	</div>
</body>
</html>



