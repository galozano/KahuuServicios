<%@ page import="kelgal.empleos.Categorias" %>



<div class="fieldcontain ${hasErrors(bean: categoriasInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="categorias.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${categoriasInstance?.nombre}"/>
</div>

