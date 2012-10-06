package kelgal.empleos

import java.util.Date;


class Profile implements Comparable
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
		categorias  lazy:false;
		reviews lazy:false;
		usuario unique:true, blank:false;
		image maxSize:1000000;
		email email:true
		nombre blank:false, size:2..45;
		celular blank:false;
    }

	@Override
	public int compareTo(Object o)
	{
		int rev1 = this.reviews == null? 0:this.reviews.size( );
		int rev2 = o.reviews == null? 0 : o.reviews.size();
		
		if(rev1 == rev2)
			return 0;
		else if(rev1 < rev2)
			return 1;
		else if(rev1 > rev2)
			return -1;
	}
}
