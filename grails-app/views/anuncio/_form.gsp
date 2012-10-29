<%@ page import="kahuu.anuncios.Anuncio" %>



<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="anuncio.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="150" required="" value="${anuncioInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'titulo', 'error')} required">
	<label for="titulo">
		<g:message code="anuncio.titulo.label" default="Titulo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="titulo" maxlength="45" required="" value="${anuncioInstance?.titulo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'urlWebsite', 'error')} required">
	<label for="urlWebsite">
		<g:message code="anuncio.urlWebsite.label" default="Url Website" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="url" name="urlWebsite" required="" value="${anuncioInstance?.urlWebsite}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'costoPClick', 'error')} required">
	<label for="costoPClick">
		<g:message code="anuncio.costoPClick.label" default="Costo PC lick" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="costoPClick" value="${fieldValue(bean: anuncioInstance, field: 'costoPClick')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="anuncio.estado.label" default="Estado" />
		
	</label>
	<g:checkBox name="estado" value="${anuncioInstance?.estado}" />
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'fechaCreado', 'error')} required">
	<label for="fechaCreado">
		<g:message code="anuncio.fechaCreado.label" default="Fecha Creado" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreado" precision="day"  value="${anuncioInstance?.fechaCreado}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'image', 'error')} required">
	<label for="image">
		<g:message code="anuncio.image.label" default="Image" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="image" name="image" />
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'numeroClicks', 'error')} required">
	<label for="numeroClicks">
		<g:message code="anuncio.numeroClicks.label" default="Numero Clicks" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroClicks" type="number" value="${anuncioInstance.numeroClicks}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'numeroVistas', 'error')} required">
	<label for="numeroVistas">
		<g:message code="anuncio.numeroVistas.label" default="Numero Vistas" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroVistas" type="number" value="${anuncioInstance.numeroVistas}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'presupuesto', 'error')} required">
	<label for="presupuesto">
		<g:message code="anuncio.presupuesto.label" default="Presupuesto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="presupuesto" value="${fieldValue(bean: anuncioInstance, field: 'presupuesto')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="anuncio.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${kahuu.general.User.list()}" optionKey="id" required="" value="${anuncioInstance?.usuario?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: anuncioInstance, field: 'vistas', 'error')} ">
	<label for="vistas">
		<g:message code="anuncio.vistas.label" default="Vistas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${anuncioInstance?.vistas?}" var="v">
    <li><g:link controller="vista" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="vista" action="create" params="['anuncio.id': anuncioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'vista.label', default: 'Vista')])}</g:link>
</li>
</ul>

</div>

