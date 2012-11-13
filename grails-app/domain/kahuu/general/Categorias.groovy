package kahuu.general

/**
 * Rerpesenta una categoria en el sistema
 * @author gustavolozano
 *
 */
class Categorias 
{	
	String nombre;

	static searchable = 
	{
		root false;
	}
	
    static constraints =
	{
		nombre unique: true, blank:false;
    }
}
