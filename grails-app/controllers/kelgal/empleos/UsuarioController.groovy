package kelgal.empleos

import kelgal.empleos.exceptions.KahuuException;
import grails.validation.ValidationException
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class UsuarioController 
{
	def usuarioService;
	
    def index() { }
	
	def login( ) 
	{
		if (session.user) 
		{
			flash.message = null;
			redirect(controller:'comentarios', action:'misComentarios');
		}
		else
		{
			render(view: "login");
		}
	}
	
	def handleLogin( )
	{
		try
		{
			session.user = usuarioService.loginUsuario(params.email, params.password);
			redirect(controller:'comentarios', action:'misComentarios');
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			redirect(action: "login");
		}	
	}

	def handleRegistration( ) 
	{	
		User user = new User(params);
		
		if(!params.agree)
		{
			flash.message = "Debe aceptar los t&eacute;rminos y condiciones.";
			render(view:"login" ,model:[userRegist:user]);
			return;
		}		
		else if (params.password != params.confirm) 
		{
			flash.message = "Las constrase&ntilde;as no son iguales."
			render(view:"login" ,model:[userRegist:user]);
		}
		else if(params.password.equals("") || params.password.size() < 4)
		{
			flash.message = "Constrase&ntilde;a tiene que ser mayor de 4 carcacteres"
			render(view:"login" ,model:[userRegist:user]);
		}
		else 
		{
			try
			{	
				usuarioService.registration(params.nombre, params.email, params.password);
				flash.message = "Registrado exitosamente. Revisa t&uacute; email para confirmar t&uacute; cuenta."
				redirect(action:'login');
			}
			catch(KahuuException e)
			{
				render(view: "login", model:[userRegist:e.invalido]);
			}
		}
	}
	
	def logout() 
	{
		log.info "User agent: " + request.getHeader("User-Agent")
		session.invalidate()
		redirect(action: "login")
	}
	
	def olvideClave( )
	{
		render(view:"olvideClave");
	}
	
	def handleOlvideClave( )
	{	
		try
		{
			usuarioService.olvidoClave(params.email);
			flash.message = "Se cambio la clave con exito. Revisa tu email con tu nueva clave.";
			render(view:"olvideClave");
		}
		catch(KahuuException e)
		{
			flash.message = e.message;
			render(view:"olvideClave");
		}
	}
	
	def verificarEmail()
	{
		try
		{
			usuarioService.verificarEmail(params.id);
			flash.message = "Gracias, ya puedes usar t&uacute; cuenta.";
			redirect(action: "login");
		}
		catch(KahuuException e)
		{
			flash.message = e.getMessage();
			redirect(action: "login");
		}
	}
}
