package kelgal.empleos

import kelgal.empleos.Categorias;
import kelgal.empleos.Profile;
import kelgal.empleos.Ciudad;
import kelgal.empleos.exceptions.KahuuException;

class PerfilController 
{
	PerfilService perfilService;
	
    def index()
	{				
		render(view: "index", model:[ciudadesLista: perfilService.darCiudades()]);
    }
		
	def profile(Long id )
	{	
		try
		{
			def profileInstance = perfilService.darPerfil(id);
			redirect(action:"profileUsuario", params:[usuario:profileInstance.usuario]);
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action: "users");
		}

	}
	
	def profileUsuario( )
	{	
		try
		{
			Profile profileInstance = perfilService.darPerfilUsuario(params.usuario);
			def categorias = perfilService.darCategorias( );
		
			//Buscar y ordernar los reviews del profile
			List revs = perfilService.darReviewsPerfil(profileInstance);
			int total =  revs ? revs.size():0;
			
			render(view: "profile", model:	[profileInstance: profileInstance, reviewsList: revs,categoriasList:categorias, reviewsTotal:total] );
		}	
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action: "users");
		}
	}
	
	def darFoto( )
	{
		def perfil = perfilService.darPerfil(id);
			
		if(perfil)
		{
			byte[] image = perfil.image;
			response.outputStream << image;
		}
	}
	
	def buscar( )
	{		
		def categorias = perfilService.darCategorias( );
		
		try
		{
			def results = perfilService.buscarPerfil(params.buscador);
			render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
		}	
		catch(KahuuException e)
		{
			flash.message = e.message;
			render(view: "users",model:[categoriasList: categorias]);
		}	
	}
	
	def users(Long id)
	{	
		def categorias = perfilService.darCategorias( );
		
		try
		{
			def results = perfilService.usuariosCategoria(id);
			render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			render(view: "users",model:[categoriasList: categorias]);
		}
	}
}
