package kelgal.empleos

class EmpleoController 
{
    def index()
	{				
		render(view: "index", model: [categoriasList: Categorias.list()]);
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
			
		render(view: "profile", model:	[profileInstance: profileInstance, categoriasList: Categorias.list()] );
	}
	
	def darFoto( )
	{
		def perfil = Profile.get(params.id);
		byte[] image = perfil.image;
		
		response.outputStream << image;
	}
	
	def buscar( )
	{
		def c = Profile.createCriteria()
		def results = c.list {
			ilike("nombre", "%" + params.buscador + "%")

			order("nombre", "desc")
		}
		
		render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: Categorias.list()]);
	}
	
	def users(Long id)
	{	
		def profiles = Profile.list();
		List results = new ArrayList();
		
		for(profile in profiles)
		{	
			for(categoria in profile.categorias)
			{					
				if(categoria.id == id)
				{
					results.add(profile);
				}	
			}
		}
		
		if(results.size() == 0)
		{
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), id]);
		}
		
		render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: Categorias.list()]);
	}
}
