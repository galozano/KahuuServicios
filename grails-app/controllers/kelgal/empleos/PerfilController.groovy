package kelgal.empleos

import kelgal.empleos.Categorias;
import kelgal.empleos.Profile;
import kelgal.empleos.Ciudad;

class PerfilController 
{
    def index()
	{				
		render(view: "index", model:[ciudadesLista: Ciudad.list( )]);
    }
		
	def profile(Long id )
	{	
		def profileInstance = Profile.get(id)
		
		if (!profileInstance)
		{
			flash.message = "No existe el perfil buscado";
			redirect(action: "users");
			return;
		}
		else
		{
			redirect(action:"profileUsuario", params:[usuario:profileInstance.usuario]);
		}

	}
	
	def profileUsuario( )
	{
		def profileInstance = Profile.findByUsuario(params.usuario);
		
		if (!profileInstance)
		{
			flash.message = "No existe el perfil buscado";
			redirect(action: "users");
			return;
		}
	
		//Buscar y ordernar los reviews del profile
		List revs = Review.findAllByProfile(profileInstance,[sort: "fechaCreado", order: "desc"]);
		int total =  revs ? revs.size():0;
	
		render(view: "profile", model:	[profileInstance: profileInstance, reviewsList: revs,categoriasList: Categorias.list(sort:'nombre'), reviewsTotal:total] );
	}
	
	
	def darFoto( )
	{
		def perfil = Profile.get(params.id);
			
		if(perfil)
		{
			byte[] image = perfil.image;
			response.outputStream << image;
		}
	}
	
	def buscar( )
	{
		//		def results = Profile.findAll("from Profile as b " +
		//					 "where b.nombre like :search or b.descripcion like :search",
		//					 [search: "%" + params.buscador +"%"])
			
		def query = Profile.where()
		{
			(descripcion =~ "%"+params.buscador+"%") || (nombre =~  "%"+params.buscador+"%") || (usuario =~  "%"+params.buscador+"%")
		}
		def results = query.list(sort:"reviews")
		
		if(results.size() == 0)
		{
			flash.message = "No se encontro ning&uacute;n resultado."
		}
		else
		{
			flash.message = null;
		}
		
		render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: Categorias.list(sort:'nombre')]);
	}
	
	def users(Long id)
	{	
		def cat = Categorias.get(id);	
			
		if(cat == null)
		{
			flash.message = "No se encontro ning&uacute;n resultado."
			render(view: "users",model:[categoriasList: Categorias.list(sort:'nombre')]);
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
				flash.message = "No se encontro ning&uacute;n resultado."
			}
			else
			{
				flash.message = null;
			}
			
			render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: Categorias.list(sort:'nombre')]);
		}
	}
}
