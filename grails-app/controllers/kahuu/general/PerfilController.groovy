package kahuu.general

import kahuu.general.Categorias;
import kahuu.general.PerfilService;
import kahuu.general.Profile;
import kahuu.general.Ciudad;
import kahuu.general.exceptions.KahuuException;


/**
 * Controlador de manejo de perfiles
 * @author gustavolozano
 *
 */
class PerfilController 
{
	//------------------------------------------------------------------------------------------
	// Servicios
	//------------------------------------------------------------------------------------------
	
	PerfilService perfilService;
	
	AnuncioService anuncioService;

	//------------------------------------------------------------------------------------------
	// Metodos 
	//------------------------------------------------------------------------------------------
	
	/**
	 * Muestra la pagina principal para elegir la ciudad
	 * @return la pagina index
	 */
    def index()
	{				
		render(view: "index", model:[ciudadesLista: perfilService.darCiudades()]);
    }
	
	/**
	 * Muestra la pagina principal con los destacados y los recientes
	 * @return render de la pagina principal 
	 */
	def principal( )
	{
		List listaDestacados = perfilService.perfilesDestacados();
		List listaRecientes = perfilService.perfilesRecientes();
		List listaCategorias = perfilService.darCategorias( );
		
		List vistaAnuncios = anuncioService.darAnuncios("principal",request.getRemoteAddr());
		
		render(view: "principal",model:[listaDestacados:listaDestacados, categoriasList:listaCategorias, listaRecientes:listaRecientes,listaAnuncios:vistaAnuncios]);
	}
		
	/**
	 * Buscar un perfil por su id y lo redirecta al el perfil por usuario (deprecated)
	 * @param id id del usuario
	 * @return redirige a la actio profileUsuario
	 */
	def profile(Long id)
	{	
		try
		{
			Profile profileInstance = perfilService.darPerfil(id);
			
			if(profileInstance == null)
			{
				flash.message = "No existe el perfil buscado";
				redirect(action: "perfiles");
			}
			else
			{
				redirect(action:"profileUsuario", params:[usuario:profileInstance.usuario]);
			}
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action: "perfiles");
		}
	}
	
	/**
	 * Muestra el perfil de un usuario con su nombre de usuario
	 * @param usuario - el nombre de usuario
	 * @return la vista profile con toda la informacion de perfil
	 */
	def profileUsuario( )
	{	
		try
		{
			Profile profileInstance = perfilService.darPerfilUsuario(params.usuario);
			def categorias = perfilService.darCategorias( );
		
			if(profileInstance == null)
			{
				flash.message = "No existe el perfil buscado";
				redirect(action: "perfiles");
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
			redirect(action: "perfiles");
		}
	}
	
	/**
	 * Retorna un stream de la foto de con la id de un perfil
	 * @param id - id del perfil
	 * @return- stream de la foto
	 */
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
			redirect(action: "perfiles");
		}

	}
	
	/**
	 * Se busca los perfiles que tengan las palabras buscada en la descripcion
	 * @return- la vista perfiles con el resultado de la busqueda
	 */
	def buscar( )
	{		
		def categorias = perfilService.darCategorias( );
		
		try
		{
			def results = perfilService.buscarPerfil(params.buscador);
			
			if(results.size() == 0)
			{
				flash.message = "No se encontro ning&uacute;n resultado.";
				redirect(action: "perfiles");
			}
			else
			{
				render(view: "perfiles",model:[nombreCategoria:"Busqueda",profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
			}	
		}	
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action: "perfiles");
		}	
	}
	
	/**
	 * Muestra los perfiles dado una id de una categoria
	 * @param id-id de la categoria
	 * @return- la vista perfiles con la lista de los perfiles de la categoria
	 */
	def perfiles(Long id)
	{	
		def categorias = perfilService.darCategorias( );
		
		try
		{
			def results = perfilService.perfilesCategoria(id);
			
			if(results == null || results.size() == 0)
			{
				flash.message = "No se encontro ning&uacute;n resultado.";
				render(view: "perfiles",model:[categoriasList: categorias]);
			}
			else
			{
				render(view: "perfiles",model:[profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
			}
			
		}
		catch(Exception e)
		{
			flash.message = e.message;
			render(view: "perfiles",model:[categoriasList: categorias]);
		}
	}
	
	/**
	 * Muestra los perfiles de una categoria dada con nombre
	 * @param - nombreCategoria nombre de la categoria
	 * @return- la pagina perfiles con los perfiles que coinciden con la categoria dada
	 */
	def categoria( )
	{
		def categorias = perfilService.darCategorias( );
		
		try
		{
			def results = perfilService.perfilesCategoriasNombre(params.nombreCategoria);
			
			if(results == null || results.size() == 0)
			{
				flash.message = "No se encontro ning&uacute;n resultado.";
				render(view: "perfiles",model:[categoriasList: categorias]);
			}
			else
			{
				render(view: "perfiles",model:[nombreCategoria:params.nombreCategoria ,profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
			}
			
		}
		catch(Exception e)
		{
			flash.message = e.message;
			render(view: "perfiles",model:[categoriasList: categorias]);
		}
	}
}
