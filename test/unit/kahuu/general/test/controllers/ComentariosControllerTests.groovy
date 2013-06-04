package kahuu.general.test.controllers



import kahuu.general.Categorias;
import kahuu.general.Certificado;
import kahuu.general.Ciudad;
import kahuu.general.ComentarioService;
import kahuu.general.ComentariosController;
import kahuu.general.PerfilService;
import kahuu.general.Profile;
import kahuu.general.Review;
import kahuu.general.User;
import kahuu.general.UsuarioService;
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
	//--------------------------------------------------------------------------------------------------
	// Dominios
	//--------------------------------------------------------------------------------------------------
	
	private Categorias categoria;
	
	private Ciudad ciudad;
	
	private Certificado cert;
	
	private Profile profile;
	
	private Review review
	
	private User user;

	//--------------------------------------------------------------------------------------------------
	// Escenarios
	//--------------------------------------------------------------------------------------------------
	
	public setIt()
	{
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena");
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		profile.totalRating = 3;
		assert profile.save() != null;
		
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA", idFacebook:"");			
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
	
	
	public setIt2( )
	{
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena");
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		profile.totalRating = 3;
		assert profile.save() != null;
		
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA",idFacebook:"");
		assert user.save() != null;
		
		//Iniciar la session
		session.user = user;
		
		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO 123123" , rating:1, profile:profile,user:user, fechaCreado:new Date());
		assert review.save() != null;
		
		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO segundo", rating:5, profile:profile,user:user,fechaCreado:new Date());
		assert review.save() != null;
		
		ArrayList comentarios = new ArrayList();
		comentarios.add(review);
		
		def mockComentario = mockFor(ComentarioService,true);
		def mockPerfil = mockFor(PerfilService,true);
		def mockUsuario = mockFor(UsuarioService,true);
		
		
		mockPerfil.demand.darPerfil
		{
			def id -> return profile;
		}
		
		mockComentario.demand.crearComentario(0..1)
		{
			Profile pPerfiln, User pUser, String titulo, String texto, int rating -> return profile;
		}
		
		mockComentario.demand.darMisComentarios(0..5)
		{
			def id -> return comentarios;
		}
		
		mockComentario.demand.deleteComentario
		{
			def id -> return;
		}
		
		mockComentario.demand.editarComentario
		{
			def id, def titulo, def texto, def rating -> return profile;
		}
		
		mockUsuario.demand.cambiarPassword(2)
		{
			String password, Long id -> return user;
		}
		
		mockUsuario.demand.actualizarUsuario
		{
			String nombreNuevo, Long id -> return user;
		}
		
		controller.comentarioService = mockComentario.createMock();
		controller.perfilService = mockPerfil.createMock();
		controller.usuarioService = mockUsuario.createMock();
	}
	
	//--------------------------------------------------------------------------------------------------
	// Tests
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * 
	 */
	void testMisComentario( )
	{
		setIt2();
		
		controller.misComentarios();
		
		assert model.totalComentarios, user.reviews.size();
		assert view == "/comentarios/miscomentarios";
	}
	
	/**
	 * 
	 */
	void testCrearComentario( )
	{
		setIt2();
		
	}
	
	/**
	 * 
	 */
	void testHandleComentario( )
	{
		setIt2();
		
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
	
	/**
	 * 
	 */
	void testDeteleComentario( )
	{
		setIt2();
		
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
	
	/**
	 * 
	 */
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
	
	/**
	 * 
	 */
	void testHandleEditComentario( )
	{
		setIt2();
		
		assert profile.totalRating == 3;
		
		params.titulo = "nuevo";
		params.texto = "texto nuevo";
		params.rating = 1;
		
		controller.handleEditComentario(review.id);
		
		assert review.titulo, params.titulo;
		assert review.texto, params.texto;
		assert review.rating, params.rating;
		
		assert profile.totalRating,1;
		
	}
	
	/**
	 * 
	 */
	void testCambiarPassword( )
	{
		setIt2();
		
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
	
	/**
	 * 
	 */
	void testActualizarUsuario( )
	{
		setIt2();
		
		params.nombre = "Nuevo Nombre";
		
		controller.handleActualizarUsuario();
		
		assert session.user.nombre, params.nombre;
		assert flash.message != null;
	}
	
}
