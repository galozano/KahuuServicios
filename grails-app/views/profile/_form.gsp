<%@ page import="kelgal.empleos.Profile" %>



<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'categorias', 'error')} ">
	<label for="categorias">
		<g:message code="profile.categorias.label" default="Categorias" />
		
	</label>
	<g:select name="categorias" from="${kelgal.empleos.Categorias.list()}" multiple="multiple" optionKey="id"  optionValue="nombre" size="5" value="${profileInstance?.categorias*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'usuario', 'error')} ">
	<label for="usuario">
		<g:message code="profile.usuario.label" default="Usuario" />
		
	</label>
	<g:textField name="usuario" value="${profileInstance?.usuario}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'image', 'error')} required">
	<label for="image">
		<g:message code="profile.image.label" default="Image" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="image" name="image" />
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="profile.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${profileInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'celular', 'error')} ">
	<label for="celular">
		<g:message code="profile.celular.label" default="Celular" />
		
	</label>
	<g:textField name="celular" value="${profileInstance?.celular}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'celular2', 'error')} ">
	<label for="celular2">
		<g:message code="profile.celular2.label" default="Celular2" />
		
	</label>
	<g:textField name="celular2" value="${profileInstance?.celular2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'certificado', 'error')} required">
	<label for="certificado">
		<g:message code="profile.certificado.label" default="Certificado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="certificado" name="certificado.id" from="${kelgal.empleos.Certificado.list()}" optionKey="id" optionValue="nombre" required="" value="${profileInstance?.certificado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'ciudad', 'error')} required">
	<label for="ciudad">
		<g:message code="profile.ciudad.label" default="Ciudad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ciudad" name="ciudad.id" from="${kelgal.empleos.Ciudad.list()}" optionKey="id" optionValue="nombre" required="" value="${profileInstance?.ciudad?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="profile.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textArea name="descripcion" value="${profileInstance?.descripcion}" rows="10" cols="40" ></g:textArea>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'estadoUsuario', 'error')} ">
	<label for="estadoUsuario">
		<g:message code="profile.estadoUsuario.label" default="Estado Usuario" />
		
	</label>
	<g:checkBox name="estadoUsuario" value="${profileInstance?.estadoUsuario}" />
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'fechaCreado', 'error')} required">
	<label for="fechaCreado">
		<g:message code="profile.fechaCreado.label" default="Fecha Creado" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreado" precision="minute"  value="${profileInstance?.fechaCreado}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="profile.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${profileInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="profile.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${profileInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'referencias', 'error')} ">
	<label for="referencias">
		<g:message code="profile.referencias.label" default="Referencias" />
		
	</label>
	<g:select name="referencias" from="${kelgal.empleos.Referencia.list()}" multiple="multiple" optionValue="nombre" optionKey="id" size="5" value="${profileInstance?.referencias*.id}" class="many-to-many"/>
</div>

