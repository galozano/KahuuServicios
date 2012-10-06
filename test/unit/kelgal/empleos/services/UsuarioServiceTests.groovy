
package kelgal.empleos.services;
import kelgal.empleos.exceptions.KahuuException;
import kelgal.empleos.Password
import kelgal.empleos.User;
import kelgal.empleos.UsuarioService;
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
	UsuarioService usuarioService = new UsuarioService();
	
	User user;
		
	void setIt()
	{
		String pass = "pass";
		mockCodec(SHA1Codec);
		
		user = new User(nombre:"Gus",password:pass.encodeAsSHA1(), email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
		assert user.save() != null;
	}
	
	void testHandleLoginPasswordInvalido()
	{
		setIt();
		
		//Password Invalido
		User user =	usuarioService.loginUsuario("gus@gus.com","invalido")
		assert user == null;
	}
	
	void testHandleLoginEmailInvalido()
	{
		setIt();
		
		User user = usuarioService.loginUsuario("gus@gu2s.com","invalido")
		assert user == null;
	}
	
	void testHandleLoginPassword()
	{
		setIt();
		
		//Usuario registrado con todo bien
		User user = 	usuarioService.loginUsuario("gus@gus.com","pass")
		assert user.nombre, this.user.nombre;
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
			def user = usuarioService.registration(nombre, email, password);
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
			usuarioService.cambiarPassword(nuevo, user.id);		
			assert nuevo.encodeAsSHA1(), user.password;
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
			usuarioService.actualizarUsuario(nuevoNombre, user.id);
			assert nuevoNombre, user.nombre;
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
			def verificar = usuarioService.verificarEmail(user.id, user.keyConfirmar);
			assert verificar == true;	
			assert user.activated, true;
			assert user.keyConfirmar.equals("");
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
			def verificar = usuarioService.verificarEmail(user.id, "INVALIDO");
			assert verificar == false;
		}
		catch(KahuuException e)
		{
			fail "No deberia llegar aca";
		}
	}
}
