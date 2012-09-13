<%@ page import="kelgal.empleos.Certificado" %>



<div class="fieldcontain ${hasErrors(bean: certificadoInstance, field: 'nivel', 'error')} required">
	<label for="nivel">
		<g:message code="certificado.nivel.label" default="Nivel" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nivel" type="number" value="${certificadoInstance.nivel}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: certificadoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="certificado.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${certificadoInstance?.nombre}"/>
</div>

