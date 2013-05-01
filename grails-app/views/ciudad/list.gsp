
<%@ page import="kahuu.general.Ciudad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ciudad.label', default: 'Ciudad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ciudad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ciudad" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'ciudad.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="activado" title="${message(code: 'ciudad.activado.label', default: 'Activado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ciudadInstanceList}" status="i" var="ciudadInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ciudadInstance.id}">${fieldValue(bean: ciudadInstance, field: "nombre")}</g:link></td>
					
						<td><g:formatBoolean boolean="${ciudadInstance.activado}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ciudadInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
