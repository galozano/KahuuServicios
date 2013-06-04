
package kahuu.general.test.services;
import kahuu.general.exceptions.KahuuException;
import kahuu.general.utils.Password;
import kahuu.general.User;
import kahuu.general.UsuarioService;
import grails.test.MockUtils;
import grails.test.mixin.*
import org.junit.*
import org.codehaus.groovy.grails.plugins.codecs.SHA1Codec


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UsuarioService)
@Mock([User])
class UsuarioServiceTests 
{
	//--------------------------------------------------------------------------------------------------
	// Servicios & Dominios
	//--------------------------------------------------------------------------------------------------
	
	UsuarioService usuarioService = new UsuarioService();
	
	User usuarioPrueba;
		
	//--------------------------------------------------------------------------------------------------
	// Escenarios
	//--------------------------------------------------------------------------------------------------
	
	void setUp()
	{
		String pass = "pass";
		mockCodec(SHA1Codec);
		
		usuarioPrueba = new User(nombre:"Gus",password:pass.encodeAsSHA1(), email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA", idFacebook:"");
		assert usuarioPrueba.save() != null;
	}
	
	//--------------------------------------------------------------------------------------------------
	// Tests
	//--------------------------------------------------------------------------------------------------
	
	@Test
	void testLoginPasswordInvalido()
	{	
		//Password Invalido
		User usuarioPrueba =	usuarioService.loginUsuario("gus@gus.com","invalido")
		assert usuarioPrueba == null;
	}
	
	@Test
	void testLoginEmailInvalido()
	{
		User usuarioPrueba = usuarioService.loginUsuario("gus@gu2s.com","invalido")
		assert usuarioPrueba == null;
	}
	
	@Test
	void testLoginPassword()
	{
		//Usuario registrado con todo bien
		User usuarioPrueba = 	usuarioService.loginUsuario("gus@gus.com","pass")
		assert usuarioPrueba.nombre, this.usuarioPrueba.nombre;
	}
	
	@Test
	void testRegist( )
	{
		//Password no son iguales
		String nombre = "Rafa";
		String email = "raf@gmail.com";
		String password = "nueva"

		//MockUtils(Email);
		def usuarioPrueba = usuarioService.registration(nombre, email, password);		
	
	}
	
	@Test
	void testCambiarPassword( )
	{
		String nuevo = "nuevaPass";	
	
		usuarioService.cambiarPassword(nuevo, usuarioPrueba.id);		
		assert nuevo.encodeAsSHA1(), usuarioPrueba.password;	
	}
	
	@Test
	void testActualizarUsuario( )
	{
		String nuevoNombre = "Nuevo Nombre";
		
		usuarioService.actualizarUsuario(nuevoNombre, usuarioPrueba.id);
		assert nuevoNombre, usuarioPrueba.nombre;
	}
	
	@Test
	void testVerificarEmail( )
	{	
		def verificar = usuarioService.verificarEmail(usuarioPrueba.id, usuarioPrueba.keyConfirmar);
		
		assert verificar == true;	
		assert usuarioPrueba.activated, true;
		assert usuarioPrueba.keyConfirmar.equals("");

	}
	
	@Test
	void testVerificarInvalido( )
	{
		def verificar = usuarioService.verificarEmail(usuarioPrueba.id, "INVALIDO");
		assert verificar == false;
	}
	
	@Test
	void testdarUsuarioId( )
	{
		def usuario = usuarioService.darUsuarioId(usuarioPrueba.id);
		assert usuario.id == usuarioPrueba.id;
	}

	/**	
	void testOlvidoClave( )
	{
		setIt();
		
		try
		{
			def usuario = usuarioService.olvidoClave(usuarioPrueba.email);
			
			
		}
		catch(KahuuException e)
		{
			fail "No deberia llegar aca";
		}
	} 
	*/
}
