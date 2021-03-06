package kahuu.general

import java.util.logging.Logger;

import kahuu.general.Categorias;
import kahuu.general.Ciudad;
import kahuu.general.Profile;
import kahuu.general.Review;
import kahuu.general.exceptions.KahuuException


/**
 * Servicio que maneja todo lo relacionado con el Perfil
 * @author gustavolozano
 *
 */
class PerfilService
{
	/**
	 * Retorna todas la ciudad que existan
	 * @return lista de ciudades
	 */
	def darCiudadesCompletas( )
	{
		return Ciudad.list(sort:'nombre');
	}
	
	/**
	 * Retorna todas las categorias 
	 * @return lista de categorias
	 */
	def darCategoriasCompletas( )
	{
		return Categorias.list(sort:'nombre');
	}
	
	/**
	 * Retorna todas la ciudad que solo esten activadas
	 * @return lista de ciudades
	 */
	def darCiudades( )
	{
		def query = Ciudad.where {
			activado == true
		}
		
		def results = query.list(sort:'nombre');
	}
	
	/**
	 * Retorna todas las categorias que solo esten activadas
	 * @return lista de categorias
	 */
	def darCategorias( )
	{		
		def query = Categorias.where {
			activado == true
		}
		
		def results = query.list(sort:'nombre')
	}
	
	/**
	 * Retorna la categoria dada una id
	 * @param idCategoria id de la categoria
	 * @return categoria de la id dada
	 */
	def darCategoriaPorId(Long idCategoria)
	{
		return Categorias.get(idCategoria);
	}
	
	/**
	 * Retrona los comentarios de un perfil en especifico
	 * @param profile el perfil - perfil != null & perfil existe
	 * @return lista de comentarios del perfil
	 */
	def darReviewsPerfil(Profile perfil)
	{
		List revs = Review.findAllByProfile(perfil,[sort: "fechaCreado", order: "desc"]);
		return revs;
	}
	
	/**
	 * Retorna un perfil con la id
	 * @param id- id del perfil- perfil != null 
	 * @return el perfil o null en caso de que no exista
	 */
	def darPerfil(id)
	{
		Profile perfil = Profile.get(id);
		
		if(!perfil)
		{
			return null;
		}
		else
		{
			return perfil;
		}
	}
	
	/**
	 * Retorna un perdil dado el nombre de usuario
	 * @param usuario- el nombre de usuario del perfil - usuario != null
	 * @return perfil con el nombre de usuario especificado o null si no existe
	 */
	def darPerfilUsuario(String usuario)
	{
		Profile perfil = Profile.findByUsuario(usuario);
		
		if(!perfil)
		{
			return null;
		}
		
		return perfil;
	}
	
	/**
	 * Busca un perfil con el texto dado - se busca en la descripcion, nombre y categoria
	 * @param buscador - el texto a buscar
	 * @return lista de los perfiles que coinciden con el texto buscado
	 */
	def buscarPerfil(String buscador)
	{
		//		def results = Profile.findAll("from Profile as b " +
		//					 "where b.nombre like :search or b.descripcion like :search",
		//					 [search: "%" + params.buscador +"%"])
			
		
		String buscadorModificado = buscador.replaceAll(" ", "%") ;
	
		def categoria = Categorias.findByNombreLike(buscadorModificado);
		
		def query = Profile.where()
		{
			(descripcion =~ "%"+buscadorModificado+"%") || (nombre =~  "%"+buscadorModificado+"%") || (usuario =~  "%"+buscadorModificado+"%") || (categorias.contains(buscador))
		}
		def results = query.list()
		
		return results.sort( );
	}
	
	/**
	 * (deprecated)
	 * Retorna una lista con los perfiles de una categoria 
	 * @param id id de la categoria, id != null
	 * @return	retorna una lista de perfiles ordenados(numero de reviews), si la categoria no existe retorna null
	 */
	def perfilesCategoria(Long id)
	{
		def cat = Categorias.get(id);
		
		if(cat == null)
		{
			return null;
		}
		else
		{
			return perfilesCategoriasNombre(cat.nombre);
		}
	}
	
	/**
	 * Retorna la lista de usuario dado el nombre de una categoria
	 * @param nombreCategoria- nombre de la categoria - nombreCategoria != null
	 * @return retorna una lista con los perfiles de la categoria
	 */
	def perfilesCategoriasNombre(String nombreCategoria)
	{
		def c = Profile.createCriteria();
		
		def results = c.list( ) {
			categorias {
					eq("nombre",nombreCategoria);
			}
		}
		return results.sort();
	}
	
	/**
	 * Retorna la lista de usuario dado el nombre de una categoria y la ciudad
	 * @param nombreCategoria- nombre de la categoria - nombreCategoria != null
	 * @param nombreCiudad - nombre de la ciudad - nombreCiudad != null
	 * @return retorna una lista con los perfiles de la categoria y de la ciudad
	 */
	def perfilesCategoriasCiudad(String nombreCategoria, String nombreCiudad)
	{
		def c = Profile.createCriteria();
		
		def results = c.list( ) {
			ciudad{
				eq("nombre",nombreCiudad);
			}
			categorias {
					eq("nombre",nombreCategoria);
			}
		}
		return results.sort();
	}
	
	
	def perfilesCategoriasCiudadId(Long idCategoria, Long idCiudad)
	{
		def c = Profile.createCriteria();
		
		def results = c.list( ) {
			ciudad{
				eq("id",idCiudad)	
			}
			categorias{
				eq("id",idCategoria)
			}
				
		}
		return results.sort();
	}
	
	
	/**
	 * Retorna una lista de 3 perfiles con lo mas destacados 
	 * los mas destacados son los que mas tienen estrellas
	 * @return la lista de los destacados
	 */
	def perfilesDestacados( )
	{
		return Profile.list(max: 5, sort: "totalRating", order: "desc").sort();		
	}
	
	/**
	 * Retorna una lista de lso perfiles que fueron anadidos recientemente
	 * @return la lista de los recientes
	 */
	def perfilesRecientes( )
	{
		return Profile.list(max: 5, sort: "fechaCreado", order: "desc");
	}
	
	/**
	 * Retorna una ciudad dada la id de la ciudad
	 * @param idCiudad - id de ciudad
	 * @return objeto ciudad
	 */
	def darCiudad(Long idCiudad)
	{
		if(idCiudad == null || idCiudad < 0)
		{
			log.error("LLego una ciudad invalida a dar ciudad");
			throw new KahuuException("Ciudad invalilda");
		}
		
		return Ciudad.get(idCiudad);
	}
}
