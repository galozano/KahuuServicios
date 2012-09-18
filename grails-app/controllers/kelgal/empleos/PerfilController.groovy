package kelgal.empleos

import kelgal.empleos.Categorias;
import kelgal.empleos.Profile;

class PerfilController 
{
    def index()
	{				
		render(view: "index", model: [categoriasList: Categorias.list(sort:'nombre')]);
    }
		
	def profile(Long id )
	{	
		def profileInstance = Profile.get(id)
		
		if (!profileInstance) 
		{
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), id]);
			redirect(action: "users");
			return;
		}
	
		int total =  profileInstance.reviews ? profileInstance.reviews.size():0;
	
		render(view: "profile", model:	[profileInstance: profileInstance, categoriasList: Categorias.list(sort:'nombre'), reviewsTotal:total] );
	}
	
	def darFoto( )
	{
		def perfil = Profile.get(params.id);
		byte[] image = perfil.image;
		
		response.outputStream << image;
	}
	
	def buscar( )
	{
//		def results = Profile.findAll("from Profile as b " +
//					 "where b.nombre like :search or b.descripcion like :search",
//					 [search: "%" + params.buscador +"%"])
	
		def query = Profile.where()
		{
			(descripcion =~ "%"+params.buscador+"%") || (nombre =~  "%"+params.buscador+"%")
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
				order("reviews", "asc");		
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
