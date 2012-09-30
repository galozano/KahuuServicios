package kelgal.empleos

import kelgal.empleos.exceptions.KahuuException;
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class UsuarioService
{
	def loginUsuario(String email, String password)
	{
		User user = User.findByEmail(email);

		if(user)
		{
			if (user.password == password.encodeAsSHA1())
			{
				return user;
			}
			else
			{
				throw new KahuuException("Constrasena incorrecta con el email ${email}");
			}
		}
		else
		{
			throw new KahuuException("Usuario ${email} no encontrado");
		}
	}

	def registration(String nombre, String email, String password)
	{
		User user = new User(nombre:nombre, email:email, password:password.encodeAsSHA1(), fechaCreado:new Date(), activated:false, keyConfirmar:Password.createRandomPass());

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

			return user;
		}
		else
		{
			user.password = "";
			throw new KahuuException("No se pudo guardar", user);
		}
	}

	def olvidoClave(String email)
	{
		String sb = Password.createRandomPass();
		User user = User.findByEmail(email);

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
			}
			else
			{
				//No se pudo guardar el nuevo usuario
				throw new KahuuException("Problemas creando la clave");
			}
		}
		else
		{
			//EL usuario buscado no existe
			throw new KahuuException("El email " + email + " no existe");
		}
	}

	def verificarEmail(id)
	{
		User u = User.get(id);
		u.activated = true;
		u.keyConfirmar = "";

		if(u.save())
		{
			return true;
		}
		else
		{
			throw new KahuuException("Problema activando tu cuenta.");
		}
	}

	def cambiarPassword(String password, Long id)
	{
		User user = User.get(id);
		user.password = password.encodeAsSHA1();

		if(user.save(flush:true))
		{
			return user;
		}
		else
		{
			throw new KahuuException("Error guardadndo usuario", user);
		}
	}
	
	def actualizarUsuario(String nombreNuevo, Long id)
	{
		User user = User.get(id);
		user.nombre = nombreNuevo;

		if(user.save(flush:true))
		{
			return user;
		}
		else
		{
			throw new KahuuException("Error guardadndo usuario", user);
		}
	}
}
