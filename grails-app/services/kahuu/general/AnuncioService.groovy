package kahuu.general

import kahuu.anuncios.Anuncio;
import kahuu.anuncios.Vista;
import org.springframework.transaction.annotation.Transactional;



class AnuncioService 
{	
	/**
	 * Crea un nuevo click para un anuncio en especifico
	 * @param idAnuncio
	 * @return
	 */
	@Transactional
	def contarClick(Long idVista)
	{		
		Vista vista = Vista.get(idVista);
		Anuncio anuncio = vista.anuncio;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);
		
		//Ver si existe otro click en los ultimos 10 minutos con la misma ip
		def c = Vista.createCriteria();
		
		def results = c {
			between("fechaCreado", cal.getTime() , new Date())
			eq("click", true)
			eq("ip", vista.ip)
		};
	
		//Solo si no existe otro click en los 10 minutos pasados
		if(results.size() == 0)
		{
			vista.click = true;
			
			anuncio.presupuesto = anuncio.presupuesto - vista.precio;
			anuncio.numeroClicks = anuncio.numeroClicks + 1;
			
			vista.save();
			anuncio.save();
		}
		
		return anuncio;
	}	
		
	/**
	 * Muestra los anuncios sorteados de una pagina en especifico
	 * @param pagina- la pagina donde se va a mostrar los anuncios
	 * @return- los 5 anuncios
	 */
	def darAnuncios(String nombreCategoria, String ip)
	{
		def query = Anuncio.where {
			estado == true
			presupuesto > costoPClick
		};
	
		List anuncios = query.list();
		List anunciosOrdenados = anuncios.sort();
		
		if(anunciosOrdenados.size() > 3)
		{
			anunciosOrdenados = anunciosOrdenados.subList(0, 3);
		}
		
		return insertarAnunciosVistos(anunciosOrdenados, ip);
	}
	
	/**
	 * Actualiza la base de datos, incrementando una vez el numero de veces que ha salido el anuncio
	 * @param listaAnuncios- la lista de anuncios que se va a actualizar
	 * @return- lista de vista para mostrar en la pagina
	 */
	@Transactional
	def insertarAnunciosVistos(List listaAnuncios, String ip)
	{
		List listaVista = new ArrayList();
		
		for(int i = 0; i < listaAnuncios.size() ; i++)
		{
			Anuncio anuncio = listaAnuncios.get(i);
			anuncio.numeroVistas = anuncio.numeroVistas + 1;
			
			Vista vistaNueva = new Vista(ip:ip, fechaCreado:new Date(), anuncio:anuncio,precio:anuncio.costoPClick,click:false);
			
			anuncio.save( );
			vistaNueva.save( );
			listaVista.add(vistaNueva);
		}
		
		return listaVista;
	}
	
	
	/**
	 * Retorna todos los anuncios de un usuario
	 * @param usuario- usuario
	 * @return lista de anuncios
	 */
	def darAnunciosUsuario(User usuario)
	{
		return Anuncio.findAllByUsuario(usuario);
	}
	
	/**
	 * Retorna el anuncio dado su id
	 * @param idAnuncio- id del anuncio
	 * @return- el auncio
	 */
	def darAnuncio(Long idAnuncio)
	{
		Anuncio anuncio = Anuncio.get(idAnuncio);		
		return !anuncio ? null: anuncio; 
	}
}