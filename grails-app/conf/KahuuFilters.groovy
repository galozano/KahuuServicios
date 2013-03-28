import grails.plugin.facebooksdk.FacebookContext
import kahuu.general.UsuarioService


class KahuuFilters
 {
	FacebookContext facebookContextProxy;
	
	UsuarioService usuarioService;
	 
	def filters = 
	{
	       loginAdminCheck(controller: 'categorias|certificado|ciudad|profile|user|anuncio|controlAnuncio', action: '*', ) 
		   {
	           before = 
			   {
	              if (!session.admin) 
				  {
	                  redirect(controller:"admin", action: 'login');
	                  return false;
	               }
	           }
		   }
		   
		   loginCheck(controller: 'comentarios', action: '*')
		   {
			   before =
			   {
				   if (!session.user)
				   {
					   redirect(controller:"usuario", action: 'login');
					   return false;
				   }
			   }
		   } 
	}
}
