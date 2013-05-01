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
	
    static constraints = 
	{
		nombre unique:true;
    }
}
