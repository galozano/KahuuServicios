package kahuu.general.test.controllers

import kahuu.general.Categorias;
import kahuu.general.Certificado;
import kahuu.general.Ciudad;
import kahuu.general.PerfilController;
import kahuu.general.PerfilService;
import kahuu.general.Profile;
import kahuu.general.Review;
import grails.plugin.searchable.SearchableService
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

@TestFor(PerfilController)
@Mock([Profile,Categorias,Ciudad,Review])
class PerfilControllerTests 
{	
	private Categorias categoria;
	
	private Ciudad ciudad;
	
	private Certificado cert;
	
	private Profile profile;

	//--------------------------------------------------------------------------------------------------
	// Escenarios
	//--------------------------------------------------------------------------------------------------

	void setUp()
	{	
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena");
		
		assert ciudad.save(flush: true) != null;
		
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		assert categoria.save(flush: true) != null;
		
		profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"descripcion1", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		
		def mockPerfilServiceFactory = mockFor(PerfilService);
		
		ArrayList lista = new ArrayList();
		lista.add(profile);
		
		mockPerfilServiceFactory.demand.darCiudades(0..2)
		{
			return new ArrayList();
		}
		
		mockPerfilServiceFactory.demand.darCategorias(0..2)
		{
			ArrayList listaCat = new ArrayList();
			listaCat.add(categoria);
			return listaCat;
		}
		
		mockPerfilServiceFactory.demand.perfilesDestacados(0..2)
		{
			return lista;
		}
		
		mockPerfilServiceFactory.demand.darPerfil(0..2)
		{
			def id -> if(id == 12345 ) return null else return profile;
		}
		
		mockPerfilServiceFactory.demand.darPerfilUsuario(0..2)
		{
			def usuario -> if(usuario.equals("NOEXISTE")) return null else return profile;
		}
		
		mockPerfilServiceFactory.demand.darReviewsPerfil(0..2)
		{
			def perfil -> return new ArrayList();
		}
				
		mockPerfilServiceFactory.demand.perfilesCategoria
		{
			def id -> if(id == 12345) return null else return lista;
		}
		
		controller.perfilService = mockPerfilServiceFactory.createMock();
		
	}
	
	//----------------------------------------------------------------------------------------------------
	// Tests
	//----------------------------------------------------------------------------------------------------
	
	@Test
	void testIndex( )
	{		
		controller.index();
		assert view == "/perfil/index";		
	}
	
	@Test
	void testPrincipal( )
	{		
		controller.principal();		
		assert view == "/perfil/principal";
	}
	
	@Test
	void testProfile( )
	{	
		//Perfil Existe id
		controller.profile(profile.id);
		assert flash.message == null;
		
		response.reset();
		
		//Perfil que no existe
		controller.profile(12345);
		assert flash.message != null;
	}
	
	@Test
	void testProfileUsuario( )
	{
		//Profile que existe
		params.usuario = profile.usuario;
		controller.profileUsuario();
		assert model.profileInstance.nombre == profile.nombre;
	}
	
	@Test
	void testProfileUsuarioInvalido()
	{
		//Profile no que existe
		params.usuario = "NOEXISTE";
		controller.profileUsuario();
		flash.message != null;
		assert response.redirectedUrl == "/perfil/perfiles";
	}
	
	@Test
	void testUsers( )
	{		
		controller.perfiles(categoria.id);
		
		assert model.profileInstanceTotal, 1;
		assert model.profileInstanceList.get(0).nombre, "Gustavo";
	}
	
	@Test
	void testUsersInvalido( )
	{
		//Buscar en categoria que no existe
		controller.perfiles(12345);
		
		assert flash.message != null;
	}
	
	/**
	 * 
	 */
	@Test
	void testBuscar( )
	{	
		params.q = "descripcion1";
		
		controller.buscar();
		
		//Buscar por descripcion
		assert flash.message == null;
		assert model.profileInstanceTotal, 1;
		
		//Buscar por nombre
		params.q = "Rafa";
		controller.buscar();
		
		assert model.profileInstanceList.get(0).nombre, "Rafael";
		
		//Buscar algo que no existe
		params.q = "NO EXISTE";
		controller.buscar();
		
		assert flash.message != null;
	}
	
	@Test
	void testEnvioEmailPerfil( )
	{
		controller.envioEmailPerfil(profile.id);
		assert render.toString() == "Confirmado";
	}
	

}
