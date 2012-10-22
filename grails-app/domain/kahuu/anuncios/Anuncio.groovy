package kahuu.anuncios

import kahuu.general.User;

/**
 * Representa un anuncio o propaganda
 * @author gustavolozano
 */
class Anuncio implements Comparable
{
	String titulo;
	
	String urlWebsite;
	
	String descripcion;
	
	double costoPClick;
	
	double presupuesto;
	
	int numeroClicks;
	
	int numeroVistas;
	
	boolean estado;
	
	User usuario;
	
	Date fechaCreado;
	
	static hasMany = [vistas:Vista];	
		
    static constraints = 
	{
		
	}

	@Override
	public int compareTo(Object o) 
	{
		double primero = ((numeroClicks/numeroVistas) * 100) * costoPClick;
		double segundo = ((o.numeroClicks/o.numeroVistas) * 100) * o.costoPClick
		
		return segundo.compareTo(primero);
	}
}
