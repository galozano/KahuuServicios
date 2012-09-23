package kelgal.empleos

import java.lang.ProcessBuilder.Redirect;

class ComentariosController {

	def index() { }

	def misComentarios( )
	{
		User user = User.get(session.user.id);
		List revs = Review.findAllByUser(user,[sort: "fechaCreado", order: "desc"]);
		
		render(view:"miscomentarios", model:[listaComentarios:revs,totalComentarios:revs.size()]);
	}

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
	
	def editarComentario(Long id)
	{
		Review rev = Review.get(id);	
		
		if(!rev)
		{
			flash.message = "El comentario no existe";
			redirect(action:"misComentarios");
			return;
		}
		
		render(view:"editcomentario", model:[comentarioInstance:rev]);
	}
	
	def handleEditComentario(Long id)
	{	
		Review.withTransaction {  status ->
			
			Review rev = Review.get(id);
			
			if(!rev)
			{
				status.setRollbackOnly();
				flash.message = "El comentario no existe";
				redirect(view:"misComentarios");
				return;
			}
			
			rev.properties = params;
			
			if(rev.save(flush:true))
			{
				Profile profile = rev.profile;
				
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
					flash.message = "Mensaje editado con exito";
					redirect(action:"misComentarios");
				}
				else
				{
					status.setRollbackOnly();
					render(view:"editcomentario", model:[comentarioInstance:rev]);
					return;
				}
			}
			else
			{
				status.setRollbackOnly();
				render(view:"editcomentario", model:[comentarioInstance:rev]);
				return;
			}	
		}	
	}

	def deleteComentario( )
	{
		Profile.withTransaction { status ->

			Review rev = Review.get(params.id);
			//Long profileId = rev.profile.id;
			Profile profile = rev.profile;
			
			rev.delete(flush:true);

			//Get profile para actualizar tu total rating
			//Profile profile = Profile.get(profileId);
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
				return;
			}
			else
			{
				status.setRollbackOnly();
				redirect(action:"misComentarios");
				//render(view:"misComentarios",model:[comentarioInstance:rev,listaComentarios:user.reviews,totalComentarios:user.reviews.size()]);
			}

			status.setRollbackOnly();
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
