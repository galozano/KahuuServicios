<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
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
				$(".youtube").colorbox({iframe:true, innerWidth:560, innerHeight:315});
			});
		</script>
	<div id="tabs">		
		<div class="tituloBox">&iquest;Qu&eacute; es Kahuu?</div>
		<div id="tab-1">
			<a href="http://www.youtube.com/embed/vD7LkpPxwhE?rel=0" class="youtube"><img width="350px" height="200px" src="${resource(dir: 'images', file: 'video_principal.jpg')}" /></a>		
			<div class="fb-like-box" data-href="https://www.facebook.com/pages/Kahuuco/242764315796016" data-width="320" data-height="200" data-show-faces="true" data-stream="false" data-header="true"></div>	
			<%--<fb:like-box href="https://www.facebook.com/pages/Kahuuco/242764315796016" width="320" height="200" show_faces="true" stream="false" header="true"></fb:like-box>--%>
		</div>
		<div class="tituloBox">Destacados</div>
		<div id="tab-1">
			<g:render template="listaPerfil"  model="['listaPerfiles':listaDestacados]"/>
		</div>
	</div>
</div>
<!-- end #content -->
<div style="clear: both;">&nbsp;</div>
</body>
</html>
