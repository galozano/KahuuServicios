
<%@ page import="kahuu.general.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-profile" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list profile">
			
				<g:if test="${profileInstance?.categorias}">
				<li class="fieldcontain">
					<span id="categorias-label" class="property-label"><g:message code="profile.categorias.label" default="Categorias" /></span>
					
						<g:each in="${profileInstance.categorias}" var="c">
						<span class="property-value" aria-labelledby="categorias-label"><g:link controller="categorias" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.reviews}">
				<li class="fieldcontain">
					<span id="reviews-label" class="property-label"><g:message code="profile.reviews.label" default="Reviews" /></span>
					
						<g:each in="${profileInstance.reviews}" var="r">
						<span class="property-value" aria-labelledby="reviews-label"><g:link controller="review" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.tipoPerfil}">
				<li class="fieldcontain">
					<span id="tipoPerfil-label" class="property-label"><g:message code="profile.tipoPerfil.label" default="Tipo Perfil" /></span>
					
						<span class="property-value" aria-labelledby="tipoPerfil-label"><g:link controller="tipoPerfil" action="show" id="${profileInstance?.tipoPerfil?.id}">${profileInstance?.tipoPerfil?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="profile.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:fieldValue bean="${profileInstance}" field="usuario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.image}">
				<li class="fieldcontain">
					<span id="image-label" class="property-label"><g:message code="profile.image.label" default="Image" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="profile.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${profileInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="profile.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${profileInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.celular}">
				<li class="fieldcontain">
					<span id="celular-label" class="property-label"><g:message code="profile.celular.label" default="Celular" /></span>
					
						<span class="property-value" aria-labelledby="celular-label"><g:fieldValue bean="${profileInstance}" field="celular"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.celular2}">
				<li class="fieldcontain">
					<span id="celular2-label" class="property-label"><g:message code="profile.celular2.label" default="Celular2" /></span>
					
						<span class="property-value" aria-labelledby="celular2-label"><g:fieldValue bean="${profileInstance}" field="celular2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.certificado}">
				<li class="fieldcontain">
					<span id="certificado-label" class="property-label"><g:message code="profile.certificado.label" default="Certificado" /></span>
					
						<span class="property-value" aria-labelledby="certificado-label"><g:link controller="certificado" action="show" id="${profileInstance?.certificado?.id}">${profileInstance?.certificado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.ciudad}">
				<li class="fieldcontain">
					<span id="ciudad-label" class="property-label"><g:message code="profile.ciudad.label" default="Ciudad" /></span>
					
						<span class="property-value" aria-labelledby="ciudad-label"><g:link controller="ciudad" action="show" id="${profileInstance?.ciudad?.id}">${profileInstance?.ciudad?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="profile.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${profileInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.estadoUsuario}">
				<li class="fieldcontain">
					<span id="estadoUsuario-label" class="property-label"><g:message code="profile.estadoUsuario.label" default="Estado Usuario" /></span>
					
						<span class="property-value" aria-labelledby="estadoUsuario-label"><g:formatBoolean boolean="${profileInstance?.estadoUsuario}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.fechaCreado}">
				<li class="fieldcontain">
					<span id="fechaCreado-label" class="property-label"><g:message code="profile.fechaCreado.label" default="Fecha Creado" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreado-label"><g:formatDate date="${profileInstance?.fechaCreado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="profile.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${profileInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.totalRating}">
				<li class="fieldcontain">
					<span id="totalRating-label" class="property-label"><g:message code="profile.totalRating.label" default="Total Rating" /></span>
					
						<span class="property-value" aria-labelledby="totalRating-label"><g:fieldValue bean="${profileInstance}" field="totalRating"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${profileInstance?.id}" />
					<g:link class="edit" action="edit" id="${profileInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
