package kelgal.empleos



import grails.test.mixin.*

import org.h2.engine.SessionFactory;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ComentariosController)
@Mock([Profile,Categorias,Review,Certificado,User])
class ComentariosControllerTests 
{
	private Categorias categoria;
	
	private Ciudad ciudad;
	
	private Certificado cert;
	
	private Profile profile;
	
	private Review review
	
	private User user;
	
	public setIt()
	{
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena");
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		profile.totalRating = 3;
		assert profile.save() != null;
		
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date());	
		
		assert user.save() != null;
		
		//Iniciar la session
		session.user = user;
		
		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO 123123" , rating:1, profile:profile,user:user, fechaCreado:new Date());
		assert review.save() != null;
		
		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO segundo", rating:5, profile:profile,user:user,fechaCreado:new Date());
		assert review.save() != null;
		
		
	}
	
	void testHandleComentario( )
	{
		
			
	}
	
	void testDeteleComentario( )
	{
		setIt();
		
		params.id = user.id;
		controller.misComentarios();
		
		assert profile.totalRating, 3;
		
		//Comentarios antes de borrar
		int total = model.totalComentarios;
		
		params.id = review.id;
		controller.deleteComentario();
		assert flash.message == null;
		
		//Comentarios despues de haber borrado (n-1)
		controller.misComentarios();
		assert model.totalComentarios, (total-1);
		
		assert profile.reviews.size(), (total-1);
		
		//Assert que se calculo bien el rating de nuevo
		assert profile.totalRating, 1;
		
	}
	
	void testCambiarPassword( )
	{
		
	}
	
	void testActualizarUsuario( )
	{
		
	}
	
}
