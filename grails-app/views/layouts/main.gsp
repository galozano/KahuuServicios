<g:applyLayout name="complete">
<html>
<head>
	<title><g:layoutTitle default="Kahuu Servicios" /></title>
	<g:layoutHead/>
</head>	
<body>
	
	<div id="userLogin">
		<g:if test="${session.user}">
			Hola, ${session.user.nombre} [<g:link controller="usuario" action="logout"> Cerrar Sesi&oacute;n</g:link> ]
		</g:if>
		<g:else>
			Hola Visitante, [<g:link controller="usuario" action="login">Ingresar/Registrar</g:link>]
		</g:else>
	</div>
	
	<div class="nav">
		<ul>
			<li><g:link class="home" controller="perfil" action="principal"><g:message code="default.home.label"/></g:link> </li> 
			<li><g:link class="comments" controller="comentarios" action="misComentarios">Mis Comentarios</g:link></li>
			<li><g:link class="contact" controller="informacion" action="contactenos">Cont&aacute;ctenos</g:link></li>		
			<li id="derecha">
				<div id="searchLargo">
					<g:form name="searchForm" url="[controller:'perfil',action:'buscar']">
						<div>
							<g:textField  class="input_buscador" name="buscador" value="busca tu servicio..." onfocus="if(this.value==this.defaultValue)this.value='';" onblur="if(this.value=='')this.value=this.defaultValue;" />
							<g:submitButton name="buscar"  value="Buscar"/>
						</div>
					</g:form>
				</div>
			</li>
		</ul>	
	</div>
	
	<div id="page">
		<g:layoutBody/>
	</div>
</body>
</html>
</g:applyLayout>