<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Ofrecer Servicio</title>
</head>
<body>
  	<div class="page-bgtop">
  		<div class="tituloBox"><h2>Registro</h2></div>	
  		 <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
  		
  		<g:form action="handlePublicarServicio" class="azul" useToken="true">
  			<div class="fieldcontain required">
				<label for="asunto">Nombre Completo</label>
				<g:textField name="nombre"  value="" />
			</div>
  		 		
  			<div class="fieldcontain">
				<label for="email"> <g:message code="user.email.label" default="Email" /></label>
				<g:field type="email" name="email" value="" />
			</div>
							
			<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'categorias', 'error')} ">
				<label for="categorias">
					<g:message code="profile.categorias.label" default="Categorias" />
				</label>
				<g:select name="categorias" from="${kahuu.general.Categorias.list()}" multiple="multiple" optionKey="nombre" optionValue="nombre" size="5" value="nombre" class="many-to-many"/>
			</div>
		
			<div class="fieldcontain required">
				<label for="asunto">Celular</label>
				<g:textField name="celular"  value="" />
			</div> 
			
			<div class="fieldcontain required">
				<label for="texto">Descipci&oacute;n</label>
				<g:textArea name="descripcion"  value="" />
			</div> 
			
			<div class="fieldcontain required">
				<label for="texto">Ciudad</label>
				<g:select name="ciudad" from="${['Bogota','Cartagena']}" value="${ciudad}" noSelection="['':'-Escoge una ciudad-']"/>	
			</div> 
			
				
			<div class="fieldcontain">
				<g:checkBox name="agree"/>Acepto las <g:link controller="informacion" action="terminos">Pol&iacute;ticas de Privacidad</g:link> y los <g:link controller="informacion" action="privacidad">T&eacute;rminos y Condiciones</g:link> de Kahuu.
			</div>		
				
			<div class="buttons">
				<g:submitButton name="contactar"  value="Enviar" />
			</div>	
  		</g:form>	
  	</div>
</body>
</html>