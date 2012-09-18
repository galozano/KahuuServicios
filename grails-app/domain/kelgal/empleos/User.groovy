package kelgal.empleos

class User 
{
	String nombre;
	
	String email;
	
	String password;
		
	Date fechaCreado;
	
	static hasMany = [reviews:Review];
	
    static constraints = 
	{
		email email: true, unique: true, blank:false;
		nombre blank:false, size:2..60;
		password blank:false;
	}
}
