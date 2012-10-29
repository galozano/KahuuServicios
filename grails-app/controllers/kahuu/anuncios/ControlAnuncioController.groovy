package kahuu.anuncios

import kahuu.general.AnuncioService;

class ControlAnuncioController 
{
	//------------------------------------------------------------------------------------------
	// Servicios
	//------------------------------------------------------------------------------------------
	
	AnuncioService anuncioService;
	
	//------------------------------------------------------------------------------------------
	// Metodos Anuncio
	//------------------------------------------------------------------------------------------
	
	/**
	 * Retorna el html con la lista de anuncios
	 * @return-texto html
	 */
	def anuncios( )
	{
		List vistaAnuncios = anuncioService.darAnuncios("principal",request.getRemoteAddr());	
		String html = "<div id=\"box\"><div id=\"tituloBox\">Anuncios</div><ul>";
		
		for (Vista v : vistaAnuncios) 
		{	
			String idS = Long.toString(v.id);
			
			if(v.anuncio != null)
			{
				html +=  "<li class=\"anuncio\"><span id=\"titulo\">";
				html += "<a rel=\"nofollow\" href=\"${g.createLink(controller:'controlAnuncio',action:'contarClick')}/"+idS+"\">" + v.anuncio.titulo + "</a></span>";
				html += "<br/><span id=\"link\">"+v.anuncio.urlWebsite+"</span>"
				
				if(v.anuncio.image != null && v.anuncio.image.size() > 0)
				{
					html += "<img src='${createLink(controller:'controlAnuncio', action:'darFoto', id: v.anuncio.id)}' width='50' height='50'/>"
				}
				
				html += "<span id=\"linea\">"+v.anuncio.descripcion+"</span>"
				html +="</li>";
			}
		}
		
		html += "</ul></div>"
		
		render html;
	}
	
	
	/**
	 * Cuenta un click con la llegada de un request
	 */
	def contarClick( )
	{
		Anuncio anuncio = anuncioService.contarClick(params.id.toLong());	
		redirect(url: anuncio.urlWebsite);
	} 
	
	/**
	 * Retorna la foto de un anuncio dado
	 * @return
	 */
	def darFotoAnuncio( )
	{
		Anuncio anuncio = anuncioService.darAnuncio(params.id);
		
		if(anuncio != null)
		{
			byte[] image = anuncio.image;
			response.outputStream << image;
		}
	}
		
}
