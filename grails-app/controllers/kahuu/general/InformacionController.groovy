package kahuu.general

/**
 * Controlados de las paginas de informacion
 * @author gustavolozano
 *
 */
class InformacionController
 {

    def index() { }
	
	//------------------------------------------------------------------------------------------
	// Metodos Informacion
	//------------------------------------------------------------------------------------------
	
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
		render(view:"registroServicio");
	}
	
	/**
	 * Maneja el formulario de ingreso al sistema
	 * @return-pagina de publicar servicio
	 */
	def handlePublicarServicio( )
	{
		withForm
		{
			if(!params.agree)
			{
				flash.message = "Debe aceptar los t&eacute;rminos y condiciones.";
				render(view:"registroServicio");
				return;
			}
			else if (params.nombre.equals("") || params.celular.equals("") || params.descripcion.equals("") || params.categoria.equals(""))
			{
				flash.message = "Uno o m&aacute;s campos estan vacios."
				render(view:"registroServicio");
			}

			else
			{
				String text = "Nombre:" + params.nombre + " Celular:" +params.celular + " Descripcion:"+ params.descripcion + " Email: "+ params.email + " Ciudad:" + params.ciudad+" Categorias:";
				
				StringBuffer textB = new StringBuffer(text);
				
				for(String c: params.categorias)
				{
					textB.append(c);
					textB.append(",");
				}
				
				//Enviar email a soporte kelgal
				sendMail
				{
					to "gustil@gmail.com"
					subject params.nombre
					body textB.toString()
				}
				
				flash.message = "Solicitud enviada con exito. La analizaremos y te llamaremos para confirmar. Muchas Gracias.";
				render(view:"registroServicio");
			}
		}
		.invalidToken
		{
			flash.message = "No unda tanta veces.";
			render(view:"registroServicio");
		}
	}

}
