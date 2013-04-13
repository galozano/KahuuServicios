package kahuu.general

import java.util.Date;

/**
 * Representa un perfil( los que proveen el servicio) en el sistema
 * @author gustavolozano
 *
 */
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
	
	static searchable =
	{
		nombre boost: 2.0;
		categorias component: true;
	};
		
	static hasMany = [categorias: Categorias,reviews:Review];
	
    static constraints = 
	{
		categorias  lazy:false;
		reviews lazy:false;
		usuario unique:true, blank:false;
		image maxSize:1000000;
		email email:true
		nombre blank:false, size:2..45;
		
		celular blank:false, validator:{
			return it.matches("[0-9]{10}")
		};
    }

	/**
	 * Compara los perfile para ordenarlos.
	 * Se compara por la cantidad de comentarios que tenga y el rating total que tenga los perfiles
	 */
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
