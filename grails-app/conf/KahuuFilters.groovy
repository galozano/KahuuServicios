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
		   
//		   loginFacebook(controller: 'usuario', action: 'logout', invert:true)
//		   {
//			   before =
//			   {
//				   if (facebookContextProxy.authenticated & !session.user)
//				   {
//					   session.user = usuarioService.verificarFacebookUsuario(facebookContextProxy.user.id.toString());
//				   }
//			   }
//		   }
		   
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
