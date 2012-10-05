<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Comentario</title>
</head>
<body>
	<div id="page">
		<div class="page-bgtop">
			<g:hasErrors bean="${comentarioInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${comentarioInstance}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
					</g:eachError>
				</ul>
			</g:hasErrors>

			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>

			<g:link controller="perfil" action="profile" id="${profileInstance?.id}">Atras</g:link>

			<h2>Escribe tu Comentario-${profileInstance?.nombre }</h2>

			<g:form action="handleComentario" useToken="true">
				<g:hiddenField name="perfilId" value="${profileInstance?.id}" />

				<div class="fieldcontain ${hasErrors(bean: comentarioInstance, field: 'rating', 'error')} required">
					<label for="Rating">Calificaci&oacute;n: Malo </label>
						<g:radioGroup name="rating" values="[1,2,3,4,5]" value="1">
							&nbsp;${it.radio}&nbsp;
						</g:radioGroup>
						Bueno
				</div>

				<div class="fieldcontain ${hasErrors(bean: comentarioInstance, field: 'titulo', 'error')} required">
					<label for="Titulo">Titulo: </label>
					<g:textField name="titulo" value="${comentarioInstance?.titulo}" />
				</div>

				<div class="fieldcontain ${hasErrors(bean: comentarioInstance, field: 'texto', 'error')} required">
					<label for="Rating">Comentario: </label>
					<g:textArea name="texto" escapeHtml="false" value="${comentarioInstance?.texto}" />
				</div>

				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="Crear" />
				</fieldset>
			</g:form>
		</div>
	</div>
</body>
</html>