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
			
			html +=  "<li class=\"anuncio\"><img src='http://cdn1.iconfinder.com/data/icons/crystalproject/48x48/filesystems/image.png' width='50' height='50'><span id=\"titulo\">";
			html += "<a rel=\"nofollow\" href=\"${g.createLink(controller:'controlAnuncio',action:'contarClick')}/"+idS+"\">" + v.anuncio.titulo + "</a></span>";
			html += "<br/><span id=\"link\">"+v.anuncio.urlWebsite+"</span>"
			html += "<span id=\"linea\">"+v.anuncio.descripcion+"</span>"				
			html +="</li>";
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
}
