
<%@ page import="kahuu.anuncios.Anuncio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'anuncio.label', default: 'Anuncio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-anuncio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-anuncio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="costoPClick" title="${message(code: 'anuncio.costoPClick.label', default: 'Costo PC lick')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'anuncio.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'anuncio.estado.label', default: 'Estado')}" />
					
						<g:sortableColumn property="presupuesto" title="${message(code: 'anuncio.presupuesto.label', default: 'Presupuesto')}" />
					
						<g:sortableColumn property="titulo" title="${message(code: 'anuncio.titulo.label', default: 'Titulo')}" />
					
						<g:sortableColumn property="urlWebsite" title="${message(code: 'anuncio.urlWebsite.label', default: 'Url Website')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${anuncioInstanceList}" status="i" var="anuncioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${anuncioInstance.id}">${fieldValue(bean: anuncioInstance, field: "costoPClick")}</g:link></td>
					
						<td>${fieldValue(bean: anuncioInstance, field: "descripcion")}</td>
					
						<td><g:formatBoolean boolean="${anuncioInstance.estado}" /></td>
					
						<td>${fieldValue(bean: anuncioInstance, field: "presupuesto")}</td>
					
						<td>${fieldValue(bean: anuncioInstance, field: "titulo")}</td>
					
						<td>${fieldValue(bean: anuncioInstance, field: "urlWebsite")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${anuncioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
