<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main"/>
<title>Kahuu Servicios</title>
</head>
<body>
	<g:render template="sidebar"/>
	<div class="content">
					
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
		<div id="tabs">
			<ul>
		    	<li><a href='#tab-1'>${nombreCategoria}</a></li>
			</ul>
			
			<div id="tab-1">
				<g:each in="${profileInstanceList}" status="i" var="profileInstance">
					<div class="post">
						<g:if test="${profileInstance?.image}">
							<g:link action="profileUsuario" params="[usuario:profileInstance.usuario]"><img src="${createLink(controller:'perfil', action:'darFoto', id: profileInstance.id)}" width="75" height="75"/></g:link>
						</g:if>
						<g:else>
							<g:link action="profileUsuario" params="[usuario:profileInstance.usuario]"><img src="${resource(dir: 'images', file: 'none.jpg')}" width="75" height="75"/></g:link>
						</g:else>
									
						<div class="title">
							<g:link action="profileUsuario" params="[usuario:profileInstance.usuario]"> ${fieldValue(bean: profileInstance, field: "nombre")}</g:link>
							<div class="rating">
								<img src="${resource(dir: 'images/skin', file: 'stars-'+profileInstance.totalRating+'.png')}" /> &nbsp;(${profileInstance.reviews.size()} Comentarios)
							</div>
						</div>
		
						<div style="clear: both;">&nbsp;</div>
					</div>
				</g:each>
			</div>
			
		</div>
	</div>
	<!-- end #content -->
	<div style="clear: both;">&nbsp;</div>
</body>
</html>
