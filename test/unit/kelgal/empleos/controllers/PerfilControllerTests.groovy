package kelgal.empleos.controllers

import kelgal.empleos.Categorias;
import kelgal.empleos.Certificado;
import kelgal.empleos.Ciudad;
import kelgal.empleos.PerfilController;
import kelgal.empleos.PerfilService;
import kelgal.empleos.Profile;
import kelgal.empleos.Review;
import grails.test.mixin.*
import groovy.mock.interceptor.MockFor
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

	public void setIt()
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
		
//		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
//		assert user.save() != null;
//
//		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO segundo", rating:5, profile:profile,user:user,fechaCreado:new Date());
//		assert review.save() != null;
		
		controller.perfilService = new PerfilService( );
	}
	
	public void setIt2( )
	{	
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena");
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		profile = new Profile(nombre:"Rafael",usuario:"Rafa",estadoUsuario:true,email:"rafa@gmail.com", certificado:cert, celular2:"3135851647",fechaCreado:new Date() ,password:"hola",celular:"3205721687", descripcion:"descripcion2", ciudad:ciudad, image: new byte[1000]);
		profile.addToCategorias(categoria);
		
		//		def perfilService = mockFor(PerfilService);
		//		perfilService.demand.darPerfil(1..1) { Long id -> return profile};
		//
		//		controller.perfilService =  perfilService.createMock( );
	}
	
	void testIndex( )
	{
		setIt();
		
		controller.index();
		assert view == "/perfil/index";		
	}
	
	void testProfile( )
	{	
		setIt();
		
		//Perfil Existe id
		controller.profile(profile.id);
		assert flash.message == null;

		response.reset( );
		
		//Perfil que no existe
		controller.profile(12345677);
		assert flash.message != null;

	}
	
	void testProfileUsuario( )
	{
		setIt();
		
		//Profile que existe
		params.usuario = "NOEXISTE";
		controller.profileUsuario();
		flash.message != null;
		assert view == "/perfil/users";
		
		response.reset( );
		
		//Profile que existe
		params.usuario = profile.usuario;
		controller.profileUsuario();
		assert model.profileInstance.nombre == profile.nombre;
	}
	
	void testUsers( )
	{		
		setIt();
		
		controller.users(categoria.id);
		
		assert model.profileInstanceTotal, 2;
		assert model.profileInstanceList.get(0).nombre, "Gustavo";
		assert model.profileInstanceList.get(1).nombre, "Rafael";
		
		//Buscar en categoria que no existe
		controller.users(12345);
		assert flash.message != null;
		
		response.reset( );
		
		controller.users();
		
	}
	
	void testBuscar( )
	{
		setIt();
		
		params.buscador = "descripcion1";
		
		controller.buscar();
		
		//Buscar por descripcion
		assert flash.message == null;
		assert model.profileInstanceTotal, 1;
		
		//Buscar por nombre
		params.buscador = "Rafa";
		controller.buscar();
		
		assert model.profileInstanceList.get(0).nombre, "Rafael";
		
		//Buscar algo que no existe
		params.buscador = "NO EXISTE";
		controller.buscar();
		
		assert flash.message != null;
	}
	

}
