package kelgal.empleos

import kelgal.empleos.exceptions.KahuuException;
import grails.validation.ValidationException
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class UsuarioController
{
	
	//------------------------------------------------------------------------------------------
	// Servicios
	//------------------------------------------------------------------------------------------
	
	UsuarioService usuarioService;

	//------------------------------------------------------------------------------------------
	// Metodos
	//------------------------------------------------------------------------------------------
	
	def index() { }

	def login( )
	{
		if (session.user)
		{
			redirect(controller:'comentarios', action:'misComentarios');
		}
		else
		{
			render(view: "login");
		}
	}

	def handleLogin( )
	{
		User user = usuarioService.loginUsuario(params.email, params.password);

		if(user != null)
		{
			session.user = user;
			redirect(controller:'comentarios', action:'misComentarios');
		}
		else
		{
			flash.message = "Constrasena o email incorrecto";
			render(view:"login", model:[userRegist:user]);
		}
	}

	def handleRegistration( )
	{
		User user = new User(params);
		
		withForm
		{
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
		.invalidToken
		{
			flash.message = "No unda tanta veces.";
			render(view:"login" ,model:[userRegist:user]);
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
			User user = usuarioService.olvidoClave(params.email);
			
			if(user != null)
			{
				flash.message = "Se cambio la clave con exito. Revisa tu email con tu nueva clave.";
			}
			else
			{
				flash.message = "El email " + params.email + " no existe";
			}
			
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
			boolean verificado = usuarioService.verificarEmail(params.id, params.key);
			
			if(verificado)
			{
				flash.message = "Gracias, ya puedes usar t&uacute; cuenta.";
				redirect(action: "login");
			}
			else
			{
				flash.message = "T&uacute; cuenta es invalida y no pudo ser confirmada";
				redirect(action: "login");
			}
		}
		catch(KahuuException e)
		{
			flash.message = e.getMessage();
			redirect(action: "login");
		}
	}
}
