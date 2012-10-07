package kelgal.empleos

class Ciudad 
{
	/**
	 * Nombre de la ciudad
	 */
	String nombre;
	
    static constraints = 
	{
		nombre unique:true;
    }
}
