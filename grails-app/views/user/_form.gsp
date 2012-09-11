<%@ page import="kelgal.empleos.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'fechaCreado', 'error')} required">
	<label for="fechaCreado">
		<g:message code="user.fechaCreado.label" default="Fecha Creado" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreado" precision="day"  value="${userInstance?.fechaCreado}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="user.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${userInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${userInstance?.password}"/>
</div>

