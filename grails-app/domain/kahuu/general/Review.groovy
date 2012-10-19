package kahuu.general

/**
 * Representa un comentario en el sistema
 * @author gustavolozano
 *
 */
class Review 
{	
	String titulo;
	
	String author;
	
	String texto;
	
	int rating;
	
	Date fechaCreado;
	
	static belongsTo = [user:User,profile:Profile];
	
	static constraints =
	{
		titulo size:4..45, blank:false;
		texto  blank:false;
		author blank:false;
		rating blank:false;
		
	}
	
	static mapping =
	 {
		texto type: 'text';
	}
}
