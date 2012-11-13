package kahuu.general


import kahuu.general.exceptions.KahuuException;

/**
 * Controlador de manejo de los comentarios
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
	
	AnuncioService anuncioService;
	
	def index() { }

	//------------------------------------------------------------------------------------------
	// Metodos Comentarios
	//------------------------------------------------------------------------------------------
	
	/**
	 * Retorna la pagina con todos los comentarios del usuario
	 * @return- pagina miscomentarios
	 */
	def misComentarios( )
	{
		try
		{
			if(session.user)
			{
				def revs = comentarioService.darMisComentarios(session.user.id);
				render(view:"miscomentarios", model:[listaComentarios:revs,totalComentarios:revs.size()]);
			}
			else
			{
				flash.message = "No existe un usuario con session iniciada";
				render(view:"miscomentarios");
			}
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			render(view:"miscomentarios");
		}
	}
	
	/**
	 * Muestra la pagina donse se escribe el comentario
	 * @params-id id del perfil que se le va a agregar el comentario
	 * @return- pagina donde se escribe el comentario
	 */
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

	/**
	 * Maneja la creacion de un nuevo comentario
	 * @params-rating calificacion 
	 * @params-titulo- titulo del comentario
	 * @params-texto - texto del comentario
	 * @return el perfil al cual se le agrego el comentario
	 */
	def handleComentario()
	{		
		//No deja que se haga doble submision de formulario	
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
	
	/**
	 * Muestra la pagina para editar un comentario
	 * @param id - id el comentario 
	 * @return - pagina para editar el comentario
	 */
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
	
	/**
	 * Maneja la actualizacion del comentario
	 * @param id- id del comentario 
	 * @params-rating calificacion 
	 * @params-titulo- titulo del comentario
	 * @params-texto - texto del comentario
	 * @return - va a la pagina de miscomentarios de los contarrio a editarcomentario si existe un error
	 */
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

	/**
	 * Remueve un comentario de la lista 
	 * @params id del comentario que se va a remover
	 * @return- pagina miscomentario
	 */
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

	/**
	 * 
	 * @return
	 */
	def miPerfil()
	{
		render(view:"miperfil",model: [userInstance:session.user]);
	}
	
	/**
	 * Te lleva a la pagina para buscar una persona para escribir un comentario
	 * @return - la pagina donde buscar
	 */
	def escribirComentario( )
	{
		render(view:"escribircomentario");
	}

	/**
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @return
	 */
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
	
	//------------------------------------------------------------------------------------------
	// Metodos Anuncios
	//------------------------------------------------------------------------------------------
	
	/**
	 * Muestra to lo relacionado con el numero de vistas y numero de clicks
	 * @return- la pagina de la administracion de kahuu anuncios.
	 */
	def administradorAnuncios( )
	{
		List anuncios = anuncioService.darAnunciosUsuario(session.user);
		render(view:"misanuncios", model:[listaAnuncios:anuncios])
	}
}
