package kahuu.general

import org.springframework.web.multipart.MultipartFile;

/**
 * Controlados de las paginas de informacion
 * @author gustavolozano
 *
 */
class InformacionController
 {
	 //------------------------------------------------------------------------------------------
	 // Servicios
	 //------------------------------------------------------------------------------------------
	 
	PerfilService perfilService;
	 
	//------------------------------------------------------------------------------------------
	// Metodos Informacion
	//------------------------------------------------------------------------------------------
	
	def index() { }
	
	/**
	 * Muestra la pagina de terminos y condiciones 
	 * @return pagina terminos
	 */
	def terminos()
	{
		render(view:"terminos");
	}
	
	/**
	 * Muestra la pagina de politicas de seguridad
	 * @return pagina privacidad
	 */
	def privacidad()
	{
		render(view:"privacidad");
	}
	
	/**
	 * Muestra la pagina de contactenos
	 * @return pagina contactenos
	 */
	def contactenos()
	{
		flash.message = null;
		render(view:"contactenos");
	}
	
	/**
	 * Muestra la pagina de comofunciona kahuu
	 * @return pagina quees
	 */
	def comoFunciona()
	{
		render(view:"quees");
	}
	
	/**
	 * Maneja el formulario de contactenos. Envia un email conla informacion que ingreso el usuario.
	 * @params nombre - nombre del usuario
	 * @params asunto - asunto del email
	 * @params texto - texto del email
	 * @return pagina de contactos con mensaje que se envio el email correctamente
	 */
	def handleContactenos()
	{
		if(params.nombre == "" || params.asunto == ""  || params.texto == "" || params.email == "")
		{
			flash.message = "Uno o m&aacute;s campos estan vac&iacute;os."
			render(view:"contactenos");
			return;
		}
		
		//Enviar email a soporte gusti
		sendMail
		{
			to "gustil@gmail.com"
			subject params.nombre +":"+ params.asunto
			body params.texto + "  " + params.email;
		}

		flash.message = "Email enviado. Muchas Gracias!"
		render(view:"contactenos");
	}
	
	/**
	 * Retrona pagian de formulario para publicar un servicio
	 * @return - pagina de registro de servicio
	 */
	def publicarServicio( )
	{		
		def listaCiudades = perfilService.darCiudadesCompletas();
		def listaCategorias = perfilService.darCategoriasCompletas();
		
		render(view:"registroServicio", model:[listaCiudades:listaCiudades, listaCategorias:listaCategorias]);
	}
	
	/**
	 * Maneja el formulario de ingreso al sistema
	 * @return-pagina de publicar servicio
	 */
	def handlePublicarServicio( )
	{
		def listaCiudades = perfilService.darCiudadesCompletas();
		def listaCategorias = perfilService.darCategoriasCompletas();
		
		withForm
		{
			MultipartFile fotoPerfil = request.getFile('fotoPerfil');
			MultipartFile fotoCedula = request.getFile('fotoCedula');
			
			if(!params.agree)
			{
				flash.message = "Debe aceptar los t&eacute;rminos y condiciones.";
				render(view:"registroServicio", model:[listaCiudades:listaCiudades, listaCategorias:listaCategorias]);

				return;
			}
			else if (params.nombre.equals("") || params.celular.equals("") || params.descripcion.equals("") || params.categoria.equals(""))
			{
				flash.message = "Uno o m&aacute;s campos estan vacios."
				render(view:"registroServicio", model:[listaCiudades:listaCiudades, listaCategorias:listaCategorias]);
				return;
			}
			else if(!fotoPerfil.contentType.equals('image/gif') && !fotoPerfil.contentType.equals('image/jpg') && !fotoPerfil.contentType.equals('image/jpeg') && !fotoPerfil.contentType.equals('image/png') && !fotoPerfil.contentType.equals('application/pdf'))
			{
				flash.message = "La foto de perfil debe ser un gif, jpg, pdf o png"
				render(view:"registroServicio", model:[listaCiudades:listaCiudades, listaCategorias:listaCategorias]);
				return;
			}
			else if(fotoPerfil.size > (1024 * 1024 * 3) || fotoCedula.size > (1024 * 1024 * 3))
			{
				flash.message = "La foto tiene que ser menor a 3 MB"
				render(view:"registroServicio", model:[listaCiudades:listaCiudades, listaCategorias:listaCategorias]);
				return;
			}
			else if(!fotoCedula.contentType.equals('image/gif') && !fotoCedula.contentType.equals('image/jpg') && !fotoCedula.contentType.equals('image/jpeg') && !fotoCedula.contentType.equals('image/png') && !fotoCedula.contentType.equals('application/pdf'))
			{
				flash.message = "La foto de la cedula debe ser un gif, jpg, pdf o png"
				render(view:"registroServicio", model:[listaCiudades:listaCiudades, listaCategorias:listaCategorias]);
				return;
			}
			else
			{
				String text = "Nombre:" + params.nombre + " \n Celular:" +params.celular + " \n Descripcion:"+ params.descripcion + " \n Email: "+ params.email + " \n Ciudad:" + params.ciudad+" \n Categorias:";
				
				log.info('Se ingreso el usuario por publicar servicio:' + text);
				
				StringBuffer textB = new StringBuffer(text);
				textB.append(params.categoria1);
				textB.append(",");
				textB.append(params.categoria2);
				
				//Enviar email a soporte kelgal
				sendMail
				{
					multipart true
					to "soporte@kahuu.co"
					subject 'PRUEBA'
					body textB.toString()
					attachBytes fotoPerfil.name,fotoPerfil.contentType, fotoPerfil.getBytes();
					attachBytes fotoCedula.name,fotoCedula.contentType, fotoCedula.getBytes();
				}
				
				flash.message = "Solicitud enviada con exito. La analizaremos y te llamaremos para confirmar. Muchas Gracias.";
				render(view:"registroServicio", model:[listaCiudades:listaCiudades, listaCategorias:listaCategorias]);
			}
		}
		.invalidToken
		{
			flash.message = "No unda tanta veces.";
			render(view:"registroServicio", model:[listaCiudades:listaCiudades, listaCategorias:listaCategorias]);
		}
	}

}
