<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title><g:message code="kahuu.kahuu" default="Kahuu" /></title>
</head>
<body>
<facebook:initJS appId="${facebookContext.app.id}" xfbml="${true}" />
<g:render template="sidebar"/>
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
				$(".youtube").colorbox({iframe:true, innerWidth:425, innerHeight:344});
			});
		</script>
	<div id="tabs">		
		<a href="http://www.youtube.com/embed/wjvYouhoLS0?rel=0" class="youtube">play me</a>
		<div class="fb-like-box" data-href="https://www.facebook.com/pages/Kahuuco/242764315796016" data-width="292" data-height="200" data-show-faces="true" data-stream="false" data-header="true"></div>			
		<div id="tituloBox">Destacados</div>
		<div id="tab-1">
			<g:render template="listaPerfil"  model="['listaPerfiles':listaDestacados]"/>
		</div>
	</div>
</div>
<!-- end #content -->
<div style="clear: both;">&nbsp;</div>
</body>
</html>
