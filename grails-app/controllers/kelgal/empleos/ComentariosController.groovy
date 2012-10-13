package kelgal.empleos


/**
 * 
 * @author gustavolozano
 *
 */
class ComentariosController 
{
	//------------------------------------------------------------------------------------------
	// Servicios
	//------------------------------------------------------------------------------------------

	ComentarioService comentarioService;
	
	UsuarioService usuarioService;
	
	PerfilService perfilService;
	
	def index() { }

	//------------------------------------------------------------------------------------------
	// Metodos Comentarios
	//------------------------------------------------------------------------------------------
	
	def misComentarios( )
	{
		try
		{
			def revs = comentarioService.darMisComentarios(session.user.id);
			render(view:"miscomentarios", model:[listaComentarios:revs,totalComentarios:revs.size()]);
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			render(view:"miscomentarios");
		}
	}
	
	def crearComentario( )
	{
		try
		{	
			def profile = perfilService.darPerfil(params.id);
			
			if(profile == null)
			{
				flash.message = "No existe el perfil buscado";
				render(view:"comentario");
			}
			
			render(view:"comentario",model:[profileInstance:profile]);
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			render(view:"comentario");
		}
	}

	def handleComentario()
	{			
		withForm 
		{				
			Profile prof;
			
			try
			{
				int rating = Integer.parseInt(params.rating);
				
				if(rating < 1 || rating > 5)
				{
					flash.message = "Valor del comentario es invalido";
					render(view:"comentario");
				}
				
				prof = perfilService.darPerfil(Long.parseLong(params.perfilId));
				
				Profile profile = comentarioService.crearComentario(prof, session.user,params.titulo, params.texto, rating);
				redirect(controller:"perfil", action:"profileUsuario", params:[usuario:profile.usuario]);
			}
			catch(KahuuException e)
			{
				render(view:"comentario",model:[comentarioInstance:e.invalido,profileInstance:prof]);
			}
			catch(Exception e)
			{
				flash.message = "Error inesperado";
				render(view:"comentario",model:[profileInstance:prof]);
			}
		}
		.invalidToken
		{
			flash.message = "No unda tanta veces crear.";
			render(view:"miscomentarios");
		}
	}
	
	def editarComentario(Long id)
	{
		try
		{
			def rev = comentarioService.darReview(id);
			render(view:"editcomentario", model:[comentarioInstance:rev]);
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action:"misComentarios");
		}
	}
	
	def handleEditComentario(Long id)
	{	
		try
		{
			int rating = Integer.parseInt(params.rating);
			
			if(rating < 1 || rating > 5)
			{
				flash.message = "Valor del comentario es invalido";
				redirect(action:"editarComentario", id:id);
			}
			else
			{
				def profile = comentarioService.editarComentario(id, params.titulo, params.texto, rating);
				flash.message = "Mensaje editado con exito";
				redirect(action:"misComentarios");
			}
		}
		catch(KahuuException e)
		{
			flash.message = e.getMessage();
			render(view:"editcomentario", model:[comentarioInstance:e.invalido]);
		}
		catch(Exception e)
		{
			flash.message = "Error inesperado";
			redirect(action:"editarComentario", id:id)
		}
	}

	def deleteComentario( )
	{		
		try
		{
			comentarioService.deleteComentario(params.id);
			redirect(action:"misComentarios");
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action:"misComentarios");
		}
	}
	
	//------------------------------------------------------------------------------------------
	// Metodos Perfil
	//------------------------------------------------------------------------------------------

	def miPerfil()
	{
		render(view:"miperfil",model: [userInstance:session.user]);
	}

	def handleCambiarPassword()
	{
		if (params.password != params.confirm)
		{
			flash.message = "Las constrase&ntilde;as no son iguales."
			redirect(action:"miPerfil")
		}
		else if(params.password.equals("") || params.password.size() < 4)
		{
			flash.message = "Constrase&ntilde;a tiene que ser mayor de 4 carcacteres"
			redirect(action:"miPerfil")
		}
		else
		{
			try
			{
				def user = usuarioService.cambiarPassword(params.password, session.user.id);
				
				session.user = user;
				flash.message = "Se cambio la constrase&ntilde;a exitosamente"
				render(view:"miperfil",model: [userInstance:user]);
			}
			catch(KahuuException e)
			{
				render(view:"miperfil",model: [userInstance:e.invalido]);
			}
		}
	}

	def handleActualizarUsuario()
	{
		try
		{
			def user = usuarioService.actualizarUsuario(params.nombre, session.user.id);
			
			session.user = user;
			flash.message = "Se actualizo el usuario exitosamente";
			render(view:"miperfil",model: [userInstance:user]);
		}
		catch(KahuuException e)
		{
			render(view:"miperfil",model: [userInstance:e.invalido]);
		}
	}
}
