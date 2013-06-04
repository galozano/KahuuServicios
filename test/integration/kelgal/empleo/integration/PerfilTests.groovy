package kelgal.empleo.integration

import static org.junit.Assert.*

import grails.plugin.searchable.SearchableService
import kahuu.general.Categorias;
import kahuu.general.Certificado;
import kahuu.general.Ciudad;
import kahuu.general.PerfilController;
import kahuu.general.PerfilService
import kahuu.general.Profile;

import org.junit.*
import grails.test.mixin.*


@TestFor(PerfilController)
@Mock([Profile,Categorias,Ciudad])
class PerfilTests {

	PerfilController perfilController;
	
	private Categorias categoria;
	
	private Ciudad ciudad;
	
	private Certificado cert;
	
	private Profile profile;
	
	//--------------------------------------------------------------------------------------------------
	// Escenarios
	//--------------------------------------------------------------------------------------------------
	
	
    @Before
    void setUp() 
	{
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena");
		
		assert ciudad.save(flush: true) != null;
		
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		assert categoria.save(flush: true) != null;
		
		profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"descripcion1", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		
		assert profile.save(flush: true) != null;
		
		profile = new Profile(nombre:"Rafael",usuario:"Rafa",estadoUsuario:true,email:"rafa@gmail.com", certificado:cert, celular2:"3135851647",fechaCreado:new Date() ,password:"hola",celular:"3205721687", descripcion:"descripcion2", ciudad:ciudad, image: new byte[1000]);
		profile.addToCategorias(categoria);
		
		assert profile.save(flush: true) != null;
		
        perfilController = new PerfilController();
		
		perfilController.perfilService = new PerfilService( );
		perfilController.searchableService = new SearchableService( );
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

	//--------------------------------------------------------------------------------------------------
	// Tests
	//--------------------------------------------------------------------------------------------------
	
    @Test
    void testIndex() 
	{
		perfilController.index();
		assert view == "/perfil/index";
    }
	
	@Test
	void testPrincipal( )
	{	
		perfilController.principal();
		assert view == "/perfil/principal";
	}
	
	@Test
	void testProfile( )
	{	
		//Perfil Existe id
		perfilController.profile(profile.id);
		assert flash.message == null;
		
		response.reset();
		
		//Perfil que no existe
		perfilController.profile(12345);
		assert flash.message != null;
	}
	
	@Test
	void testProfileUsuario( )
	{
		//Profile que existe
		params.usuario = profile.usuario;
		perfilController.profileUsuario();
		assert model.profileInstance.nombre == profile.nombre;
	}
	
	@Test
	void testProfileUsuarioInvalido()
	{
		//Profile no que existe
		params.usuario = "NOEXISTE";
		perfilController.profileUsuario();
		flash.message != null;
		
		assert response.redirectedUrl == "/perfil/perfiles";
	}
	
	@Test
	void testUsers( )
	{
		perfilController.perfiles(categoria.id);
		
		assert model.profileInstanceTotal, 1;
		assert model.profileInstanceList.get(0).nombre, "Gustavo";
	}
	
	@Test
	void testUsersInvalido( )
	{	
		//Buscar en categoria que no existe
		perfilController.perfiles(12345);
		
		assert flash.message != null;
	}
	
	@Test
	void testBuscar( )
	{
		params.q = "descripcion1";
		
		perfilController.buscar();
		
		//Buscar por descripcion
		assert flash.message == null;
		assert model.profileInstanceTotal, 1;
		
		//Buscar por nombre
		params.q = "Rafa";
		perfilController.buscar();
		
		assert model.profileInstanceList.get(0).nombre, "Rafael";
		
		//Buscar algo que no existe
		params.q = "NO EXISTE";
		perfilController.buscar();
		
		assert flash.message != null;
	}
}
