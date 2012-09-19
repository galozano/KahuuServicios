package kelgal.empleos

class Ciudad 
{
	String nombre;
	
    static constraints = 
	{
		nombre unique:true;
    }
}
