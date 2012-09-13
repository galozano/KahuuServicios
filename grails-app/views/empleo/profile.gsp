<%@ page contentType="text/html;charset=US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<meta name="layout" content="main" />
<title>Perfil-<g:fieldValue bean="${profileInstance}" field="nombre"/></title>
</head>

<body>
	<div id="page">
		<div id="page-bgtop">
			<div id="sidebar">
					<h1>Categor&iacuteas</h1>
					<ul>
						<g:each in="${categoriasList}" var="categoriaInstance">
							<li>
								<g:link action="users" id="${categoriaInstance.id}"> ${fieldValue(bean: categoriaInstance, field: "nombre")}</g:link>					
							</li>
						</g:each>
					</ul>
			</div>
			<div class="content">
				<div id="profile-up">	
					<g:if test="${profileInstance?.image}">
						<img src="${createLink(controller:'empleo', action:'darFoto', id: profileInstance.id)}"  width="100" height="100"/>
					</g:if>
					<g:else>
						<img src="${resource(dir: 'images', file: 'none.jpg')}" width="100" height="100"/>
					</g:else>		
					<div id="profile-general">
						<h1><g:fieldValue bean="${profileInstance}" field="nombre"/></h1>
						<h1>Cel:<g:fieldValue bean="${profileInstance}" field="celular"/></h1>
					</div>
				</div>
				<div class="profile-box">
					<div class="box-header">
						Resumen Personal 
					</div>
					<div class="box-content">
						<p>
							${profileInstance.descripcion}
						</p>
					</div>
				</div>
				<div class="profile-box">
					<div class="box-header">
						Referencias
					</div>
					<div class="box-content">
						<g:each in="${profileInstance.referencias}" var="c">					
							<div id="referencia">${c.nombre}</div>
						</g:each>
					</div>
				</div>
				<div class="profile-box">
					<div class="box-header">
						Categor&iacuteas <span>
					</div>
					<div class="box-content">
						<g:each in="${profileInstance.categorias}" var="c">					
								<div id="categoria">${c.nombre}</div>
						</g:each>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>