package kelgal.empleos

import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class UsuarioController 
{
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
		def user = User.findByEmail(params.email);
		
		if (user)
		{
			if (user.password == params.password.encodeAsSHA1())
			{
				session.user = user;
				redirect(controller:'comentarios', action:'misComentarios');
			}
			else
			{
				flash.message = "Constrasena incorrecta con el email ${params.email}";
				redirect(action: "login");
			}
		}
		else
		{
			flash.message = "Usuario con email ${params.email} no encontrado";
			redirect(action: "login");
		}
	}
	
	def register()
	{
		if(!session.user)
		{	
			render(view: "login");
		}
		else
		{
			redirect(controller:'perfil', action:'users', id:1);
		}
	}

	def handleRegistration( ) 
	{
		def user = new User(params);
		user.fechaCreado = new Date();
		
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
		else 
		{
			user.password = params.password.encodeAsSHA1();
			
			//Poner codigo de email
			user.activated = false;
			user.keyConfirmar = Password.createRandomPass(); 
			
			if (user.save(flush:true)) 
			{
				//Enviar email de confirmacion
				String link = new ApplicationTagLib().createLink([controller: 'usuario', action:"verificarEmail", absolute: true,  params:[id: user.id, key: user.keyConfirmar]]);
				String send = "<p>Para verificar t&uacute; email click en el siguiente link</p> <br/>" + link + "<br/><br/> Kahuu Servicios";
				
				sendMail
				{
					to user.email;
					subject "Verificar Email:Kahuu"
					html send
				}
				
				flash.message = "Registrado exitosamente. Revisa t&uacute; email para confirmar t&uacute; cuenta."
				redirect(action:'login');
			}
			else
			{ 
				user.password = "";
				render(view: "login", model:[userRegist:user]);
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
		String sb = Password.createRandomPass();
		
		User user = User.findByEmail(params.email);
		
		if(user != null)
		{
			user.password = sb.encodeAsSHA1();
			
			if(user.save())
			{
				String mensaje = "<p> Tu nueva clave es "+ sb + "</p> <br/> Cambia tu clave en Tus Comentarios>Perfil>Cambiar Constrase&ntilde;a"; 
				
				sendMail
				{
					to user.email;
					subject "Tu nueva clave"
					html mensaje
				}
				
				flash.message = "Se cambio la clave con exito. Revisa tu email con tu nueva clave.";
				render(view:"olvideClave");
			}
			else
			{
				//No se pudo guardar el nuevo usuario
				flash.message = "Problemas creando la clave";
				render(view:"olvideClave");
			}
		}
		else
		{
			//EL usuario buscado no existe
			flash.message = "El email " + params.email + " no existe";
			render(view:"olvideClave");
		}
		
		render(view:"olvideClave");
	}
	
	def verificarEmail()
	{
		User u = User.get(params.id);
		u.activated = true;
		u.keyConfirmar = "";
		
		if(u.save())
		{
			flash.message = "Gracias, ya puedes usar t&uacute; cuenta.";
			redirect(action: "login")
		}
		else
		{
			flash.message = "Problema activando tu cuenta.";
			redirect(action: "login")
		}
	}
}
