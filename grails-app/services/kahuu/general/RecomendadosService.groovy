package kahuu.general

import org.springframework.transaction.annotation.Transactional;

import kahuu.general.exceptions.KahuuException


@Transactional
class RecomendadosService
{

	/**
	 * Metodo para recomendar a un profesional el especifico
	 * @return
	 */
	def recomendarPerfil(Long idPefil, Long idUsuario)
	{
		log.debug("---Recomendando Perfil---")
		Profile perfil = Profile.get(idPefil);
		User usuario = User.get(idUsuario);
		
		Recomendados recomendados = new Recomendados(profile:perfil,user:usuario);
		
		log.debug("Guardado la recomendacion de:" + recomendados.toString());
		
		if(recomendados.save())
		{
			return true;
		}
		else
		{
			log.error("No se pudo guardar la recomendacion");
			throw new KahuuException("Error guardadndo la recomendacion", recomendados);
		}
	}
	
	/**
	 * Retorna si el usuario dado esta recomendado o no
	 * @return
	 */
	def estaRecomendado(Profile perfil, User usuario)
	{
		Recomendados recomendados = Recomendados.findByProfileAndUser(perfil,usuario);	
		return recomendados;
	}
	
	/**
	 * Retorna la lista de amigos del usuario que recomendaron el perfil
	 * @return lista de amigos recomendados
	 */
	def darRecomendaron(Long idPerfil)
	{
		Profile perfil = Profile.get(idPerfil)
		List recomendaciones = Recomendados.findAllByProfile(perfil);
		return recomendaciones;
	}
}
