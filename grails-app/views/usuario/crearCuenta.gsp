<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title>Registro-Kahuu Servicios</title>
</head>
<body>
	<div class="body">
		<div id="page">		
			<div id="registrar" class="cuadro">
				<h1>Incr&iacute;bete</h1>
				<g:form action="handleRegistration">
				
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'nombre', 'error')} ">
						<label for="nombre"> <g:message code="user.nombre.label" default="Nombre" /></label>
						<g:textField name="nombre" value="${userInstance?.nombre}" />
					</div>
					
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
						<label for="email"> <g:message code="user.email.label" default="Email" /></label>
						<g:field type="email" name="email" value="${userInstance?.email}" />
					</div>

					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
						<label for="password"> <g:message code="user.password.label" default="Password" /></label>
						<g:textField name="password" value="${userInstance?.password}" />
					</div>
					
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'confirm', 'error')} ">
						<label for="password">Confirmas Constrase&ntilde;a</label>
						<g:textField name="confirm" value="${userInstance?.password}" />
					</div>
					
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'agree', 'error')} ">
						<g:checkBox name="agree" /> Acepto las Pol&iacute;ticas de Privacidad y los T&eacute;rminos y Condiciones de Kahuu Servicios.
					</div>
					
					<g:submitButton name="create" class="save" value="Crear Cuenta" />
				</g:form>
			</div>
		</div>
	</div>
</body>
</html>