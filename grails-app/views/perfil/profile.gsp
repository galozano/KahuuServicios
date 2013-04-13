<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title><g:fieldValue bean="${profileInstance}" field="nombre"/></title>
</head>
<body>
	<facebook:initJS appId="${facebookContext.app.id}" xfbml="${true}" />
	<g:render template="sidebar"/>
	<div class="content">
		<div class="profile-up">	
			<g:if test="${profileInstance?.image}">
				<img src="${createLink(controller:'perfil', action:'darFoto', id: profileInstance.id)}"  width="100" height="100"/>
			</g:if>
			<g:else>
				<img src="${resource(dir: 'images', file: 'none.jpg')}" width="100" height="100"/>
			</g:else>

			<div class="profile-general">
				<h1><g:fieldValue bean="${profileInstance}" field="nombre"/></h1>
				<img src="${resource(dir: 'images/skin', file: 'stars-'+profileInstance.totalRating+'.png')}" />&nbsp;(${reviewsTotal} Comentarios)
			</div>
					
			<div id="errores"></div>	
			<g:formRemote name="recomendarPerfil" onSuccess="disableRecommend(data)" onFailure="error()" url="[controller: 'perfil', action: 'recomendarPerfil']"  update="[success: 'message', failure: 'errores']">
    			<input type="hidden" name="idPerfil" value="${profileInstance.id}" />
    			<g:if test="${session.user}">
    				<input type="hidden" name="idUsuario" value="${session.user.id}" />
    				<g:if test="${loRecomende}">
    					<input type="submit" id="botton-recomendar" class="botton-recomendar-disabled" value="Recomendar"/> 
    				</g:if>
    				<g:else>
    					<input type="submit" id="botton-recomendar" class="botton-recomendar-enable" value="Recomendar"/>
    				</g:else>
    			</g:if>
    			<g:else>
    				Para recomendar <g:link controller="usuario" action="login">Ingresa</g:link> o <g:link controller="usuario" action="login">Registrate</g:link>
    			</g:else>
			</g:formRemote >
			
			<div id="textRecomendados" class="textoRecomendacion"></div>
			<script type="text/javascript">
				function fetchInfoRecomendados()
				{
					$.ajax(
					{
						url: "${g.createLink(controller:'perfil',action:'darAmigosRecomendaron')}",
						data: {idPerfil: "${profileInstance.id}"},
						context: document.body
					}).done(function(data) { 
						$("#textRecomendados").html(data);
						$(".inline").colorbox({inline:true, width:"30%"});
					});
				}	

				fetchInfoRecomendados();	
			</script>
			
			<!-- Facebook Like Button --><%--
			<% String url = "http://" + request.getServerName()  + request.getContextPath() + "/" + profileInstance.usuario %>			
			<div class="fb-like" data-href="${url}" data-send="false"  data-width="500" data-show-faces="false" data-action="recommend"></div>
		--%></div>
		<div class="profile-box">
				<g:if test="${session.user}">
					<div id="telefono" class="azul">
							Celular:&nbsp;<g:fieldValue bean="${profileInstance}" field="celular"/>
							<g:if test="${profileInstance.celular2}">,<g:fieldValue bean="${profileInstance}" field="celular2"/></g:if>
					</div>
				</g:if>
				<g:else>
					<div id="telefono" class="azul">Para ver el tel&eacute;fono <g:link controller="usuario" action="login">Ingresa</g:link> o <g:link controller="usuario" action="login">Registrate</g:link></div>
				</g:else>	
		</div>
		<div class="profile-box">
			<div class="box-header">
				Descripci&oacute;n
			</div>
			<div class="box-content azul">
				<p><g:lines string='${profileInstance.descripcion}'></g:lines></p>
			</div>
		</div>
		<div class="profile-box">
					<div class="box-header">
						Categor&iacute;as 
					</div>
					<div class="box-content azul">
						<g:each in="${profileInstance.categorias}" var="c">					
								<div id="categoria">${c.nombre}</div>
						</g:each>
					</div>
				</div>
				<div class="profile-box">
					<div class="box-header">
						Comentarios (
						<g:if test="${session.user}">
							<g:link controller="comentarios" action="crearComentario" id="${profileInstance.id}">Escribir Comentario</g:link>
						</g:if>
						<g:else>
							Para comentar <g:link controller="usuario" action="login">Ingresa</g:link> o <g:link controller="usuario" action="login">Registrate</g:link>
						</g:else> )
					</div>
					<div class="box-content azul">
						<g:each in="${reviewsList}" var="c">					
								<div class="review">
									<div id="header_review">
										<img src="${resource(dir: 'images/skin', file: 'stars-'+c.rating+'.png')}" />
										&nbsp;&nbsp;${c.titulo}  &nbsp;&nbsp;${c.fechaCreado.format("dd MMM, yyyy  HH:MM")} 
									</div>
									<div id="author"> Por ${c.author}</div>
									<div id="text_review">
									<p>
										  <g:lines string='${c.texto}'></g:lines>
									</p>
									</div>			
								</div>
						</g:each>	
					</div>				
				</div>
			</div>
			<!-- end #content -->
			<div style="clear: both;">&nbsp;</div>
</body>
</html>