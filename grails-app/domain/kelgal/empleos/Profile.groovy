package kelgal.empleos

import java.util.Date;


class Profile implements Comparable
{	
	String nombre;
	
	/**
	 * Nombre de usuario para identificar al perfil
	 */
	String usuario;
	
	/**
	 * Constrasena del perfil
	 */
	String password;
	
	String email;
	
	/**
	 * Fecha y Hora en la cual fue creado el perfil
	 */
	Date fechaCreado;
	
	/**
	 * Estado del usuario, incrito o no
	 */
	boolean estadoUsuario;
	
	String celular;
	
	String celular2;
	
	String descripcion;
	
	Ciudad ciudad;
	
	byte[] image;
	
	/**
	 * El rating total del perfil
	 */
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
		int totalRating1 = this.totalRating == null? 0: this.totalRating;
		int totalRating2 = o.totalRating == null ? 0 : o.totalRating;
			
		if(rev1 == rev2)
		{
			if(totalRating1 == totalRating2)
				return 0;
			else if(totalRating1 < totalRating2)
				return 1;
			else if(totalRating1 > totalRating2)
				return -1;
		}
		else if(rev1 < rev2)
		{
			if(totalRating1 == totalRating2)
				return 1;
			else if(totalRating1 < totalRating2)
				return 1;
			else if(totalRating1 > totalRating2)
				return -1;
		}
		else if(rev1 > rev2)
		{
			if(totalRating1 == totalRating2)
				return -1;
			else if(totalRating1 < totalRating2)
				return 1;
			else if(totalRating1 > totalRating2)
				return -1;
		}
	}
}
