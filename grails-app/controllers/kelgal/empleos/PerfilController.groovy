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
		
		int total =  profileInstance.reviews.size();
			
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
		def results = Profile.findAll("from Profile as b " +
					 "where b.nombre like :search or b.descripcion like :search",
					 [search: "%" + params.buscador +"%"])
	
		render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: Categorias.list(sort:'nombre')]);
	}
	
	def users(Long id)
	{	
		def cat = Categorias.get(id);	
			
		def c = Profile.createCriteria();
		
		def results = c.list() {
			categorias {
					eq("nombre",cat.nombre);
			}
			order("reviews", "asc");		
		}
	


		if(results.size() == 0)
		{
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), id]);
		}
		
		render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: Categorias.list(sort:'nombre')]);
	}
}
