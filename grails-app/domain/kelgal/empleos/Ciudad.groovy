package kelgal.empleos

/**
 * Representa una ciudad en el sistema
 * @author gustavolozano
 *
 */
class Ciudad 
{
	String nombre;
	
    static constraints = 
	{
		nombre unique:true;
    }
}
