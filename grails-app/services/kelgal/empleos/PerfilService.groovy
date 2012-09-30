package kelgal.empleos

import kelgal.empleos.exceptions.KahuuException

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
			throw new KahuuException("Perfil no existe");
		}
		
		return perfil;
	}
	
	def darPerfilUsuario(String usuario)
	{
		Profile perfil = Profile.findByUsuario(usuario);
		
		if(!perfil)
		{
			throw new KahuuException("Perfil no existe");
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
		def results = query.list(sort:"reviews")
		
		if(results.size() == 0)
		{
			throw new KahuuException("No se encontro ning&uacute;n resultado.");
		}
		else
		{
			return results;
		}
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
			
			def results = c.list() {
				categorias {
						eq("nombre",cat.nombre);
				}
				
				order("totalRating", "desc");
			}
	
			if(results.size() == 0)
			{
				throw new KahuuException("No se encontro ning&uacute;n resultado.");
			}
			else
			{
				return results;
			}
		}
	}
}
