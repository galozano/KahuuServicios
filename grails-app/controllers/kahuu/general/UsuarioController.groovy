package kahuu.general

import kahuu.general.User;
import kahuu.general.UsuarioService;
import kahuu.general.exceptions.KahuuException;
import grails.plugin.facebooksdk.FacebookContext;
import grails.plugin.facebooksdk.FacebookGraphClient
import grails.validation.ValidationException
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib



/**
 * Controlado de el manejo de usuarios 
 * @author gustavolozano
 *
 */
class UsuarioController
{
	
	//------------------------------------------------------------------------------------------
	// Servicios
	//------------------------------------------------------------------------------------------
	
	FacebookContext facebookContext;
	
	UsuarioService usuarioService;

	//------------------------------------------------------------------------------------------
	// Metodos
	//------------------------------------------------------------------------------------------
	
	def index() { }

	/**
	 * 
	 * @return
	 */
	def login( )
	{
		if (session.user)
		{
			redirect(controller:'comentarios', action:'misComentarios');
		}
		else
		{
			render(view: "login", model:[facebookContext: facebookContext]);
		}
	}
	
	/**
	 * Maneja el login cuando se hace click en el boton de facebook
	 * @return va a la pagina de comentarios
	 */
	def handleFacebook( )
	{
		FacebookGraphClient facebookGraphClient = new FacebookGraphClient();
		
		if (facebookContext.app.id && facebookContext.authenticated) 
		{
			String token = facebookContext.user.token;
			
			if(token)
			{
				facebookGraphClient = new FacebookGraphClient(token)
				def usuarioFacebook = facebookGraphClient.fetchObject(facebookContext.user.id.toString());
				
				//Verificar con id que el usuario existe
				User usuario = usuarioService.verificarFacebookUsuario(facebookContext.user.id.toString());
				
				if(usuario != null)
				{
					session.user = usuario;
					redirect(controller:'comentarios', action:'misComentarios');
				}
				else
				{
					User nuevoUsuario = usuarioService.agregarUsuarioFacebook(usuarioFacebook.name, usuarioFacebook.email, facebookContext.user.id.toString())
					session.user = nuevoUsuario;
					redirect(controller:'comentarios', action:'misComentarios');
				}
			}
		}
	}

	/**
	 * Maneja el login del formulario login
	 * @param- email - email que ingreso el usuario
	 * @param- password- password que ingreso el usuario
	 * @return- la pagina con los comentario si el registro fue exitoso de lo contrario la misma pagina login con su mensaje
	 */
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

	/**
	 * 
	 * @return
	 */
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
					flash.message = "Registrado exitosamente. Revisa t&uacute; email para confirmar t&uacute; cuenta.Revisa tu correo no deseado!"
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

	/**
	 * Termina la session 
	 * @return rederige a la action login
	 */
	def logout()
	{
		log.info "User agent: " + request.getHeader("User-Agent");
		
		facebookContext.user.invalidate();
		session.user = null;
		session.invalidate();
		redirect(action: "login");
	}

	/**
	 * Muestra la pagina de olvido clave
	 * @return pagina olvideClave
	 */
	def olvideClave( )
	{
		render(view:"olvideClave");
	}

	/**
	 * Maneja el olvido de la clave del usuario
	 * @params email- el email del usuario
	 * @return - mensaje de que se cambio con exito o que hubo un problema
	 */
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
	 
	/**
	 * Verificacion de la cuenta del usuario
	 * @params id- id del usuario
	 * @params key- el key o clave de confirmacion	
	 * @return mensaje que fue activada la cuenta o de lo contrario
	 */
	def verificarEmail()
	{
		try
		{
			boolean verificado = usuarioService.verificarEmail(params.id, params.key);
			
			if(verificado)
			{
				flash.message = "Gracias, ya puedes usar t&uacute; cuenta.";
				render(view: "verificarEmail");
			}
			else
			{
				flash.message = "T&uacute; cuenta es invalida y no pudo ser confirmada";
				render(view: "verificarEmail");
			}
		}
		catch(KahuuException e)
		{
			flash.message = e.getMessage();
			render(view: "verificarEmail");
		}
	}
}
