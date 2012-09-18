package kelgal.empleos

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
		titulo size:4..20;		
	}
	
	static mapping = {
		texto type: 'text';
	}
}
