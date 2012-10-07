package kelgal.empleos

class Categorias 
{	
	/**
	 * 
	 */
	String nombre;

    static constraints =
	{
		nombre unique: true, blank:false;
    }
}
