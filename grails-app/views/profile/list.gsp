
<%@ page import="kelgal.empleos.Profile" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-profile" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="usuario" title="${message(code: 'profile.usuario.label', default: 'Usuario')}" />
					
						<g:sortableColumn property="image" title="${message(code: 'profile.image.label', default: 'Image')}" />
					
						<g:sortableColumn property="celular" title="${message(code: 'profile.celular.label', default: 'Celular')}" />
					
						<th><g:message code="profile.ciudad.label" default="Ciudad" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'profile.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="estadoUsuario" title="${message(code: 'profile.estadoUsuario.label', default: 'Estado Usuario')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${profileInstanceList}" status="i" var="profileInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${profileInstance.id}">${fieldValue(bean: profileInstance, field: "usuario")}</g:link></td>
					
						<td>${fieldValue(bean: profileInstance, field: "image")}</td>
					
						<td>${fieldValue(bean: profileInstance, field: "celular")}</td>
					
						<td>${fieldValue(bean: profileInstance, field: "ciudad")}</td>
					
						<td>${fieldValue(bean: profileInstance, field: "descripcion")}</td>
					
						<td><g:formatBoolean boolean="${profileInstance.estadoUsuario}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${profileInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
