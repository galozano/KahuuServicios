package kelgal.empleos

class User 
{
	String nombre;
	
	String email;
	
	String password;
	
	Date fechaCreado;
	
    static constraints = 
	{
		email email: true, unique: true	
	}
	
	static mapping = 
	{
		tablePerHierarchy false
	}
}
