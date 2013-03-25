<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Buscar Comentario</title>
</head>
<body>
	<div class="page-bgtop">
		<g:render template="navegacion"/>
		
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

		<br/><br/><br/>
		<p>
			&iquest;C&oacute;mo escribir un comentario?
		</p>
		<ol>
			<li>1. Busca el servicio o persona que quieres buscar en la barra de abajo.</li>
			<li>2. Busca 'escribir comentario' en su perfil.</li>
			<li>3. Escribe el comentario y pon crear.</li>
		</ol>
		<br/><br/>
		
		<h1>Buscar</h1>
		
		<g:form name="searchForm" url="[action:'buscar',controller:'perfil']" class="azul">
			<div>
				<g:textField  class="input_buscador" name="q" value="busca tu servicio..." onfocus="if(this.value==this.defaultValue)this.value='';" onblur="if(this.value=='')this.value=this.defaultValue;" />
				<div class="buttons">
					<g:submitButton name="buscar"  value="Buscar"/>
				</div>
			</div>
		</g:form>		
	</div>
</body>
</html>