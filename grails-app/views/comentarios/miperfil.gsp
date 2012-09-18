<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Mi Perfil</title>
</head>
<body>
  <div class="body">
  	<div class="page-bgtop">
  		<div class="nav" role="navigation">
			<ul>
				<li><g:link action="misComentarios">Comentarios</g:link></li>
				<li><g:link action="miPerfil">Perfil</g:link></li>
			</ul>
		</div>
  	
  		<g:hasErrors bean="${userInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${userInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
			
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
				
  		<g:form action="handleActualizarUsuario">
			 <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'nombre', 'error')} ">
				<label for="nombre">
					<g:message code="user.nombre.label" default="Nombre" />
					
				</label>
				<g:textField name="nombre" value="${userInstance?.nombre}" size="40"/>
				
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="Actualizar" />
				</fieldset>
			</div>
		</g:form>
		
		<g:form action="handleCambiarPassword">
			<div class="fieldcontain ${hasErrors(bean: userPass, field: 'password', 'error')} required">
				<label for="password">Constrase&ntilde;a</label>
				<span class="required-indicator">*</span>
				<g:passwordField name="password" value="${userPass?.password}" />
			</div>
						
			<div class="fieldcontain">
				<label for="password">Confirmar Constrase&ntilde;a</label>
				<span class="required-indicator">*</span>
				<g:passwordField name="confirm" value="${userPass?.password}" />
			</div>
			
			<fieldset class="buttons">
				<g:submitButton name="create" class="save" value="Cambiar" />
			</fieldset>
		</g:form>
  	</div>
  </div>
</body>
</html>