<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Kahuu - Ofrecer Servicio</title>
</head>
<body>
  	<div class="page-bgtop">
  		<div class="tituloBox"><h2>Registro</h2></div>	
  		 <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
  		
  		<g:uploadForm name="publicarServicio" action="handlePublicarServicio" class="azul" useToken="true">
  			<div class="fieldcontain required">
				<label for="asunto">Nombre Completo</label><div class="required-indicator">*</div>
				<g:textField name="nombre"  onfocus="this.value=''" value="" />
			</div>
  		 		
  			<div class="fieldcontain">
				<label for="email"> <g:message code="user.email.label" default="Email" /></label><div class="required-indicator">*</div>
				<g:field type="email" name="email" value="" />
			</div>
							
			<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'categorias', 'error')} ">
				<label for="categorias">
					<g:message code="profile.categorias.label" default="Categorias" /><div class="required-indicator">*</div>
				</label>
				<g:select name="categoria1" from="${listaCategorias}" optionKey="nombre" optionValue="nombre" value="nombre" noSelection="['':'-Escoge una Categoria-']"/>
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'categorias', 'error')} ">
				<label for="categorias">
					<g:message code="profile.categorias.label" default="Categorias" />
				</label>
				<g:select name="categoria2" from="${listaCategorias}" optionKey="nombre" optionValue="nombre" value="nombre" noSelection="['':'-Escoge una Categoria-']"/>
			</div>
			
			<div class="fieldcontain required">
				<label for="otra">Otra Categor&iacute;a (En caso de que su trabajo no entre dentro de las categor&iacute;as listadas anteriormente)</label>
				<g:textField name="otraCategoria"  value="" />
			</div> 
		
			<div class="fieldcontain required">
				<label for="asunto">Celular</label><div class="required-indicator">*</div>
				<g:textField name="celular"  value="" />
			</div> 
			
			<div class="fieldcontain required">
				<label for="texto">Descipci&oacute;n</label><div class="required-indicator">*</div>
				<g:textArea name="descripcion"  value="Coloque aqui informacion de su actividad profesional como: \n - Trabajos que realiza \n - Barrios de la ciudad donde trabaja \n - Experiencia laboral \n - Horario de trabajo \n - Trabaja a domicilio o tiene su local?" />
			</div> 
			
			<div class="fieldcontain required">
				<label for="texto">Ciudad</label><div class="required-indicator">*</div>
				<g:select name="ciudad" from="${listaCiudades}" value="nombre" optionKey="nombre" optionValue="nombre" noSelection="['':'-Escoge una ciudad-']"/>	
			</div>
			
			<div class="fieldcontain required">
				<label for="texto">Adjunta imagen de tu cedula* (Documento escaneado, Max. 3 MB) La imagen ser&aacute; usada solo para fines administrativos</label><div class="required-indicator">*</div>
				 <input type="file" name="fotoCedula" id="fotoCedula"/>
			</div> 
			
			<div class="fieldcontain required">
				<label for="texto">Adjunta foto de perfil (Recomendamos utilizar foto tomada desde un celular, Max. 3 MB)</label>
				<input type="file" name="fotoPerfil" id="fotoPerfil" />
			</div> 
			
			<div class="fieldcontain">
				<g:checkBox name="agree"/>Acepto las <g:link controller="informacion" action="terminos">Pol&iacute;ticas de Privacidad</g:link> y los <g:link controller="informacion" action="privacidad">T&eacute;rminos y Condiciones</g:link> de Kahuu.
			</div>		
				
			<div class="buttons">
				<g:submitButton name="contactar"  value="Enviar" />
			</div>	
  		</g:uploadForm>	
  	</div>
</body>
</html>