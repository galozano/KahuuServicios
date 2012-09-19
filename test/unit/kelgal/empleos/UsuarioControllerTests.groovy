package kelgal.empleos



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
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
		assert user.save() != null;
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
		setIt();
		
		//Password invalido
		params.email = "gus@gus.com";
		params.password = "invalido";
		
		controller.handleLogin();
		assert flash.message != null;
		assert response.redirectedUrl == "/usuario/login";
		assert session.user == null;
		
		response.reset();

		// Email no existe
		params.email = "gus@gus.com";
		params.password = "invalido";
		
		controller.handleLogin();
		assert flash.message != null;
		assert response.redirectedUrl == "/usuario/login";
		assert session.user == null;
		
		response.reset();
		
		//Usuario registrado con todo bien
		params.email = "gus@gus.com";
		params.password = "pass";
		
		controller.handleLogin();	
		//assert session.user != null;
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
		
		//Agrre esta en false
		params.nombre = "Rafa";
		params.email = "raf@gmail.com";
		params.agree = false;
		params.password = "nueva"
		params.confirm = "nueva";
		
		controller.handleRegistration();
		assert flash.message != null;
		
		// Todo perfecto
		params.nombre = "Rafa";
		params.email = "raf@gmail.com";
		params.agree = true;
		params.password = "nueva"
		params.confirm = "nueva";
		
		controller.handleRegistration();
	}
}
