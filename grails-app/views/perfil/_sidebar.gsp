
<div id="sidebar">
	<h1>Categor&iacuteas</h1>
	<ul>
		<g:each in="${categoriasList}" var="categoriaInstance">
			<li><g:link action="categoria"
					params="[nombreCategoria: categoriaInstance.nombre]">
					${fieldValue(bean: categoriaInstance, field: "nombre")}
				</g:link></li>
		</g:each>
	</ul>
</div>