package kelgal.empleos

/**
 * Rerpesenta una categoria en el sistema
 * @author gustavolozano
 *
 */
class Categorias 
{	
	String nombre;

    static constraints =
	{
		nombre unique: true, blank:false;
    }
}
