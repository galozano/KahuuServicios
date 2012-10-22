package kahuu.general.test.services



import kahuu.anuncios.Anuncio
import kahuu.anuncios.Vista
import kahuu.general.AnuncioService;
import kahuu.general.User
import grails.test.mixin.*
import org.junit.*


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AnuncioService)
@Mock([User,Anuncio,Vista])
class AnuncioServiceTests 
{	
	//--------------------------------------------------------------------------------------------------
	// Servicios & Dominios
	//--------------------------------------------------------------------------------------------------
	
	Anuncio anuncio;
	
	Vista click;
	
	User user;
	
	AnuncioService anuncioService = new AnuncioService();
	
	//--------------------------------------------------------------------------------------------------
	// Escenarios
	//--------------------------------------------------------------------------------------------------
	
	public setIt( )
	{
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
		assert user.save() != null;
		
		anuncio = new Anuncio(titulo:"titulo1",urlWebsite:"url1",descripcion:"descripcion1",costoPClick:200,fechaCreado:new Date(), presupuesto:50000,numeroClicks:0,numeroVistas:1,estado:false,usuario:user);
		assert anuncio.save() != null;
		
		anuncio = new Anuncio(titulo:"titulo2",urlWebsite:"url2",descripcion:"descripcion2",costoPClick:200,fechaCreado:new Date(),  presupuesto:100,numeroClicks:1,numeroVistas:2,estado:true,usuario:user);
		assert anuncio.save() != null;
		
		anuncio = new Anuncio(titulo:"titulo3",urlWebsite:"url3",descripcion:"descripcion3",costoPClick:200,fechaCreado:new Date(),  presupuesto:50000,numeroClicks:1,numeroVistas:21,estado:true,usuario:user);
		assert anuncio.save() != null;
		
		user = new User(nombre:"Gus1",password:"pass1", email:"gus1@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA1");
		assert user.save() != null;
		
		anuncio = new Anuncio(titulo:"titulo4",urlWebsite:"url4",descripcion:"descripcion4",costoPClick:200, fechaCreado:new Date(), presupuesto:50000,numeroClicks:5,numeroVistas:10,estado:true,usuario:user);
		assert anuncio.save() != null;
		
		
	}
	
	/**
	 * Test que la lista de anuncio salga correctamente y que las vista se cree 
	 */
    void testDarAnuncios()
	{
		setIt();
		
      	List listaVistas = anuncioService.darAnuncios("ejemplo","200.10.10.10");
		
		assert listaVistas.size() == 2;	
		
		System.out.println(listaVistas.get(0).anuncio.titulo);
			
		assert listaVistas.get(0).id,anuncio.id;
    }
	
	/**
	 * Test que se cuente el click
	 */
	void testContarClick( )
	{
		setIt();
		
		int clicks = this.anuncio.numeroClicks;
		
		List listaVistas = anuncioService.darAnuncios("ejemplo","200.10.10.10");
		
		System.out.println(listaVistas.get(0).anuncio.titulo);
		
		Anuncio anuncio = anuncioService.contarClick(listaVistas.get(0).id);
		
		System.out.println(anuncio.titulo);
		assert clicks + 1, anuncio.numeroClicks;
		
		
	}
}
