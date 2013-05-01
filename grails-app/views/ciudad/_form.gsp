<%@ page import="kahuu.general.Ciudad" %>



<div class="fieldcontain ${hasErrors(bean: ciudadInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="ciudad.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${ciudadInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ciudadInstance, field: 'activado', 'error')} ">
	<label for="activado">
		<g:message code="ciudad.activado.label" default="Activado" />
		
	</label>
	<g:checkBox name="activado" value="${ciudadInstance?.activado}" />
</div>

