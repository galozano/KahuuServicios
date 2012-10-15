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
		<g:form name="searchForm" url="[controller:'perfil',action:'buscar']">
			<div>
				<g:textField  class="input_buscador" name="buscador" value="busca tu servicio..." onfocus="if(this.value==this.defaultValue)this.value='';" onblur="if(this.value=='')this.value=this.defaultValue;" />
				<g:submitButton name="buscar"  value="Buscar"/>
			</div>
		</g:form>		
	</div>
</body>
</html>