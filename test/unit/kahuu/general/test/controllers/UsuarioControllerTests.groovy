package kahuu.general.test.controllers

import kahuu.general.Categorias;
import kahuu.general.Certificado;
import kahuu.general.Profile;
import kahuu.general.Review;
import kahuu.general.User;
import kahuu.general.UsuarioController;
import kahuu.general.UsuarioService;
import grails.test.mixin.*
import org.junit.*


/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UsuarioController)
@Mock([Profile,Categorias,Review,Certificado,User])

class UsuarioControllerTests 
{
	User user;
	
	void setIt()
	{
		String pass = "pass";
		
		user = new User(nombre:"Gus",password:pass.encodeAsSHA1(), email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA", idFacebook:"");
		assert user.save() != null;
		
		controller.usuarioService = new UsuarioService();
	}
	
	void setIt2()
	{
		def mockUserService = mockFor(UsuarioService);
		
		String pass = "pass";
		user = new User(nombre:"Gus",password:pass.encodeAsSHA1(), email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
		
		mockUserService.demand.loginUsuario(0..3)
		{
			String usuario, String password -> if(password.equals("invalido")) return null else return user;
		}
		
		controller.usuarioService = mockUserService.createMock();
	}
	
	void testLogin( )
	{
		setIt();
		
		session.user = user;
		//Usuario log in
		controller.login();
		assert response.redirectedUrl == "/comentarios/misComentarios";
		
		//Visitante
		session.user = null;
		controller.login();
		assert view == "/usuario/login";
		
	}
	
	void testHandleLogin()
	{
		setIt2();
		
		//Password invalido
		params.email = "gus@gus.com";
		params.password = "invalido";
		
		controller.handleLogin();
		assert flash.message != null;
		assert view == "/usuario/login";
		assert session.user == null;
		
		response.reset();

		// Email no existe
		params.email = "gus@gus.com";
		params.password = "invalido";
		
		controller.handleLogin();
		assert flash.message != null;
		assert view == "/usuario/login";
		assert session.user == null;
		
		response.reset();
		flash.message = null;
		
		//Usuario registrado con todo bien
		params.email = "gus@gus.com";
		params.password = "pass";
		
		controller.handleLogin();	
		assert session.user != null;
		assert flash.message == null;
		assert response.redirectedUrl == "/comentarios/misComentarios";
	}
	
	void testRegister()
	{
		
	}
	
	void testHandleRegistration()
	{
		//Password no son iguales
		params.nombre = "Rafa";
		params.email = "raf@gmail.com";
		params.agree = true;
		params.password = "nueva"
		params.confirm = "nueva2";
		
		controller.handleRegistration();
		assert flash.message != null;
		assert view == "/usuario/login"
		
		//Agrre esta en false
		params.nombre = "Rafa";
		params.email = "raf@gmail.com";
		params.agree = false;
		params.password = "nueva"
		params.confirm = "nueva";
		
		controller.handleRegistration();
		assert flash.message != null;
		assert view == "/usuario/login"
		
		// Todo perfecto
		params.nombre = "Rafa";
		params.email = "raf@gmail.com";
		params.agree = true;
		params.password = "nueva"
		params.confirm = "nueva";
		
		controller.handleRegistration();
		
		assert view == "/usuario/login"
	}
}
