package kahuu.general


/**
 * Representa un usuario en el sistema
 * @author gustavolozano
 *
 */
class User 
{
	String nombre;
	
	String email;
	
	String password;
		
	Date fechaCreado;
	
	boolean activated;
	
	String keyConfirmar;
	
	String idFacebook;
	
	Date ultimoLogin;
	
	static hasMany = [reviews:Review];
	
    static constraints = 
	{
		email email: true, unique: true, blank:false;
		nombre blank:false, size:2..45;
		password blank:false;
	}
}
