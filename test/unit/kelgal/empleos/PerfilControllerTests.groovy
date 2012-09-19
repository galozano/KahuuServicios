package kelgal.empleos

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
	
	/**
	 * Set UP el scenario
	 */
	public void setIt() 
	{	
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena");
		
		assert ciudad.save() != null;
		
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		assert categoria.save(flush: true) != null;
		
		profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"descripcion1", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		
		assert profile.save() != null;
		
		profile = new Profile(nombre:"Rafael",usuario:"Rafa",estadoUsuario:true,email:"rafa@gmail.com", certificado:cert, celular2:"3135851647",fechaCreado:new Date() ,password:"hola",celular:"3205721687", descripcion:"descripcion2", ciudad:ciudad, image: new byte[1000]);
		profile.addToCategorias(categoria);
		
		assert profile.save() != null;
		
//		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
//		assert user.save() != null;
//		
//		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO segundo", rating:5, profile:profile,user:user,fechaCreado:new Date());
//		assert review.save() != null;
	}
	
	void testIndex( )
	{
		setIt();
		
		controller.index();
		assert view == "/perfil/index";		
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
	
	void testProfile( )
	{ 	
		setIt();
		
		//Perfil que no existe
		controller.profile(877);
		assert flash.message != null;
		
		
		//Profile que existe
		params.usuario = profile.usuario;
		controller.profileUsuario();	
		assert model.profileInstance.nombre == profile.nombre;  
	}
}
