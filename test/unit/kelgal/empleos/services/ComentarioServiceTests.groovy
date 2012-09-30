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
	private Categorias categoria;
	
	private Ciudad ciudad;
	
	private Certificado cert;
	
	private Profile profile;
	
	private Review review
	
	private User user;
	
	def comentarioService = new ComentarioService( );
	
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
	
	void testDarMisComentarios( )
	{
		setIt();
		
		try
		{
			def results = comentarioService.darMisComentarios(user.id);
			assert results.size(), 2;
		}
		catch(KahuuException e)
		{
			fail "No debe llegar aca";
		}
		
	}
	
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
	
	void testDarReview( )
	{
		setIt();
		
		try
		{
			Review rev = comentarioService.darReview(review.id);
			
			assert rev.titulo, review.titulo;
			assert rev.rating, review.rating;
		}
		catch(KahuuException e)
		{
			fail "No debe llegar aca";
		}	
	}
	
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
	
	void testCrearComentario( )
	{
		setIt();
		 
		//test ideal
		int inicial = profile.totalRating;
		
		Long perfilId  = profile.id;
		String titulo = "Prueba Titulo";
		String texto = "prueba texto kjsdfgh";
		int rating = 4;
		
		try
		{
			comentarioService.crearComentario(perfilId,user,titulo,texto,rating);
		}
		catch(KahuuException e)
		{
			fail "No debe llegar aca";
		}
	}
	
	void testEditarComentario( )
	{
		
	}
	
	void testDeleteComentario( )
	{
		
	}
}
