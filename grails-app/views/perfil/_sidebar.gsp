	
<div class="sidebar">
	<div class="box">
		<div class="tituloBox">Ciudades</div>	
		<ul>
			<g:each in="${ciudadesList}" var="ciudadInstance">
				<li>
					<g:if test="${ciudadInstance.id == idCiudad}">
						<div id="elegido"></div>
					</g:if>
					<g:link action="categoriasCiudadId" params="[idCategoria: idCategoria,idCiudad:ciudadInstance.id]"> ${fieldValue(bean: ciudadInstance, field: "nombre")}</g:link>
				</li>
			</g:each>
		</ul>
	</div>
	<div class="box">
		<div class="tituloBox">Categor&iacute;as</div>
		<ul>
			<g:each in="${categoriasList}" var="categoriaInstance">
				<li>
					<g:if test="${categoriaInstance.id == idCategoria}">
						<div id="elegido"></div>
					</g:if>
					<g:link action="categoriasCiudadId" params="[idCategoria: categoriaInstance.id,idCiudad:idCiudad]"> ${fieldValue(bean: categoriaInstance, field: "nombre")}</g:link>
				</li>
			</g:each>
		</ul>
	</div>
		
	<div id="anun"></div>
	<%--<script type="text/javascript">
		$.ajax(
		{
		  url: "${g.createLink(controller:'controlAnuncio',action:'anuncios')}",
		  context: document.body
		}).done(function(data) { 
		  $("#anun").html(data)
		});

	</script>
--%></div>

