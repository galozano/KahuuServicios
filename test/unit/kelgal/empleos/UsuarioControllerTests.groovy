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

    void testSomething()
	{
       fail "Implement me"
    }
	
	void testHadleComentario( )
	{
		Categorias categoria = new Categorias(nombre:"Primera");
		Ciudad ciudad = new Ciudad(nombre:"Cartagena");
		Certificado cert = new Certificado(nombre:"Principal", nivel:1);
		
		def profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		
		assert profile.save() != null;
		
		User user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com", puntos:0,fechaCreado:new Date());
		
		assert user.save() != null;
		
		Review review = new Review(author:"Gustavo Lozano", texto:"TEXTO akdjhfkhjadfhkja TEXTO kjadfhk", rating:1, profile:profile,user:user);
		
		assert review.save() != null;
		
		
	}
	
	
	
}
