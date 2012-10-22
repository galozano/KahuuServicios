
<div class="sidebar">
	<div id="box">
		<div id="tituloBox">Categor&iacute;as</div>
		<ul>
			<g:each in="${categoriasList}" var="categoriaInstance">
				<li><g:link action="categoria"
						params="[nombreCategoria: categoriaInstance.nombre]">
						${fieldValue(bean: categoriaInstance, field: "nombre")}
					</g:link></li>
			</g:each>
		</ul>
	</div>
		
	<div id="anun"></div>
	<script type="text/javascript">
		$.ajax(
		{
		  url: "${g.createLink(controller:'controlAnuncio',action:'anuncios')}",
		  context: document.body
		}).done(function(data) { 
		  $("#anun").html(data)
		});

	</script>
</div>

