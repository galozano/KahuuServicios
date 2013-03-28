package kahuu.general.test.services



import grails.test.mixin.*
import java.text.SimpleDateFormat
import kahuu.general.Categorias;
import kahuu.general.Certificado;
import kahuu.general.Ciudad;
import kahuu.general.PerfilService;
import kahuu.general.Profile;
import kahuu.general.Review;
import kahuu.general.User
import kahuu.general.exceptions.KahuuException
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PerfilService)
@Mock([Profile,Categorias,Ciudad,Review,User])
class PerfilServiceTests {

	//--------------------------------------------------------------------------------------------------
	// Servicios & Dominios
	//--------------------------------------------------------------------------------------------------
	
	PerfilService perfilService = new PerfilService( );
	
	private Categorias categoria;
	
	private Ciudad ciudad;
	
	private Certificado cert;
	
	private Profile profile;
	
	private User user;
	
	private Review review;
	
	//--------------------------------------------------------------------------------------------------
	// Escenarios
	//--------------------------------------------------------------------------------------------------
	
	public void setIt()
	{
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena");
		
		assert ciudad.save(flush: true) != null;
		
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		assert categoria.save(flush: true) != null;
		
		for(int i = 1; i < 20; i ++)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse("2009-12-"+i);
			
			profile = new Profile(nombre:"Nombre" + i,usuario:"Usuario" + i,password:"Passowrd",email:"email"+i+"@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:date1 ,celular:"3205721687", descripcion:"descripcion" + i, ciudad:ciudad, image: new byte[10]);
			profile.addToCategorias(categoria);
			
			assert profile.save(flush: true) != null;
		}
		
		profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date( ) ,celular:"3205721687", descripcion:"descripcion1", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		
		assert profile.save(flush: true) != null;
		
		profile = new Profile(nombre:"Rafael",usuario:"Rafa",estadoUsuario:true,email:"rafa@gmail.com", certificado:cert, celular2:"3135851647",fechaCreado:new Date( ) ,password:"hola",celular:"3205721687", descripcion:"descripcion2", ciudad:ciudad, image: new byte[1000]);
		profile.addToCategorias(categoria);
		
		assert profile.save(flush: true) != null;
		
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA",idFacebook:"");
		assert user.save(flush: true) != null;

		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO segundo", rating:5, profile:profile,user:user,fechaCreado:new Date());
		assert review.save(flush: true) != null;
	}
	
	//--------------------------------------------------------------------------------------------------
	// Tests
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * 
	 */
	void testDarCiudades()
	{
		setIt();
		
		def results = perfilService.darCiudades();	
		assert results.size( ), Ciudad.list().size();
	}
	
	/**
	 * 
	 */
	void testDarCategorias( )
	{
		setIt();
		
		def results = perfilService.darCategorias();
		assert results.size( ), Categorias.list().size();
	}
	
	/**
	 * 
	 */
	void testDarPerfil( )
	{
		setIt();

		Profile result = perfilService.darPerfil(profile.id);

		assert result.id, profile.id;
		assert result.nombre, profile.nombre;
		assert result.email, profile.email;
		assert result.celular, profile.celular;

	}
	
	/**
	 * 
	 */
	void testDarPerfilInvalido( )
	{
		setIt();
		
		Profile result = perfilService.darPerfil(123455);
		assert result == null;

	}
	
	/**
	 * 
	 */
	void testDarPerfilUsuario( )
	{
		setIt();
		
		Profile result = perfilService.darPerfilUsuario(profile.usuario);

		assert result.id, profile.id;
		assert result.nombre, profile.nombre;
		assert result.email, profile.email;
		assert result.celular, profile.celular;
	
	}
	
	/**
	 * 
	 */
	void testDarPerfilUsuarioInvalido( )
	{
		setIt();
		
		Profile result = perfilService.darPerfilUsuario("Invalido");
		assert result == null;
	}

	/**
	 * 
	 */
	void testPerfilesCategoria( )
	{
		setIt();
		
		def results = perfilService.perfilesCategoria(categoria.id);
		
		if(results == null)
		{
			fail("No puede llegar aca");
		}
		
		assert results.size(), 2;
		
		assert results.get(0).nombre, "Gustavo";
		assert results.get(1).nombre, "Rafael";	
		
		//TODO: hay que hacer caso para pobrar que se ordenan bien
	}
	
	/**
	 * 
	 */
	void testPerfilesCategoriaInvalido( )
	{
		setIt();
		
		def results = perfilService.perfilesCategoria(12345);
		assert results == null;

	}
	
	/**
	 * 
	 */
	void testBuscar( )
	{
		setIt();

		//Buscar por descripcion
		String buscador = "descripcion1";
		def results = perfilService.buscarPerfil(buscador);

		assert results.size(), 1;

		//Buscar por nombre
		buscador = "Rafa";
		results = perfilService.buscarPerfil(buscador);

		assert results.get(0).nombre, "Rafael";

	}
	
	/**
	 * 
	 */
	void testBuscarInvalido( )
	{	
		String buscador = "NO EXISTE";
		def results = perfilService.buscarPerfil(buscador);
		assert results.size()==0;
	}
	
	/**
	 * Prueba que los perfiles destacados sean los correctos
	 */
	void testPerfilesDestacados( )
	{
		setIt();
		
		List lista =  perfilService.perfilesDestacados( );
		
		assert lista.size() == 5;
		assert lista.get(0), profile;
	}
	
	/**
	 * Prueba que los perfiles sean los mas recientes y esten bien ordenados
	 */
	void testPerfilesRecientes( )
	{
		setIt();
		
		List lista = perfilService.perfilesRecientes();
		
		assert lista.size( ) == 5;
		
		assert lista.get(0).fechaCreado.compareTo(profile.fechaCreado) == 0;
		assert lista.get(1).fechaCreado.compareTo(profile.fechaCreado) == -1;
	}
}
