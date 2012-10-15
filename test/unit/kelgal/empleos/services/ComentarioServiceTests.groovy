package kelgal.empleos.services



import kelgal.empleos.Categorias;
import kelgal.empleos.Certificado;
import kelgal.empleos.Ciudad;
import kelgal.empleos.ComentarioService;
import kelgal.empleos.Profile;
import kelgal.empleos.Review;
import kelgal.empleos.User;
import kelgal.empleos.exceptions.KahuuException;
import grails.test.mixin.*
import org.junit.*


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ComentarioService)
@Mock([Profile,Categorias,Review,Certificado,User])
class ComentarioServiceTests
{	
	//--------------------------------------------------------------------------------------------------
	// Servicios & Dominios
	//--------------------------------------------------------------------------------------------------
	
	private Categorias categoria;
	
	private Ciudad ciudad;
	
	private Certificado cert;
	
	private Profile profile;
	
	private Review review
	
	private User user;
	
	ComentarioService comentarioService = new ComentarioService( );
	
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
		
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA");
		assert user.save() != null;
		
		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO 123123" , rating:1, profile:profile,user:user, fechaCreado:new Date());
		assert review.save() != null;
		
		review = new Review(author:"Gustavo Lozano",titulo:"titulo1 es", texto:"TEXTO segundo", rating:5, profile:profile,user:user,fechaCreado:new Date());
		assert review.save() != null;
	}
	
	//--------------------------------------------------------------------------------------------------
	// Tests
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * 
	 */
	void testDarMisComentarios( )
	{
		setIt();
		
		try
		{
			def results = comentarioService.darMisComentarios(user.id);
			assert results.size(), 2;
			
			//TODO: esten ordenados por fecha
			
		}
		catch(KahuuException e)
		{
			fail "No debe llegar aca";
		}
		
	}
	
	/**
	 * 
	 */
	void testDarMisComentariosInvalido( )
	{
		setIt();
		
		try
		{
			def results = comentarioService.darMisComentarios(123456);
			fail "No debe llegar aca";
		}
		catch(KahuuException e)
		{
			//Pasa por aca
		}
		
	}
	
	/**
	 * 
	 */
	void testDarReview( )
	{
		setIt();
		
		try
		{
			Review rev = comentarioService.darReview(review.id);
				
			assert review.id, rev.id;
			assert review.titulo, rev.titulo;
			assert review.texto, rev.texto;
			assert review.rating, rev.rating;
		}
		catch(KahuuException e)
		{
			fail "No debe llegar aca";
		}	
	}
	
	/**
	 * 
	 */
	void testDarReviewInvalido( )
	{
		setIt();
		
		try
		{
			Review rev = comentarioService.darReview(12345);
			fail "No debe llegar aca";
		}
		catch(KahuuException e)
		{
			//Pasa por aca
		}
	}
	
	/**
	 * 
	 */
	void testCrearComentario( )
	{
		setIt();
		 
		//test ideal
		int inicial = profile.totalRating;
		int totalReviews = this.profile.reviews.size();
		
		
		String titulo = "Prueba Titulo";
		String texto = "prueba texto kjsdfgh";
		int rating = 4;
		
		try
		{
			Profile profNuevo = comentarioService.crearComentario(profile,user,titulo,texto,rating);
			
			assert profNuevo.reviews.size( ), (totalReviews + 1); 
			assert profNuevo.nombre, profile.nombre;
		}
		catch(KahuuException e)
		{
			fail "No debe llegar aca";
		}
	}
	
	/**
	 * 
	 */
	void testEditarComentario( )
	{
		setIt();
		
		String titulo = "nuevo";
		String texto = "texto nuevo";
		int rating = 1;
		
		try
		{
			def profile = comentarioService.editarComentario(review.id, titulo, texto, rating);
			
			assert review.titulo, titulo;
			assert review.texto, texto;
			assert review.rating, rating;
			
			assert profile.totalRating == 1;
		}
		catch(KahuuException e)
		{
			fail "No debe llegar aca";
		}
	}
	
	/**
	 * 
	 */
	void testDeleteComentario( )
	{
		setIt();
		
		try
		{
			List todos = comentarioService.darMisComentarios(user.id);
			
			int total = todos.size();
			assert profile.totalRating, 3;
		
			//Borrar el comentario
			comentarioService.deleteComentario(review.id);
	
			//Comentarios despues de haber borrado (n-1)
			todos = comentarioService.darMisComentarios(user.id);
			assert todos.size(), (total-1);
			
			//Ver si el rating se calculo bien
			assert profile.totalRating, 1;
		}
		catch(KahuuException e)
		{
			fail "No debe llegar aca";
		}
	}
	
	/**
	 * 
	 */
	void testDeleteComentarioInvalido( )
	{
		setIt();
		
		try
		{
			comentarioService.deleteComentario(12345);
			fail "No debe llegar aca";
		}
		catch(KahuuException e)
		{
			//Deberia llegar aca
		}
	}
}
