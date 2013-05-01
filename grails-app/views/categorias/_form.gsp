<%@ page import="kahuu.general.Categorias" %>



<div class="fieldcontain ${hasErrors(bean: categoriasInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="categorias.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${categoriasInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoriasInstance, field: 'activado', 'error')} ">
	<label for="activado">
		<g:message code="categorias.activado.label" default="Activado" />
		
	</label>
	<g:checkBox name="activado" value="${categoriasInstance?.activado}" />
</div>

