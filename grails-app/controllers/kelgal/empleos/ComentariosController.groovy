package kelgal.empleos

import java.lang.ProcessBuilder.Redirect;

import kelgal.empleos.exceptions.KahuuException;

class ComentariosController 
{
	ComentarioService comentarioService;
	UsuarioService usuarioService;
	PerfilService perfilService;
	
	def index() { }

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
		try
		{
			def profile = comentarioService.crearComentario(params.perfilId, session.user,params.titulo, params.texto, params.rating);
			redirect(controller:"perfil", action:"profile", id:profile.id);
		}
		catch(KahuuException e)
		{
			render(view:"comentario",model:[comentarioInstance:e.invalido]);
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
			def profile = comentarioService.editarComentario(id, params.titulo, params.texto, params.rating);
			flash.message = "Mensaje editado con exito";
			redirect(action:"misComentarios");
		}
		catch(KahuuException e)
		{
			flash.message = e.getMessage();
			render(view:"editcomentario", model:[comentarioInstance:e.invalido]);
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
	// Perfil Login
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
