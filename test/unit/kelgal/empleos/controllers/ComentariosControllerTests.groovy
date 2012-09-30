package kelgal.empleos.controllers



import kelgal.empleos.Categorias;
import kelgal.empleos.Certificado;
import kelgal.empleos.Ciudad;
import kelgal.empleos.ComentarioService;
import kelgal.empleos.ComentariosController;
import kelgal.empleos.PerfilService;
import kelgal.empleos.Profile;
import kelgal.empleos.Review;
import kelgal.empleos.User;
import kelgal.empleos.UsuarioService;
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
		
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");			
		assert user.save() != null;
		
		//Iniciar la session
		session.user = user;
		
		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO 123123" , rating:1, profile:profile,user:user, fechaCreado:new Date());
		assert review.save() != null;
		
		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO segundo", rating:5, profile:profile,user:user,fechaCreado:new Date());
		assert review.save() != null;
		
		controller.comentarioService = new ComentarioService();
		controller.perfilService = new PerfilService();
		controller.usuarioService = new UsuarioService();
	}
	
	void testHandleComentario( )
	{
		setIt();
		
		//test ideal
		int inicial = profile.totalRating;
		
		params.perfilId  = profile.id;
		params.titulo = "Prueba Titulo";
		params.texto = "prueba texto kjsdfgh";
		params.rating = 4;
		
		controller.handleComentario();
		
		assert profile.totalRating, 3;
		assert profile.reviews.size(), 3;
		
		//Test titulo muy largo	
	}
	
	void testDeteleComentario( )
	{
		setIt();
		
		params.id = user.id;
		controller.misComentarios();
		
		assert profile.totalRating, 3;
		
		//Scar el total antes de borrar el comentario
		int total = model.totalComentarios;
		
		//Borrar el comentario
		params.id = review.id;
		controller.deleteComentario();
		assert flash.message == null;
		
		//Comentarios despues de haber borrado (n-1)
		controller.misComentarios();
		assert model.totalComentarios, (total-1);
		
		assert profile.reviews.size(), (total-1);
		
		//Ver si el rating se calculo bien
		assert profile.totalRating, 1;
		
	}
	
	void testEditComentario( )
	{
		setIt();
		
		//Test Comentario que no existe
		controller.editarComentario(1245);
		
		assert flash.message != null;
		assert response.redirectedUrl == "/comentarios/misComentarios";
		
		response.reset();
		
		//Test que existe comentario
		controller.editarComentario(review.id);
		assert view == "/comentarios/editcomentario";
		assert model.comentarioInstance, review;
	}
	
	void testHandleEditComentario( )
	{
		setIt();
		
		params.titulo = "nuevo";
		params.texto = "texto nuevo";
		params.rating = 1;
		
		controller.handleEditComentario(review.id);
		
		assert review.titulo, params.titulo;
		assert review.texto, params.texto;
		assert review.rating, params.rating;
		
		assert profile.totalRating == 1;
		
	}
	
	void testCambiarPassword( )
	{
		setIt();
		
		//Test condiciones ideales
		params.password = "nuevapass"
		params.confirm  = "nuevapass";
		
		controller.handleCambiarPassword();
		
		assert model.userInstance.password, params.password.encodeAsSHA1();;
		
		//Test Passwords diferentes
		params.password = "nuevapass"
		params.confirm  = "diferente";
		
		controller.handleCambiarPassword();
	
		assert flash.message != null;
			
	}
	
	void testActualizarUsuario( )
	{
		setIt();
		
		params.nombre = "Nuevo Nombre";
		
		controller.handleActualizarUsuario();
		
		assert session.user.nombre, params.nombre;
		assert flash.message != null;
	}
	
}
