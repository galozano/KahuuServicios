package kahuu.general

class Recomendados 
{
	Profile profile;
	
	User user;
	
	Date fechaCreado;
	
    static constraints = 
	{
		profile unique:'user';
    }
}
