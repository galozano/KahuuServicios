<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title>Ingresar</title>
</head>
<body>
	<div class="body">
		<div id="page">
			<div class="page-bgtop">
			
				<g:hasErrors bean="${userInstance}">
					<ul class="errors" role="alert">
						<g:eachError bean="${userInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
						</g:eachError>
					</ul>
				</g:hasErrors>
				
				<g:hasErrors bean="${userRegist}">
					<ul class="errors" role="alert">
						<g:eachError bean="${userRegist}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
						</g:eachError>
					</ul>
				</g:hasErrors>
			
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				
				<div id="cuadroIzq">
					<div><h2>Login</h2></div>
					<g:form action="handleLogin">
						<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
							<label for="email"> <g:message code="user.email.label" default="Email" /></label>
							<span class="required-indicator">*</span>
							<g:field type="email" name="email" value="${userInstance?.email}" />
						</div>
		
						<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
							<label for="password">Constrase&ntilde;a</label>
							<span class="required-indicator">*</span>
							<g:passwordField name="password"  value="${userInstance?.password}" />
						</div> 
						
						<div class="olvide">
							<g:link action="olvideClave">Olvid&eacute; mi clave</g:link>
						</div>
						
						<div class="buttons">
							<g:submitButton name="create" class="save" value="Iniciar" />
						</div>
					</g:form>
				</div>
				<div id="cuadroDer">
					<div><h2>Inscr&iacute;bete</h2></div>
					<g:form action="handleRegistration">
						<div class="fieldcontain ${hasErrors(bean: userRegist, field: 'nombre', 'error')} required">
							<label for="nombre"> <g:message code="user.nombre.label" default="Nombre" /></label>
							<span class="required-indicator">*</span>
							<g:textField name="nombre" value="${userRegist?.nombre}" />
						</div>
						
						<div class="fieldcontain ${hasErrors(bean: userRegist, field: 'email', 'error')} required">
							<label for="email"> <g:message code="user.email.label" default="Email" /></label>
							<span class="required-indicator">*</span>
							<g:field type="email" name="email" value="${userRegist?.email}" />
						</div>
	
						<div class="fieldcontain ${hasErrors(bean: userRegist, field: 'password', 'error')} required">
							<label for="password">Constrase&ntilde;a</label>
							<span class="required-indicator">*</span>
							<g:passwordField name="password" value="${userRegist?.password}" />
						</div>
						
						<div class="fieldcontain">
							<label for="password">Confirmar Constrase&ntilde;a</label>
							<span class="required-indicator">*</span>
							<g:passwordField name="confirm" value="${userRegist?.password}" />
						</div>
						
						<div class="fieldcontain">
							<g:checkBox name="agree"/> Acepto las <g:link controller="informacion" action="terminos">Pol&iacute;ticas de Privacidad</g:link> y los <g:link controller="informacion" action="privacidad">T&eacute;rminos y Condiciones</g:link> de Kahuu Servicios.
						</div>
						
						<fieldset class="buttons">
							<g:submitButton name="create" class="save" value="Crear Cuenta" />
						</fieldset>
					</g:form>			
				</div>
			</div>
		</div>
	</div>
</body>
</html>



