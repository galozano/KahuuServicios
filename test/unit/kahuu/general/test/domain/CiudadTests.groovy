package kahuu.general.test.domain

import kahuu.general.Ciudad

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Ciudad)
class CiudadTests 
{
	void testConstraint( )
	{
		Ciudad ciudad = new Ciudad(nombre:"Cartagena");
		
		mockForConstraintsTests(Ciudad, [ciudad]);
		
		//Ciudad sin nombre
		Ciudad nuevaCiudad = new Ciudad();
		assert !nuevaCiudad.validate();
		assert "nullable" == nuevaCiudad.errors["nombre"];
		
		//Ciudad con mismo nombre
		ciudad = new Ciudad(nombre:"Cartagena");
		assert !nuevaCiudad.validate();
		assert "nullable" == nuevaCiudad.errors["nombre"];
		
		//Ciudad con todo bien
		nuevaCiudad = new Ciudad(nombre:"Bogota");
		assert nuevaCiudad.validate();
	}
	
}
