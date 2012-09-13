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
	
	Certificado certificado;
	
	byte[] image;
		
	static hasMany = [referencias:Referencia,categorias: Categorias];
	
    static constraints = 
	{
		categorias  lazy:false
		usuario unique:true;
		image maxSize:1000000;
		email email:true
    }
}
