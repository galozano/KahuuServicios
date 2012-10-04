package kelgal.empleos

import kelgal.empleos.exceptions.KahuuException;
import org.springframework.transaction.annotation.Transactional


/**
 * EL servicio que maneja todo lo relacionado con los comentarios de los perfiles
 * @author gustavolozano
 */
@Transactional
class ComentarioService 
{
	/**
	 * Devuelve los comentarios de un usuario existente 
	 * @param id id != null && id existe
	 * @return
	 */
	@Transactional(readOnly = true)
	def darMisComentarios(Long id)
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
	 * Devuelve el review con un id dado
	 * @pre el id debe existir
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	def darReview(id)
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
	 * @param pPerfiln
	 * @param pUser
	 * @param titulo
	 * @param texto
	 * @param rating
	 * @return el perfil a quien se le agrego el comentario
	 */
	def crearComentario(Profile pPerfiln, User pUser, String titulo, String texto, int rating)
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
	 */
	def editarComentario(Long id,String titulo, String texto, int rating)
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
	 */
	def deleteComentario(id)
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
