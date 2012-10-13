package kelgal.empleos;

import kelgal.empleos.exceptions.KahuuException;
import org.springframework.transaction.annotation.Transactional


/**
 * EL servicio que maneja todo lo relacionado con los comentarios
 * @author gustavolozano
 */
@Transactional
class ComentarioService 
{
	/**
	 * Devuelve los comentarios de un usuario existente 
	 * @param id- id del usuario id != null && id existe
	 * @return lista con los comentarios del usuario
	 * @throws KahuuException-si no existe el usuario
	 */
	@Transactional(readOnly = true)
	def darMisComentarios(Long id) throws KahuuException
	{
		User user = User.get(id);
		
		if(user)
		{
			List revs = Review.findAllByUser(user,[sort: "fechaCreado", order: "desc"]);
			return revs;
		}
		else
		{
			throw new KahuuException("El usuario no existe");
		}
	}

	/**
	 * Retorna el review con un id dado
	 * @param  Long id del review, id != null & id existe
	 * @return Review review identificado con la id entrada por parametro
	 * @throws KahuuException si el review no existe
	 */
	@Transactional(readOnly = true)
	def darReview(Long id) throws KahuuException
	{
		Review rev =  Review.get(id);

		if(!rev)
		{
			throw new KahuuException("El comentario no existe");
		}

		return rev;
	}

	/**
	 * Agregar un comentario a un perfil
	 * @param pPerfiln- perfil al cual se le agrega el nuevo comentario
	 * @param pUser- usuario que agrego el nuevo comentario
	 * @param titulo - titulo del comentario
	 * @param texto - comentario
	 * @param rating - calificacion del comentario
	 * @return perfil a quien se le agrego el comentario
	 * @throws KahuuException- en caso de que no se pueda guardar el perfil actualizado o el comentario 
	 */
	def crearComentario(Profile pPerfiln, User pUser, String titulo, String texto, int rating) throws KahuuException
	{
		Profile profile = pPerfiln;
		User user = pUser;

		Review review = new Review(author:user.nombre, titulo:titulo ,texto:texto, rating:rating, profile:profile,user:user, fechaCreado:new Date());

		if(review.save(flush:true))
		{
			profile = Profile.get(profile.id);

			int total =  profile.reviews.size();
			int average = 0;

			if(total != 0)
			{
				average = profile.reviews.rating.sum() / total;
			}

			//Se actualiza el rating al nuevo despues de agregar el comentario
			profile.totalRating = average;

			if(profile.save(flush:true))
			{
				return profile;
			}
			else
			{
				throw new KahuuException("Error guardando", review);
			}
		}
		else
		{
			throw new KahuuException("Error guardando", review);
		}
	}
	
	/**
	 * Se edita el comentario con la id especificada
	 * @param id id!= null
	 * @param titulo titulo != null
	 * @param texto  texto != null
	 * @param rating  rating < 6 && rating > -1
	 * @return retorna el perfil al cual se le fue editado el comentario
	 * @throws KahuuException- so no se puede guardarel nuevo review 
	 */
	def editarComentario(Long id,String titulo, String texto, int rating) throws KahuuException
	{
		Review rev = Review.get(id);

		if(!rev)
		{
			throw new KahuuException("El comentario no existe");
		}

		rev.titulo = titulo;
		rev.texto = texto;
		rev.rating = rating;
		
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

			if(profile.save(flush:true))
			{
				return profile;
			}
			else
			{
				throw new KahuuException("Error guardando", rev);
			}
		}
		else
		{
			throw new KahuuException("Error guardando", rev);
		}
	}
	
	/**
	 * Borra un comentario con la id dada
	 * @param id id != null && id existe
	 * @return void
	 * @throws KahuuException
	 */
	def deleteComentario(id) throws KahuuException
	{	
		Review rev = Review.get(id);
		
		if(!rev)
		{
			throw new KahuuException("Comentario no existe");
		}
		
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
			return;
		}
		else
		{
			throw new KahuuException("Error eliminando", rev);
		}
	}
}
