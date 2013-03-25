package kahuu.general

import kahuu.general.User;


import kahuu.general.utils.Password;
import kahuu.general.exceptions.KahuuException;
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib


/**
 * Servicio que maneja todo lo que tiene que ver con el Usuario
 * @author gustavolozano
 */
class UsuarioService
{
	
	/**
	 * Verifica que el usuario que esta haciendo login existe y es valido
	 * @param email email del usuario tratando de registrarse, email != null
	 * @param password password del usuario tratando de registrarse, password != null
	 * @return retorna el usuario en caso que todo sea correcto, null de lo contrario
	 * @throws KahuuException
	 */
	def loginUsuario(String email, String password) throws KahuuException
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
	 * @throws KahuuException
	 */
	def registration(String nombre, String email, String password) throws KahuuException
	{
		User user = new User(nombre:nombre, email:email, password:password.encodeAsSHA1(), fechaCreado:new Date(), activated:false, idFacebook:"", keyConfirmar:Password.createRandomPass());

		if (user.save(flush:true))
		{
			//Enviar email de confirmacion
			String link = new ApplicationTagLib().createLink([controller: 'usuario', action:"verificarEmail", absolute: true,  params:[id: user.id, key: user.keyConfirmar]]);
			String send = "<p>Para verificar t&uacute; email click en el siguiente link</p> <br/>" + link + "<br/><br/> Kahuu Servicios";
			
			sendMail
			{
				to user.email
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
	 * @throws KahuuException
	 */
	def olvidoClave(String email)  throws KahuuException
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
	 * @param id id del usuario, id != null && id existe
	 * @return verdadero en caso que se verifique bien el email, fakse de lo contario
	 * @throws KahuuException arroja una excepcion en caso de que exista un error guardado el usuario
	 */
	def verificarEmail(id, String clave)  throws KahuuException
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

	/**
	 * Cambia la constrasena del usuario por la nueva especificada
	 * @param password- las constrasena nueva, password != null
	 * @param id la id del usuario al cual se le cambia la constrasena, id != null
	 * @return retorna el usuario a quien se le cambio la constrasena.
	 * @throws KahuuException- arroja una excepcion en caso de que el usuario no exista o no se pude guarar el usuario
	 */
	def cambiarPassword(String password, Long id) throws KahuuException
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
	

	/**
	 * Actualiza el nombre del usuario con la id dada
	 * @param nombreNuevo- nombre nuevo
	 * @param id - id del usuario a cambiar
	 * @return retorna el usuario con el nombre cambiado
	 * @throws KahuuException - an caso de que no se pueda guardar el nuevo usuario
	 */
	def actualizarUsuario(String nombreNuevo, Long id)  throws KahuuException
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
	
	/**
	 * 
	 * @param idFacebook
	 * @return
	 */
	def verificarFacebookUsuario(String idFacebook)
	{
		User user = User.findByIdFacebook(idFacebook);
		return user;
	}
	
	/**
	 * 
	 * @param nombre
	 * @param email
	 * @param idFacebook
	 * @return
	 */
	def agregarUsuarioFacebook(String nombre, String email, String idFacebook)
	{
		User user = new User(nombre:nombre, email:email, password:"none", fechaCreado:new Date(), activated:false, idFacebook:idFacebook, keyConfirmar:"");
	
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
