package kelgal.empleos

import java.util.Date;


class Profile
{
	String nombre;
	
	String usuario;
	
	String password;
	
	String email;
	
	Date fechaCreado;
	
	boolean estadoUsuario;
	
	String celular;
	
	String celular2;
	
	String descripcion;
	
	Ciudad ciudad;
	
	byte[] image;
	
	int totalRating;
	
	Certificado certificado;
		
	static hasMany = [categorias: Categorias,reviews:Review];
	
    static constraints = 
	{
		categorias  lazy:false
		usuario unique:true;
		image maxSize:1000000;
		email email:true
		nombre blank:false;
		celular blank:false;
		descripcion blank:false;
    }
}
