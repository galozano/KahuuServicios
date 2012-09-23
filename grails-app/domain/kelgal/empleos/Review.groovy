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
		titulo size:4..20, blank:false;
		texto  blank:false;
	}
	
	static mapping = {
		texto type: 'text';
	}
}
