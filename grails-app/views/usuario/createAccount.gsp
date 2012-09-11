<%@ page contentType="text/html;charset=US-ASCII" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
<div id="registrar" class="cuadro">	
		<div id="titulo">			
			<a href="login.jsp" class="atras">Atras</a>
			<h1>Registrar</h1>		
			<span  style="color: red" >Mensaje</span>
		</div>

		<form class="formLogin" action="" method="post">
			<table>
				<tr>
					<td>Nombre:<span style="color: red">*</span>
					</td>
					<td><input type="text" name="nombre" />
					</td>
				</tr>
				<tr>
					<td>Apellido:<span style="color: red">*</span>
					</td>
					<td><input type="text" name="apellido" />
					</td>
				</tr>
				<tr>
					<td>Email:<span style="color: red">*</span>
					</td>
					<td><input type="text" name="email" />
					</td>
				</tr>
				<tr>
					<td>Telefono:<span style="color: red">*</span>
					</td>
					<td><input type="text" name="telefono" />
					</td>
				</tr>
				<tr>
					<td>Direccion:<span style="color: red">*</span>
					</td>
					<td><input type="text" name="direccion" />
					</td>
				</tr>
				<tr>
					<td>Constrasena:<span style="color: red">*</span>
					</td>
					<td><input type="password" name="password" />
					</td>
				</tr>
				<tr>
					<td>Confirmar Constrasena:<span style="color: red">*</span>
					</td>
					<td><input type="password2" name="password2" />
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="Registrar" />
					</td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>