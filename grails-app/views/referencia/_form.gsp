<%@ page import="kelgal.empleos.Referencia" %>



<div class="fieldcontain ${hasErrors(bean: referenciaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="referencia.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${referenciaInstance?.nombre}"/>
</div>

