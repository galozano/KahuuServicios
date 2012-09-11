<%@ page import="kelgal.empleos.Ciudad" %>



<div class="fieldcontain ${hasErrors(bean: ciudadInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="ciudad.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${ciudadInstance?.nombre}"/>
</div>

