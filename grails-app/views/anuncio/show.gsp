
<%@ page import="kahuu.anuncios.Anuncio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'anuncio.label', default: 'Anuncio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-anuncio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-anuncio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list anuncio">
			
				<g:if test="${anuncioInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="anuncio.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${anuncioInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="anuncio.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${anuncioInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.urlWebsite}">
				<li class="fieldcontain">
					<span id="urlWebsite-label" class="property-label"><g:message code="anuncio.urlWebsite.label" default="Url Website" /></span>
					
						<span class="property-value" aria-labelledby="urlWebsite-label"><g:fieldValue bean="${anuncioInstance}" field="urlWebsite"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.costoPClick}">
				<li class="fieldcontain">
					<span id="costoPClick-label" class="property-label"><g:message code="anuncio.costoPClick.label" default="Costo PC lick" /></span>
					
						<span class="property-value" aria-labelledby="costoPClick-label"><g:fieldValue bean="${anuncioInstance}" field="costoPClick"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="anuncio.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:formatBoolean boolean="${anuncioInstance?.estado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.fechaCreado}">
				<li class="fieldcontain">
					<span id="fechaCreado-label" class="property-label"><g:message code="anuncio.fechaCreado.label" default="Fecha Creado" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreado-label"><g:formatDate date="${anuncioInstance?.fechaCreado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.image}">
				<li class="fieldcontain">
					<span id="image-label" class="property-label"><g:message code="anuncio.image.label" default="Image" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.numeroClicks}">
				<li class="fieldcontain">
					<span id="numeroClicks-label" class="property-label"><g:message code="anuncio.numeroClicks.label" default="Numero Clicks" /></span>
					
						<span class="property-value" aria-labelledby="numeroClicks-label"><g:fieldValue bean="${anuncioInstance}" field="numeroClicks"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.numeroVistas}">
				<li class="fieldcontain">
					<span id="numeroVistas-label" class="property-label"><g:message code="anuncio.numeroVistas.label" default="Numero Vistas" /></span>
					
						<span class="property-value" aria-labelledby="numeroVistas-label"><g:fieldValue bean="${anuncioInstance}" field="numeroVistas"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.presupuesto}">
				<li class="fieldcontain">
					<span id="presupuesto-label" class="property-label"><g:message code="anuncio.presupuesto.label" default="Presupuesto" /></span>
					
						<span class="property-value" aria-labelledby="presupuesto-label"><g:fieldValue bean="${anuncioInstance}" field="presupuesto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="anuncio.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="user" action="show" id="${anuncioInstance?.usuario?.id}">${anuncioInstance?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${anuncioInstance?.vistas}">
				<li class="fieldcontain">
					<span id="vistas-label" class="property-label"><g:message code="anuncio.vistas.label" default="Vistas" /></span>
					
						<g:each in="${anuncioInstance.vistas}" var="v">
						<span class="property-value" aria-labelledby="vistas-label"><g:link controller="vista" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${anuncioInstance?.id}" />
					<g:link class="edit" action="edit" id="${anuncioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
