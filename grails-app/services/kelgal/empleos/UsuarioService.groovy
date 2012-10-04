package kelgal.empleos

import kelgal.empleos.exceptions.KahuuException;
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib


/**
 * 
 * @author gustavolozano
 *
 */
class UsuarioService
{
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
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
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * Registra un nuevo usuario al sistema
	 * @param nombre  nombre != null && nombre != ""
	 * @param email  email != null && email != ""
	 * @param password  password != null && password != ""
	 * @return retorna el usuario que fue registrado
	 */
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

	/**
	 * Envia un email con clave nueva
	 * @param email email != null
	 * @return retorna el usuario al cual se le olvido la clave
	 */
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
				
				return user;
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
			return null;
		}
	}

	/**
	 * Verificar el email despues de registrarse con un link
	 * @param id id != null && id existe
	 * @return
	 */
	def verificarEmail(id, String clave)
	{
		User u = User.get(id);
		
		if(u.keyConfirmar.equals(clave))
		{
			u.activated = true;
			u.keyConfirmar = "";
			
			if(u.save(flush:true))
			{
				return true;
			}
			else
			{
				throw new KahuuException("Problema activando tu cuenta.");
			}
		}
		else
		{
			return false;
		}
	}

	def cambiarPassword(String password, Long id)
	{
		User user = User.get(id);
	
		if(!user)
		{
			throw new KahuuException("El usuario no existe");
		}
		
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
		
		if(!user)
		{
			throw new KahuuException("El usuario no existe");
		}
		
		//Actualizar el nombre del usuario
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
