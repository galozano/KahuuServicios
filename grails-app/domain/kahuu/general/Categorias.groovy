package kahuu.general

/**
 * Rerpesenta una categoria en el sistema
 * @author gustavolozano
 *
 */
class Categorias 
{	
	String nombre;
	
	String descripcion;
	
	boolean activado;

	static searchable = 
	{
		root false;
	}
	
    static constraints =
	{
		nombre unique: true, blank:false;
    }
}
