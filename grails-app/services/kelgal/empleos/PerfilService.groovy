package kelgal.empleos

import kelgal.empleos.exceptions.KahuuException


/**
 * 
 * @author gustavolozano
 *
 */
class PerfilService
{
	def darCiudades( )
	{
		return Ciudad.list( );
	}
	
	def darCategorias( )
	{
		return Categorias.list(sort:'nombre');
	}
	
	def darReviewsPerfil(Profile profile)
	{
		List revs = Review.findAllByProfile(profile,[sort: "fechaCreado", order: "desc"]);
		return revs;
	}
	
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
	
	def darPerfilUsuario(String usuario)
	{
		Profile perfil = Profile.findByUsuario(usuario);
		
		if(!perfil)
		{
			return null;
		}
		
		return perfil;
	}
	
	def buscarPerfil(String buscador)
	{
		//		def results = Profile.findAll("from Profile as b " +
		//					 "where b.nombre like :search or b.descripcion like :search",
		//					 [search: "%" + params.buscador +"%"])
			
		def query = Profile.where()
		{
			(descripcion =~ "%"+buscador+"%") || (nombre =~  "%"+buscador+"%") || (usuario =~  "%"+buscador+"%")
		}
		def results = query.list()
		
		
		return results.sort( );
	}
	
	def usuariosCategoria(id)
	{
		def cat = Categorias.get(id);
		
		if(cat == null)
		{
			throw new KahuuException("No se encontro ning&uacute;n resultado.");
		}
		else
		{
			def c = Profile.createCriteria();
			
			def results = c.list( ) {
				categorias {
						eq("nombre",cat.nombre);
				}
			}
	
			return results.sort();
		}
	}
}
