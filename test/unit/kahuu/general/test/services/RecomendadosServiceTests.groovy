package kahuu.general.test.services



import java.text.SimpleDateFormat

import kahuu.general.Categorias;
import kahuu.general.Certificado;
import kahuu.general.Ciudad;
import kahuu.general.Profile;
import kahuu.general.Recomendados
import kahuu.general.RecomendadosService;
import kahuu.general.User;
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RecomendadosService)
@Mock([Profile,Categorias,Ciudad,User,Recomendados])
class RecomendadosServiceTests {

	//--------------------------------------------------------------------------------------------------
	// Servicios & Dominios
	//--------------------------------------------------------------------------------------------------
	
	RecomendadosService recomendadosService = new RecomendadosService();
	
	private Profile profile;
	
	private User user;
	
	private Certificado cert;
	
	private Categorias categoria;
	
	private Ciudad ciudad;
	
	private Recomendados recomendados;
	
	//--------------------------------------------------------------------------------------------------
	// Escenarios
	//--------------------------------------------------------------------------------------------------
	
	public void setIt()
	{
		categoria = new Categorias(nombre:"Primera");
		ciudad = new Ciudad(nombre:"Cartagena",activado:true);
		
		cert = new Certificado(nombre:"Principal", nivel:1);
		
		
		assert ciudad.save(flush: true) != null;
		assert categoria.save(flush: true) != null;
		
		for(int i = 1; i < 20; i ++)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse("2009-12-"+i);
			
			profile = new Profile(nombre:"Nombre" + i,usuario:"Usuario" + i,password:"Passowrd",email:"email"+i+"@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:date1 ,celular:"3205721687", descripcion:"descripcion" + i, ciudad:ciudad, image: new byte[10]);
			profile.addToCategorias(categoria);
			
			assert profile.save(flush: true) != null;
		}
		
		profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date( ) ,celular:"3205721687", descripcion:"descripcion1", ciudad:ciudad, image: new byte[10]);
		profile.addToCategorias(categoria);
		
		assert profile.save(flush: true) != null;
		
		profile = new Profile(nombre:"Rafael",usuario:"Rafa",estadoUsuario:true,email:"rafa@gmail.com", certificado:cert, celular2:"3135851647",fechaCreado:new Date( ) ,password:"hola",celular:"3205721687", descripcion:"descripcion2", ciudad:ciudad, image: new byte[1000]);
		profile.addToCategorias(categoria);
		
		assert profile.save(flush: true) != null;
		
		user = new User(nombre:"Gus",password:"pass", email:"gus@gus.com",fechaCreado:new Date(), activated:false, keyConfirmar:"HOLA",idFacebook:"");
		assert user.save(flush: true) != null;
		
	
	}

	//--------------------------------------------------------------------------------------------------
	// Tests
	//--------------------------------------------------------------------------------------------------
		
	void testRecomendarPerfil( )
	{
		setIt();
		
		recomendadosService.recomendarPerfil(profile.id, user.id);
		assert recomendadosService.estaRecomendado(profile, user).profile.compareTo(profile) == 0;
	}
	
	void testEstaRecomendado( )
	{
		setIt();
		
		Recomendados rec = new Recomendados(profile:profile, user:user);
		assert rec.save(flush: true) != null;
		
		assert recomendadosService.estaRecomendado(profile, user).profile.compareTo(profile) == 0;
	
		//Perfil invalido
		assert recomendadosService.estaRecomendado(1234, user).profile;
	}
	
	void testDarRecomendaron( )
	{
		setIt();
		
		Recomendados rec = new Recomendados(profile:profile, user:user);
		assert rec.save(flush: true) != null;
		
		assert recomendadosService.darRecomendaron(profile.id).size() == 1;
	}
	
	
}