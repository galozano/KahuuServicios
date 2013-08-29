<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Kahuu - Contactenos</title>
</head>
<body>
  	<div class="page-bgtop">
  		<div class="tituloBox"><h2>Cont&aacute;ctenos</h2></div>	
  		 <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
  		
  		<div class="azul">
	  		<g:form action="handleContactenos" class="info">
	  			
	  			<div class="fieldcontain required">
					<label for="asunto">Nombre</label>
					<g:textField name="nombre"  value="" />
				</div>
	  		
	  			<div class="fieldcontain  required">
					<label for="email"> <g:message code="user.email.label" default="Email" /></label>
					<g:field type="email" name="email" value="" />
				</div>
						
				<div class="fieldcontain required">
					<label for="asunto">Asunto</label>
					<g:textField name="asunto"  value="" />
				</div> 
				
				<div class="fieldcontain required">
					<label for="texto">Texto</label>
					<g:textArea name="texto"  value="" />
				</div> 
							
				<div class="buttons">
					<g:submitButton name="contactar"  value="Enviar" />
				</div>
	  		</g:form>
  		</div>	
  	</div>
</body>
</html>