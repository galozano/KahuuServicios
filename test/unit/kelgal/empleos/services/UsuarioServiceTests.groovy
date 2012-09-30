
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
	def usuarioService = new UsuarioService();
	
	User user;
		
	void setIt()
	{
		String pass = "pass";
		
		user = new User(nombre:"Gus",password:pass.encodeAsSHA1(), email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
		assert user.save() != null;
		
	}
	
	void testHandleLogin()
	{
		setIt();
		
		//Password Invalido
		try
		{
			usuarioService.loginUsuario("gus@gus.com","invalido")
			fail "No deberia llegar hasta aca";
		}
		catch(KahuuException e)
		{
			//Debe existir un error
		}

		// Email no existe
		try
		{
			usuarioService.loginUsuario("gus@gu2s.com","invalido")
			fail "No deberia llegar hasta aca";
		}
		catch(KahuuException e)
		{
			//Debe existir un error
		}

		//Usuario registrado con todo bien
		try
		{
			usuarioService.loginUsuario("gus@gus.com","pass")
		}
		catch(KahuuException e)
		{
			fail "No deberia llegar aca";
		}

	}
}
