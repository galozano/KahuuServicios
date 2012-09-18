package kelgal.empleos

import java.lang.ProcessBuilder.Redirect;

class ComentariosController {

    def index() { }
	
	def crearComentario( )
	{
		def profile = Profile.get(params.id);
		render(view:"comentario",model:[profileInstance:profile]);
	}
	
	def handleComentario()
	{
		Profile.withTransaction { status ->
			
			Profile profile = Profile.get(params.perfil);
			User user = session.user;
			
			Review review = new Review(author:user.nombre, titulo:params.titulo ,texto:params.texto, rating:params.rating, profile:profile,user:user, fechaCreado:new Date());
		
			if(review.save())
			{
				profile = Profile.get(params.perfil);
				
				int total =  profile.reviews.size();
				int average = 0;
		
				if(total != 0)
				{
					average = profile.reviews.rating.sum() / total;
				}
				
				//Se actualiza el rating al nuevo despues de agregar el comentario
				profile.totalRating = average;
				
				if(profile.save())
				{
					redirect(controller:"perfil", action:"profile", id:profile.id);
				}
				else
				{
					status.setRollbackOnly();
					render(view:"comentario",model:[profileInstance:profile,comentarioInstance:review]);
				}
			}
			else
			{
				status.setRollbackOnly();
				render(view:"comentario",model:[profileInstance:profile,comentarioInstance:review]);
			}
		}
	}
	
	def misComentarios( )
	{
		User user = User.get(session.user.id);	
		render(view:"miscomentarios", model:[listaComentarios:user.reviews,totalComentarios:user.reviews.size()])
	}
	
	def deleteComentario( )
	{
		Review.withTransaction { status ->
			
			Review rev = Review.get(params.id);
			User user = User.get(session.user.id);
			Profile profile = rev.profile;
			
			if(rev.delete(flush:true))
			{
				int total =  profile.reviews.size();
				int average = 0;
		
				if(total != 0)
				{
					average = profile.reviews.rating.sum() / total;
				}
				
				//Se actualiza el rating al nuevo despues de agregar el comentario
				profile.totalRating = average;
				
				if(profile.save())
				{
					redirect(action:"misComentarios");
				}
				else
				{
					status.setRollbackOnly();
					render(view:"misComentarios",model:[comentarioInstance:rev,listaComentarios:user.reviews,totalComentarios:user.reviews.size()]);
				}
			}
			else
			{
				status.setRollbackOnly();
				flash.message = "No se pudo borrar el comentario";
				render(view:"misComentarios",model:[comentarioInstance:rev,listaComentarios:user.reviews,totalComentarios:user.reviews.size()]);
			}
		}
	}
	
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
			User user = User.get(session.user.id);
			
			user.password = params.password.encodeAsSHA1();
			
			if(user.save(flush:true))
			{
				session.user = user;
				flash.message = "Se cambio la constrase&ntilde;a exitosamente"
				render(view:"miperfil",model: [userInstance:user]);
			}
			else
			{
				render(view:"miperfil",model: [userInstance:user]);
			}
		}
	}
	
	def handleActualizarUsuario()
	{
		User user = User.get(session.user.id);
		user.nombre = params.nombre;
		
		if(user.save(flush:true))
		{
			session.user = user;
			flash.message = "Se actualizo el usuario exitosamente"
			render(view:"miperfil",model: [userInstance:user]);
		}
		else
		{
			render(view:"miperfil",model: [userInstance:user]);	
		}
	}	
}
