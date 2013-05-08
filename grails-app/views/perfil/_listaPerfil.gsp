<g:each in="${listaPerfiles}" status="i" var="profileInstance">
<div class="post azul">
	<g:link action="profileUsuario" params="[usuario:profileInstance.usuario,idCiudad:idCiudad]"></g:link>
	<g:if test="${profileInstance?.image}">
		<g:link action="profileUsuario" params="[usuario:profileInstance.usuario,idCiudad:idCiudad]">
			<img src="${createLink(controller:'perfil', action:'darFoto', id: profileInstance.id)}" width="75" height="75"/>
		</g:link>
	</g:if>
	<g:else>
		<g:link action="profileUsuario" params="[usuario:profileInstance.usuario,idCiudad:idCiudad]">
			<img src="${resource(dir: 'images', file: 'none.jpg')}" width="75" height="75"/>
		</g:link>
	</g:else>
							
	<div class="title">
		<div class="rating">
			<img src="${resource(dir: 'images/skin', file: 'stars-'+profileInstance.totalRating+'.png')}"/><%-- &nbsp;(${profileInstance.reviews.size()} Comentarios)--%>
		</div>
		<g:link action="profileUsuario" params="[usuario:profileInstance.usuario,idCiudad:idCiudad]">${fieldValue(bean: profileInstance, field: "nombre")}</g:link>
		<div class="descripcion">		
			<g:if test="${profileInstance.descripcion.length() > 90}">
				${profileInstance.descripcion.substring(0,70)}...
			</g:if>
			<g:else>
				${profileInstance.descripcion}
			</g:else>
		</div>
		<div id="textRecomendados${profileInstance.id}" class="textoRecomendacion"></div>
		<script type="text/javascript">
				$.ajax(
				{
				  url: "${g.createLink(controller:'perfil',action:'darAmigosRecomendaron')}",
				  data: {idPerfil: "${profileInstance.id}"},
				  context: document.body
				}).done(function(data) { 
				  $("#textRecomendados${profileInstance.id}").html(data);
				  $(".inline").colorbox({inline:true, width:"30%"});
				});
		</script>
		</div>
</div>
</g:each>