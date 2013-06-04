package kelgal.empleo.integration

import static org.junit.Assert.*
import kahuu.general.Categorias;
import kahuu.general.Certificado;
import kahuu.general.Ciudad;
import kahuu.general.ComentarioService
import kahuu.general.ComentariosController
import kahuu.general.PerfilService
import kahuu.general.Profile;
import kahuu.general.Review;
import kahuu.general.User;
import kahuu.general.UsuarioService

import org.junit.*

import grails.plugin.facebooksdk.FacebookContext
import grails.test.mixin.*

import org.h2.engine.SessionFactory;

@TestFor(ComentariosController)
class ComentarioTests 
{
	//--------------------------------------------------------------------------------------------------
	// Dominios
	//--------------------------------------------------------------------------------------------------
	
	ComentariosController comentarioController;
	
	//--------------------------------------------------------------------------------------------------
	// Escenarios
	//--------------------------------------------------------------------------------------------------
	
	@Before
	void setUp() 
	{
		comentarioController = new ComentariosController();
		comentarioController.comentarioService = new ComentarioService();
		comentarioController.perfilService = new PerfilService();
		comentarioController.usuarioService = new UsuarioService();
		comentarioController.facebookContext = new FacebookContext();
	}

    @After
    void tearDown() 
	{
	
    }

	@Test
	void testMisComentario( )
	{	
		comentarioController.misComentarios();
	}
	
}
