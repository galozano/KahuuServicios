	
<div class="sidebar">
	<div class="box">
		<div class="tituloBox">Ciudades</div>	
		<ul>
			<g:each in="${ciudadesList}" var="ciudadInstance">
				<li>
					<g:if test="${ciudadInstance.nombre.equals(nombreCiudad)}">
						<div id="elegido"></div>
					</g:if>
					<g:link action="categoriasCiudad" params="[nombreCategoria: nombreCategoria,nombreCiudad:ciudadInstance.nombre]"> ${fieldValue(bean: ciudadInstance, field: "nombre")}</g:link>
				</li>
			</g:each>
		</ul>
	</div>
	<div class="box">
		<div class="tituloBox">Categor&iacute;as</div>
		<ul>
			<g:each in="${categoriasList}" var="categoriaInstance">
				<li>
					<g:if test="${categoriaInstance.nombre.equals(nombreCategoria)}">
						<div id="elegido"></div>
					</g:if>
					<g:link action="categoriasCiudad" params="[nombreCategoria: categoriaInstance.nombre,nombreCiudad:nombreCiudad]"> ${fieldValue(bean: categoriaInstance, field: "nombre")}</g:link>
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

