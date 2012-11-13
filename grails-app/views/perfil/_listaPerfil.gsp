<g:each in="${listaPerfiles}" status="i" var="profileInstance">
<div class="post azul">
	<g:link action="profileUsuario" params="[usuario:profileInstance.usuario]"></g:link>
	<g:if test="${profileInstance?.image}">
		<g:link action="profileUsuario" params="[usuario:profileInstance.usuario]">
			<img src="${createLink(controller:'perfil', action:'darFoto', id: profileInstance.id)}" width="75" height="75"/>
		</g:link>
	</g:if>
	<g:else>
		<g:link action="profileUsuario" params="[usuario:profileInstance.usuario]">
			<img src="${resource(dir: 'images', file: 'none.jpg')}" width="75" height="75"/>
		</g:link>
	</g:else>
									
	<div class="title">
		<g:link action="profileUsuario" params="[usuario:profileInstance.usuario]">${fieldValue(bean: profileInstance, field: "nombre")}</g:link>
		<div class="descripcion">		
			<g:if test="${profileInstance.descripcion.length() > 90}">
				${profileInstance.descripcion.substring(0,89)}...
			</g:if>
			<g:else>
				${profileInstance.descripcion}
			</g:else>
		</div>
		<div class="rating">
			<img src="${resource(dir: 'images/skin', file: 'stars-'+profileInstance.totalRating+'.png')}"/><%-- &nbsp;(${profileInstance.reviews.size()} Comentarios)--%>
		</div>
	</div>
</div>
</g:each>