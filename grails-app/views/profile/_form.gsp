<%@ page import="kahuu.general.Profile" %>


<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'categorias', 'error')} ">
	<label for="categorias">
		<g:message code="profile.categorias.label" default="Categorias" />
		
	</label>
	<g:select name="categorias" from="${kahuu.general.Categorias.list()}" multiple="multiple" optionKey="id" size="5" optionValue="nombre" value="${profileInstance?.categorias*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'reviews', 'error')} ">
	<label for="reviews">
		<g:message code="profile.reviews.label" default="Reviews" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${profileInstance?.reviews?}" var="r">
    <li><g:link controller="review" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="review" action="create" params="['profile.id': profileInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'review.label', default: 'Review')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'tipoPerfil', 'error')} required">
	<label for="tipoPerfil">
		<g:message code="profile.tipoPerfil.label" default="Tipo Perfil" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoPerfil" name="tipoPerfil.id" from="${kahuu.general.TipoPerfil.list()}" optionValue="nombre" optionKey="id" required="" value="${profileInstance?.tipoPerfil?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="profile.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="usuario" required="" value="${profileInstance?.usuario}"/>
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

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="profile.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="45" required="" value="${profileInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'celular', 'error')} required">
	<label for="celular">
		<g:message code="profile.celular.label" default="Celular" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="celular" required="" value="${profileInstance?.celular}"/>
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
	<g:select id="certificado" name="certificado.id" from="${kahuu.general.Certificado.list()}" optionValue="nombre" optionKey="id" required="" value="${profileInstance?.certificado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'ciudad', 'error')} required">
	<label for="ciudad">
		<g:message code="profile.ciudad.label" default="Ciudad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ciudad" name="ciudad.id" from="${kahuu.general.Ciudad.list()}" optionKey="id" optionValue="nombre" required="" value="${profileInstance?.ciudad?.id}"  class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="profile.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${profileInstance?.descripcion}"/>
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
	<g:datePicker name="fechaCreado" precision="day"  value="${profileInstance?.fechaCreado}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="profile.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${profileInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'totalRating', 'error')} required">
	<label for="totalRating">
		<g:message code="profile.totalRating.label" default="Total Rating" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="totalRating" type="number" value="${profileInstance.totalRating}" required=""/>
</div>

