package kelgal.empleos



/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

@TestFor(EmpleoController)
@Mock([Profile,Categorias])
class EmpleoControllerTests 
{	
	void testIndex( )
	{
		def categoria = new Categorias(nombre:"Primera");
		assert categoria.save(flush: true) != null;
		
		controller.index();
		
		assert view == "/empleo/index";		
		assert model.categoriasList.count , 1;	
	}
	
	void testUsers( )
	{
		Categorias categoria = new Categorias(nombre:"Primera");
		Referencia referencia = new Referencia(nombre:"Ref1");
		Ciudad ciudad = new Ciudad(nombre:"Cartagena");
		Certificado cert = new Certificado(nombre:"Principal", nivel:1);
		
		def profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[10]);
		profile.addToReferencias(referencia);
		profile.addToCategorias(categoria);
		
		assert profile.save() != null;
		
		profile = new Profile(nombre:"Rafael",usuario:"Rafa",estadoUsuario:true,email:"rafa@gmail.com", certificado:cert, celular2:"3135851647",fechaCreado:new Date() ,password:"hola",celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[1000]);
		profile.addToReferencias(referencia);
		profile.addToCategorias(categoria);
		
		assert profile.save() != null;
		
		controller.users(categoria.id);
		
		assert model.profileInstanceTotal, 2;
		
		assert model.profileInstanceList.get(0).nombre, "Gustavo";
		assert model.profileInstanceList.get(1).nombre, "Rafael";
	}
	
	void testBuscar( )
	{
		Categorias categoria = new Categorias(nombre:"Primera");
		Referencia referencia = new Referencia(nombre:"Ref1");
		Ciudad ciudad = new Ciudad(nombre:"Cartagena");
		Certificado cert = new Certificado(nombre:"Principal", nivel:1);
		
		def profile = new Profile(nombre:"Gustavo",usuario:"gus",password:"hola",email:"gus@gmail.com",certificado:cert, celular2:"3135851647", estadoUsuario:false,fechaCreado:new Date() ,celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[10]);
		profile.addToReferencias(referencia);
		profile.addToCategorias(categoria);
		
		assert profile.save() != null;
		
		profile = new Profile(nombre:"Rafael",usuario:"Rafa",estadoUsuario:true,email:"rafa@gmail.com", certificado:cert, celular2:"3135851647",fechaCreado:new Date() ,password:"hola",celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[1000]);
		profile.addToReferencias(referencia);
		profile.addToCategorias(categoria);
		
		assert profile.save() != null;
		
		
		
			
	}
	
	void testProfile( )
	{ 	
		//Perfil que no existe
		controller.profile(877);
		
		assert flash.message != null;
		
		//Test normal
		Categorias categoria = new Categorias(nombre:"Primera");
		Referencia referencia = new Referencia(nombre:"Ref1");
		Ciudad ciudad = new Ciudad(nombre:"Cartagena");
		Certificado cert = new Certificado(nombre:"Principal", nivel:1);
		  	
		def profile = new Profile(nombre:"Gus",usuario:"gus",email:"rafa@gmail.com", certificado:cert, celular2:"3135851647",estadoUsuario:false,fechaCreado:new Date() ,password:"hola", celular:"3205721687", descripcion:"decripcion", ciudad:ciudad, image: new byte[10]);
		profile.addToReferencias(referencia);
		profile.addToCategorias(categoria);
		
		assert profile.save() != null;
		
		controller.profile(profile.id);
		
		assert model.profileInstance.nombre == profile.nombre;  
	}
}
