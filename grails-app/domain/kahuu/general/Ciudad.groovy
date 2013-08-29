package kahuu.general

/**
 * Representa una ciudad en el sistema
 * @author gustavolozano
 *
 */
class Ciudad 
{
	String nombre;
	
	boolean activado;
	
	static searchable =
	{
		root false;
	}
	
	
    static constraints = 
	{
		nombre unique:true;
    }
}
