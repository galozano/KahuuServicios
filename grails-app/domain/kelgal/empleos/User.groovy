package kelgal.empleos

class User 
{
	String nombre;
	
	String email;
	
	String password;
	
	int puntos;
		
	Date fechaCreado;
	
	static hasMany = [reviews:Review];
	
    static constraints = 
	{
		email email: true, unique: true, blank:false;
		nombre blank:false;
		password blank:false;
		
	}
}
