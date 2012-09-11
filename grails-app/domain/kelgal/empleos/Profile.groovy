package kelgal.empleos

import java.util.Date;


class Profile
{
	String nombre;
	
	String usuario;
	
	String password;
	
	Date fechaCreado;
	
	boolean estadoUsuario;
	
	String celular;
	
	String descripcion;
	
	Ciudad ciudad;
	
	byte[] image;
		
	static hasMany = [referencias:Referencia,categorias: Categorias];
	
    static constraints = 
	{
		categorias  lazy:false
		usuario unique:true;
		image maxSize:1000000;
    }
}
