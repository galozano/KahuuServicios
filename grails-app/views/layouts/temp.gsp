<div class="wrapper">
	<div class="header">
		<div class="nav">
			<ul>
				<li><g:link class="home" controller="perfil" action="principal"><g:message code="default.home.label"/></g:link> </li> 
				<li><g:link class="comments" controller="comentarios" action="misComentarios">Mis Comentarios</g:link></li>	
				<li><g:link class="quees" controller="informacion" action="comoFunciona">C&oacute;mo funciona?</g:link></li>
				<li><g:link class="publicar" controller="informacion" action="publicarServicio">P&uacute;blica tu Servicio</g:link></li>
				<li><g:link class="contact" controller="informacion" action="contactenos">Cont&aacute;ctenos</g:link></li>
				<li id="derecha">
					<g:if test="${session.user}">
						<g:link controller="usuario" action="logout">Cerrar Sesi&oacute;n</g:link>
					</g:if>
					<g:else>
						<g:link controller="usuario" action="login">Ingresar/Registrar</g:link>
					</g:else>
				</li>
				
			</ul>	
		</div>
		<div class="subheader">
			<div class="kahuuLogo" role="banner">
				<a href="${createLink(uri: '/')}"><img src="${resource(dir: 'images', file: 'logoKahuu.png')}" /></a>
			</div>
			<div class="searchLargo">
				<g:form name="searchForm" url="[controller:'perfil',action:'buscar']" method="get">
					<div>
						<g:textField  class="input_buscador" name="q" value="Estas buscando por un..." onfocus="if(this.value==this.defaultValue)this.value='';" onblur="if(this.value=='')this.value=this.defaultValue;" />
						<input type="hidden" name="ciudad" value="${idCiudad}">
						<g:submitButton name="buscar" value=""/>
					</div>
				</g:form>
			</div>
		</div>
	</div>	
	
	<g:layoutBody/>
	
	<div class="footer" role="contentinfo">
		<div align="center">Usar este sitio web implica la aceptacion de los <g:link controller="informacion" action="terminos">T&eacute;rminos y Condiciones</g:link> y <g:link controller="informacion" action="privacidad">Politicas de Privacidad</g:link> de Kahuu Servicios</div>
		<div align="center">COPYRIGHT &copy; 2013 KAHUU.CO. TODOS LOS DERECHOS RESERVADOS</div>
	</div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />
	</div>
	
	
	
<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
</head>
<body>
<facebook:initJS appId="${facebookContext.app.id}" xfbml="${true}" />

<div class="row">
   <div class="span8">
		<a href="${createLink(uri: '/')}"><img src="${resource(dir: 'images', file: 'logoKahuu.png')}" /></a>
    </div>
    <div class="span4"> </div>
</div>

<div class="row">
	 <div class="span9">
	   <div class="row">
      		<div class="span6">
      			<a class="btn btn-primary btn-large">Registrate</a>
      		</div>
     		<div class="span3">
     			<a class="btn btn-primary btn-large">Registrate</a>
     		</div>
    	</div>
	 </div>
</div>

<%--<g:render template="sidebar"/>
<div class="content">
	<g:if test="${flash.message}">
		<div class="message" role="status">
			${flash.message}
		</div>
	</g:if>
		<script>
			$(document).ready(function(){
				//Examples of how to assign the Colorbox event to elements
				$(".ajax").colorbox();
				$(".youtube").colorbox({iframe:true, innerWidth:560, innerHeight:315});
			});
		</script>
	<div id="tabs">		
		<div class="tituloBox">&iquest;Qu&eacute; es Kahuu?</div>
		<div id="tab-1">
			<a href="http://www.youtube.com/embed/vD7LkpPxwhE?rel=0" class="youtube"><img width="350px" height="200px" src="${resource(dir: 'images', file: 'video_principal.jpg')}" /></a>		
			<div class="fb-like-box" data-href="https://www.facebook.com/pages/Kahuuco/242764315796016" data-width="320" data-height="200" data-show-faces="true" data-stream="false" data-header="true"></div>	
			<fb:like-box href="https://www.facebook.com/pages/Kahuuco/242764315796016" width="320" height="200" show_faces="true" stream="false" header="true"></fb:like-box>
		</div>
		<div class="tituloBox">Destacados</div>
		<div id="tab-1">
			<g:render template="listaPerfil"  model="['listaPerfiles':listaDestacados]"/>
		</div>
	</div>
</div>
<!-- end #content -->
<div style="clear: both;">&nbsp;</div>
--%>
</body>
</html>
	