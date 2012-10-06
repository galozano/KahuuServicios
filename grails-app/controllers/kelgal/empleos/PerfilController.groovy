package kelgal.empleos

import kelgal.empleos.Categorias;
import kelgal.empleos.Profile;
import kelgal.empleos.Ciudad;
import kelgal.empleos.exceptions.KahuuException;

class PerfilController 
{
	//------------------------------------------------------------------------------------------
	// Servicios
	//------------------------------------------------------------------------------------------
	
	PerfilService perfilService;

	//------------------------------------------------------------------------------------------
	// Metodos 
	//------------------------------------------------------------------------------------------
	
    def index()
	{				
		render(view: "index", model:[ciudadesLista: perfilService.darCiudades()]);
    }
		
	def profile(Long id)
	{	
		try
		{
			Profile profileInstance = perfilService.darPerfil(id);
			
			if(profileInstance == null)
			{
				flash.message = "No existe el perfil buscado";
				redirect(action: "users");
			}
			else
			{
				redirect(action:"profileUsuario", params:[usuario:profileInstance.usuario]);
			}
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
		
			if(profileInstance == null)
			{
				flash.message = "No existe el perfil buscado";
				redirect(action: "users");
			}
			else
			{			
				//Buscar y ordernar los reviews del profile
				List revs = perfilService.darReviewsPerfil(profileInstance);
				int total =  revs ? revs.size():0;
				
				render(view: "profile", model:	[profileInstance: profileInstance, reviewsList: revs,categoriasList:categorias, reviewsTotal:total] );
			}
		}	
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action: "users");
		}
	}
	
	def darFoto(Long id)
	{
		try
		{
			def perfil = perfilService.darPerfil(id);
			
			if(perfil != null)
			{
				byte[] image = perfil.image;
				response.outputStream << image;
			}
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action: "users");
		}

	}
	
	def buscar( )
	{		
		def categorias = perfilService.darCategorias( );
		
		try
		{
			def results = perfilService.buscarPerfil(params.buscador);
			
			if(results.size() == 0)
			{
				flash.message = "No se encontro ning&uacute;n resultado.";
				redirect(action: "users");
			}
			else
			{
				render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
			}	
		}	
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action: "users");
		}	
	}
	
	def users(Long id)
	{	
		def categorias = perfilService.darCategorias( );
		
		try
		{
			def results = perfilService.perfilesCategoria(id);
			
			if(results == null || results.size() == 0)
			{
				flash.message = "No se encontro ning&uacute;n resultado.";
				render(view: "users",model:[categoriasList: categorias]);
			}
			else
			{
				render(view: "users",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
			}
			
		}
		catch(Exception e)
		{
			flash.message = e.message;
			render(view: "users",model:[categoriasList: categorias]);
		}
	}
}
