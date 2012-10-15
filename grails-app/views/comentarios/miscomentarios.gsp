<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Mis Comentarios</title>
</head>
<body>
<div class="page-bgtop">
	<g:render template="navegacion"/>
		
	<g:hasErrors bean="${comentarioInstance}">
		<ul class="errors" role="alert">
			<g:eachError bean="${comentarioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
				
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
		
  	<div class="box-content">
	  	<g:form>
			<g:each in="${listaComentarios}" var="c">					
				<div class="review">
					<div id="header_review">
						<g:link action="deleteComentario" id="${c.id}" title="eliminar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><img src="${resource(dir: 'images/skin', file: 'delete.png')}" /></g:link>
						<g:link action="editarComentario" id="${c.id}" title="editar"><img src="${resource(dir: 'images/skin', file: 'edit.png')}" /></g:link>
						<img src="${resource(dir: 'images/skin', file: 'stars-'+c.rating+'.png')}" />
						&nbsp;&nbsp;${c.titulo}  &nbsp;&nbsp;${c.fechaCreado.format("dd MMM, yyyy")}  
						&nbsp;&nbsp; &nbsp;&nbsp; ${c.profile.nombre }
					</div>
					<div id="text_review">
						${c.texto}
					</div>			
				</div>
			</g:each>	
		</g:form>
	</div>	
	<g:paginate controller="comentarios" action="misComentarios" total="${totalComentarios}" />			
</div>	
</body>
</html>