package kahuu.general

import grails.plugin.facebooksdk.FacebookContext;
import grails.plugin.facebooksdk.FacebookContextUser
import grails.plugin.facebooksdk.FacebookGraphClient
import kahuu.general.Categorias;
import kahuu.general.PerfilService;
import kahuu.general.Profile;
import kahuu.general.Ciudad;
import kahuu.general.exceptions.KahuuException;
import org.codehaus.groovy.grails.web.json.JSONObject
import org.compass.core.engine.SearchEngineQueryParseException


/**
 * Controlador de manejo de perfiles
 * @author gustavolozano
 */
class PerfilController 
{
	//------------------------------------------------------------------------------------------
	// Servicios
	//------------------------------------------------------------------------------------------
	
	FacebookContext facebookContext;
	
	PerfilService perfilService;
	
	RecomendadosService recomendadosService;
	
	UsuarioService usuarioService;
	
	def searchableService;

	//------------------------------------------------------------------------------------------
	// Metodos 
	//------------------------------------------------------------------------------------------
	
	/**
	 * Muestra la pagina 
	 * @return la pagina index
	 */
    def index()
	{				
    	redirect(action:"principal");
	}
	
	/**
	 * Muestra la pagina principal con los destacados y los recientes
	 * @return render de la pagina principal 
	 */
	def principal(Long ciudad)
	{
		List listaCategorias = perfilService.darCategorias( );
		List listaCiudades = perfilService.darCiudades();
		List listaDestacados = perfilService.perfilesDestacados();
		
		if(ciudad == null || ciudad.equals(""))
		{
			//TODO:Arreglar esto
			ciudad = 1;
		}
		
		log.debug("Mostrando lista de perfiles destacados")
		
		render(view: "principal",model:[idCiudad:ciudad ,listaDestacados:listaDestacados, categoriasList:listaCategorias,ciudadesList:listaCiudades]);
	}
		
	/**
	 * (deprecated) Ahora se ultiliza el profileUsuario 
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
			log.warn("Error mostrando perfil:" + e.getMessage());
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
			def categorias = perfilService.darCategorias( );
			List listaCiudades = perfilService.darCiudades();
			
			//Saca la ciudad de donde es la persona
			Long idCiudad = params.long('idCiudad');
			
			log.debug("Ciudad de persona es:" + idCiudad);
			
			boolean loRecomende = false; //Si el usuario en session recomendo o no el perfil
			
			Profile profileInstance = perfilService.darPerfilUsuario(params.usuario);
			
			if(profileInstance == null)
			{
				flash.message = "No existe el perfil buscado";
				redirect(action: "perfiles");
				return;
			}
			else
			{			
				//Buscar y ordernar los reviews del profile
				List revs = perfilService.darReviewsPerfil(profileInstance);
				int total =  revs ? revs.size():0;
				
				//Averiguar si el perfil esta recomendado por el usuario en session
				if(session.user)
				{
					Recomendados rec = recomendadosService.estaRecomendado(profileInstance, session.user);
					loRecomende = rec==null? false:true;		
				}
				
				log.debug("Perfil-Estado de la recomendacion: " + loRecomende);
				
				render(view: "profile", model:	[loRecomende:loRecomende,profileInstance: profileInstance, reviewsList: revs,categoriasList:categorias, reviewsTotal:total, ciudadesList:listaCiudades, idCiudad:idCiudad] );
			}
		}	
		catch(KahuuException e)
		{
			log.warn("Error mostrando perfil:" + e.getMessage());
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
		def perfil = perfilService.darPerfil(id);
			
		if(perfil != null)
		{
			byte[] image = perfil.image;
			response.outputStream << image;
		}
	}
	
	/**
	 * Se busca los perfiles que tengan las palabras buscada en la descripcion
	 * @return- la vista perfiles con el resultado de la busqueda
	 */
	def buscar(Long ciudad)
	{		
		def categorias = perfilService.darCategorias( );
		List listaCiudades = perfilService.darCiudades();
		Ciudad ciudadEncontrada = perfilService.darCiudad(ciudad);
		
//		
//		try
//		{
//			def results = perfilService.buscarPerfil(params.buscador);
//			
//			if(results.size() == 0)
//			{
//				flash.message = "No se encontro ning&uacute;n resultado.";
//				redirect(action: "perfiles");
//			}
//			else
//			{
//				render(view: "perfiles",model:[nombreCategoria:"Busqueda",profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
//			}	
//		}	
//		catch(KahuuException e)
//		{
//			flash.message = e.message;
//			redirect(action: "perfiles");
//		}	
		
		if (!params.q?.trim()) {
			return [:]
		}
		
		try 
		{
		
			//Falta que me del el nombre de la ciudad
			def searchResults = searchableService.search("*" + params.q + "* +"+ ciudadEncontrada.nombre + " *");
			render(view: "perfiles",model:[idCiudad:ciudad, nombreCategoria:"Buscador",profileInstanceList: searchResults.results ,profileInstanceTotal:searchResults.results.size(), categoriasList: categorias, ciudadesList:listaCiudades]);			
		}
		catch (SearchEngineQueryParseException e)
		{
			log.warn("Error en la busqueda:" + e.getMessage());
			flash.message = e.message;
			redirect(action: "perfiles");
		}
	}
	
	/**
	 *  (deprecated)
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
	 * (deprecated) ahora se comenzara a ultilizar categoriasCiudad
	 * Muestra los perfiles de una categoria dada con nombre
	 * @param - nombreCategoria nombre de la categoria
	 * @return- la pagina perfiles con los perfiles que coinciden con la categoria dada
	 */
	def categoria(String nombreCategoria)
	{
		def categorias = perfilService.darCategorias( );
		
		try
		{
			def results = perfilService.perfilesCategoriasNombre(nombreCategoria);
			
			if(results == null || results.size() == 0)
			{
				flash.message = "No se encontro ning&uacute;n resultado.";
				render(view: "perfiles",model:[categoriasList: categorias]);
			}
			else
			{
				render(view: "perfiles",model:[nombreCategoria:nombreCategoria ,profileInstanceList: results ,profileInstanceTotal:results.size(), categoriasList: categorias]);
			}
		}
		catch(Exception e)
		{
			flash.message = e.message;
			render(view: "perfiles",model:[categoriasList: categorias]);
		}
	}
	
	/**
	 *  (deprecated)
	 * Mustra los perfiles de una ciudad y categoria particular
	 * @param nombreCiudad - el nombre de la ciudad de los perfiles
	 * @param nombreCategoria - el nombre de la categoria de los perfiles
	 * @return lista de perfiles
	 */
	def categoriasCiudad(String nombreCategoria, String nombreCiudad)
	{
		def categorias = perfilService.darCategorias( );
		def ciudades = perfilService.darCiudades();
		
		log.debug("Numero de Ciudades a imprimir:" + ciudades.size());
		
		try
		{
			def results = perfilService.perfilesCategoriasCiudad(nombreCategoria, nombreCiudad);
			
			if(results == null || results.size() == 0)
			{
				flash.message = "No se encontro ning&uacute;n resultado.";
				render(view: "perfiles",model:[nombreCategoria:nombreCategoria, nombreCiudad:nombreCiudad,categoriasList: categorias,ciudadesList:ciudades]);
			}
			else
			{
				render(view: "perfiles",model:[nombreCategoria:nombreCategoria, nombreCiudad:nombreCiudad, profileInstanceList:results ,profileInstanceTotal:results.size(), categoriasList:categorias, ciudadesList:ciudades]);
			}
		}
		catch(Exception e)
		{
			flash.message = e.message;
			render(view: "perfiles",model:[categoriasList: categorias]);
		}
	}
	
	/**
	 * Mustra los perfiles de una ciudad y categoria particular
	 * @param nombreCiudad - el nombre de la ciudad de los perfiles
	 * @param nombreCategoria - el nombre de la categoria de los perfiles
	 * @return lista de perfiles
	 */
	def categoriasCiudadId(Long idCiudad, Long idCategoria)
	{
		log.debug("Metodo:categoriasCiudadId");
		
		def categorias = perfilService.darCategorias( );
		def ciudades = perfilService.darCiudades();
		
		log.debug("Numero de Ciudades a imprimir:" + ciudades.size());
		log.debug("Categoria:" + idCategoria)
		
		if(idCiudad == null && idCategoria != null)
		{
			redirect(action: "principal", params:[ciudad:1]);
			return;
		}
		
		if(idCiudad != null && idCategoria == null)
		{
			redirect(action: "principal", params:[ciudad:idCiudad]);
			return;
		}
			
		def categoria = perfilService.darCategoriaPorId(idCategoria);
		
		try
		{
			def results = perfilService.perfilesCategoriasCiudadId(idCategoria, idCiudad);
			
			if(results == null || results.size() == 0)
			{
				flash.message = "No se encontro ning&uacute;n resultado.";
				render(view: "perfiles",model:[categoria:categoria,idCategoria:idCategoria, idCiudad:idCiudad,categoriasList: categorias,ciudadesList:ciudades]);
			}
			else
			{
				render(view: "perfiles",model:[categoria:categoria,idCategoria:idCategoria, idCiudad:idCiudad, profileInstanceList:results ,profileInstanceTotal:results.size(), categoriasList:categorias, ciudadesList:ciudades]);
			}
		}
		catch(Exception e)
		{
			flash.message = e.message;
			render(view: "perfiles",model:[categoriasList: categorias]);
		}
	}
	
	//------------------------------------------------------------------------------------------
	// Metodos AJAX
	//------------------------------------------------------------------------------------------
	
	/**
	 * Metodo para recomendar a un profesional el especifico
	 * @param idPerfil - el perfil que se recomendo
	 * @param idUsuario - el id del usuario que recomendo
	 * @return
	 */
	def recomendarPerfil(Long idPerfil, Long idUsuario)
	{
		try
		{
			String respuesta = recomendadosService.recomendarPerfil(idPerfil, idUsuario);
			render respuesta;
		}
		catch(Exception e)
		{
			flash.message = e.message;
		}
	}
	
	/**
	 * Retorna la lista de amigos del usuario que recomendaron el perfil
	 * @return lista de amigos recomendados
	 */
	def darAmigosRecomendaron(Long idPerfil)
	{	
		//Lista de recomendaciones que recomendaron este perfil
		List recomendaciones = recomendadosService.darRecomendaron(idPerfil);
		
		int totalRecomendados = recomendaciones.size(); //Total de recomendados desconocidos
		int cuantosAmigos = 0;
		boolean recomendadoPorMi = false;
		
		def mapaRecomendados = [:]
		String listaDeNombres = "";
		String html = "";
		
		if (facebookContext.authenticated && session.user)
		{
			// User is authenticated
			def facebookClient = new FacebookGraphClient(facebookContext.user.token);
			//Get el perfil del usuario
			List myFriends = facebookClient.fetchConnection("me/friends");
			
			//Guarda toda la lista de recomendados en un mapa
			for(Recomendados rec: recomendaciones)
			{
				if(rec.user.id == session.user.id)
				{
					recomendadoPorMi = true;
					
					//quitarse a si mismo de la lista
					totalRecomendados--;
				}
				
				mapaRecomendados.put(rec.user.idFacebook, true);
			}
			
			for(String usuario : myFriends)
			{
				JSONObject userJson = new JSONObject(usuario);
					
				if(mapaRecomendados.get(userJson.getString("id")) && !(userJson.getString("id").equals(facebookContext.user.id.toString())))
				{
					cuantosAmigos++;
					totalRecomendados--;
					listaDeNombres += "<img src='https://graph.facebook.com/" + userJson.getString("id") + "/picture'/>" + userJson.getString("name") + "</br>";
				}
			}
		}
		
		html = "<div style='display:none'>"
		html += "<div id='inline_content' style='padding:10px; background:#fff;overflow:visible;height:200px'>"
		html +=  listaDeNombres
		html +=	"</div>"
		html += "</div>"
		
		if(cuantosAmigos == 0 && totalRecomendados > 0 && recomendadoPorMi)
		{
			html +=  "T&uacute; y otras " + totalRecomendados + " personas lo recomiendan en facebook";
		}
		else if(cuantosAmigos == 0 && totalRecomendados > 0 && !recomendadoPorMi)
		{
			html +=  totalRecomendados + " personas lo recomiendan en facebook";
		}
		else if(totalRecomendados == 0 && cuantosAmigos == 0 && !recomendadoPorMi)
		{
			html +=  "Se el primero de tus amigos en facebook en recomendar a esta persona ";
		}
		else if(totalRecomendados == 0 && cuantosAmigos == 0 && recomendadoPorMi)
		{
			html +=  "T&uacute; lo recomiendas en facebook";
		}
		else if(cuantosAmigos > 0 && totalRecomendados > 0 && recomendadoPorMi)
		{
			html +=  "T&uacute;, <a class='inline' href='#inline_content''>" + cuantosAmigos +" amigos en facebook </a> y otras " + totalRecomendados + " personas lo recomiendan";
		}
		else if(cuantosAmigos > 0 && totalRecomendados > 0 && !recomendadoPorMi)
		{
			html +=  "<a class='inline' href='#inline_content''>" + cuantosAmigos +" de tus amigos en facebook </a> lo recomiendan y otras " + totalRecomendados + " personas";
		}
		else if(cuantosAmigos > 0 && totalRecomendados == 0 && recomendadoPorMi)
		{
			html +=  "T&uacute;,<a class='inline' href='#inline_content''>" + cuantosAmigos +" de tus amigos en facebook </a> lo recomiendan";
		}
		else if(cuantosAmigos > 0 && totalRecomendados == 0 && !recomendadoPorMi)
		{
			html +=  "<a class='inline' href='#inline_content''>" + cuantosAmigos +" de tus amigos en facebook </a> lo recomiendan";
		}
		
		render html;
	}
	
	/**
	 * 
	 * @param idPerfil
	 * @return
	 */
	def envioEmailPerfil(Long idPerfil,String mensaje,String email)
	{
		User usuario = session.user;
		Profile perfil = perfilService.darPerfil(idPerfil);
		
		String emailMensaje = "Estan solicitando el servicio que ofreces:";
		emailMensaje += mensaje;
		emailMensaje += "\n\n Para contactar el usuario escribele a: " + email;
		
		//Enviar Email a perfil con info del usuario
		sendMail
		{
			to perfil.email
			subject "Solicitud de Servicio"
			body emailMensaje;
		}
		
		flash.message = "Se envio el mensaje satisfactoriamente."
		redirect(action:"profileUsuario", params:[usuario:perfil.usuario]);
	}
}
