
<div class="sidebar">
	<div class="box">
		<div id="tituloBox">Categor&iacute;as</div>
		<ul>
			<g:each in="${categoriasList}" var="categoriaInstance">
				<li>
					<g:if test="${categoriaInstance.nombre.equals(nombreCategoria)}">
						<div id="elegido"></div>
					</g:if>
					<g:link action="categoria" params="[nombreCategoria: categoriaInstance.nombre]"> ${fieldValue(bean: categoriaInstance, field: "nombre")}</g:link>
				</li>
			</g:each>
		</ul>
	</div>
	
	<div class="box">
		<div id="tituloBox">Ciudades</div>	
		<ul>
			<li><div id="elegido"></div>Cartagena</li>
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

