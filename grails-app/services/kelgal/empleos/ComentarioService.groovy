package kelgal.empleos

import kelgal.empleos.exceptions.KahuuException;
import org.springframework.transaction.annotation.Transactional

@Transactional
class ComentarioService {

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

	def crearComentario(Long pPerfil, User pUser, String titulo, String texto, int rating)
	{
		Profile profile = Profile.get(pPerfil);
		User user = pUser;

		Review review = new Review(author:user.nombre, titulo:titulo ,texto:texto, rating:rating, profile:profile,user:user, fechaCreado:new Date());

		if(review.save())
		{
			profile = Profile.get(pPerfil);

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
				return profile;
			}
			else
			{
				status.setRollbackOnly();
				throw new KahuuException("Error guardando", review);
			}
		}
		else
		{
			status.setRollbackOnly();
			throw new KahuuException("Error guardando", review);
		}
	}

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

			if(profile.save())
			{
				return profile;
			}
			else
			{
				status.setRollbackOnly();
				throw new KahuuException("Error guardando", rev);
			}
		}
		else
		{
			status.setRollbackOnly();
			throw new KahuuException("Error guardando", rev);
		}
	}
	
	def deleteComentario(id)
	{	
		Review rev = Review.get(id);
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
			status.setRollbackOnly();
			throw new KahuuException("Error eliminando", rev);
		}
		status.setRollbackOnly();
	}
}
