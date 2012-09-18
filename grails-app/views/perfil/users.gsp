<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title>Kahuu Servicios</title>

</head>
<body>
	<div class="body">
		<div id="page">
				<div id="sidebar">
					<h1>Categor&iacuteas</h1>
					<ul>
						<g:each in="${categoriasList}" var="categoriaInstance">
							<li>
								<g:link action="users" id="${categoriaInstance.id}"> ${fieldValue(bean: categoriaInstance, field: "nombre")}</g:link>					
							</li>
						</g:each>
					</ul>
				</div>
				<div class="content">
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<g:each in="${profileInstanceList}" status="i" var="profileInstance">
						<div class="post">
							<g:if test="${profileInstance?.image}">
								<img src="${createLink(controller:'empleo', action:'darFoto', id: profileInstance.id)}" width="75" height="75"/>
							</g:if>
							<g:else>
								<img src="${resource(dir: 'images', file: 'none.jpg')}" width="75" height="75"/>
							</g:else>
							
							<div class="title">
								<g:link action="profile" id="${profileInstance.id}"> ${fieldValue(bean: profileInstance, field: "nombre")}</g:link>
								<div class="rating">
									<img src="${resource(dir: 'images/skin', file: 'stars-'+profileInstance.totalRating+'.png')}" /> &nbsp;(${profileInstance.reviews.size()} Comentarios)
								</div>
							</div>

							<div style="clear: both;">&nbsp;</div>
						</div>
					</g:each>
				</div>
				<!-- end #content -->
				<div style="clear: both;">&nbsp;</div>
			</div>
	</div>
</body>
</html>
