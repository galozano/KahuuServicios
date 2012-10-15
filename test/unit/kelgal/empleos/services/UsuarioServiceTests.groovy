
package kelgal.empleos.services;
import kelgal.empleos.exceptions.KahuuException;
import kelgal.empleos.Password
import kelgal.empleos.User;
import kelgal.empleos.UsuarioService;
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
	
	void setIt()
	{
		String pass = "pass";
		mockCodec(SHA1Codec);
		
		usuarioPrueba = new User(nombre:"Gus",password:pass.encodeAsSHA1(), email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
		assert usuarioPrueba.save() != null;
	}
	
	//--------------------------------------------------------------------------------------------------
	// Tests
	//--------------------------------------------------------------------------------------------------
	
	void testLoginPasswordInvalido()
	{
		setIt();
		
		//Password Invalido
		User usuarioPrueba =	usuarioService.loginUsuario("gus@gus.com","invalido")
		assert usuarioPrueba == null;
	}
	
	void testLoginEmailInvalido()
	{
		setIt();
		
		User usuarioPrueba = usuarioService.loginUsuario("gus@gu2s.com","invalido")
		assert usuarioPrueba == null;
	}
	
	void testLoginPassword()
	{
		setIt();
		
		//Usuario registrado con todo bien
		User usuarioPrueba = 	usuarioService.loginUsuario("gus@gus.com","pass")
		assert usuarioPrueba.nombre, this.usuarioPrueba.nombre;
	}
	
	void testRegist( )
	{
		setIt();
		
		//Password no son iguales
		String nombre = "Rafa";
		String email = "raf@gmail.com";
		String password = "nueva"

		try
		{
			//MockUtils(Email);
			def usuarioPrueba = usuarioService.registration(nombre, email, password);
			
		}		
		catch(KahuuException e)
		{
			fail "No deberia llegar aca";
		}		
	}
	
	void testCambiarPassword( )
	{
		setIt();
		
		String nuevo = "nuevaPass";
		
		try
		{
			usuarioService.cambiarPassword(nuevo, usuarioPrueba.id);		
			assert nuevo.encodeAsSHA1(), usuarioPrueba.password;
		}
		catch(KahuuException e)
		{
			fail "No deberia llegar aca";
		}
		
	}
	
	void testActualizarUsuario( )
	{
		setIt();
		
		String nuevoNombre = "Nuevo Nombre";
		
		try
		{
			usuarioService.actualizarUsuario(nuevoNombre, usuarioPrueba.id);
			assert nuevoNombre, usuarioPrueba.nombre;
		}
		catch(KahuuException e)
		{
			fail "No deberia llegar aca";
		}
		
	}
	
	void testVerificarEmail( )
	{
		setIt();
		
		try
		{
			def verificar = usuarioService.verificarEmail(usuarioPrueba.id, usuarioPrueba.keyConfirmar);
			assert verificar == true;	
			assert usuarioPrueba.activated, true;
			assert usuarioPrueba.keyConfirmar.equals("");
		}
		catch(KahuuException e)
		{
			fail "No deberia llegar aca";
		}
	}
	
	void testVerificarInvalido( )
	{
		setIt();
		
		try
		{
			def verificar = usuarioService.verificarEmail(usuarioPrueba.id, "INVALIDO");
			assert verificar == false;
		}
		catch(KahuuException e)
		{
			fail "No deberia llegar aca";
		}
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
