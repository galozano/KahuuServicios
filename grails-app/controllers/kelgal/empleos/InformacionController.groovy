package kelgal.empleos

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
			flash.message = "Uno o m&aacute;s campos vac&iacute;o."
			render(view:"contactenos");
			return;
		}
		
		//Enviar email a soporte kelgal
		sendMail
		{
			to "gustil@gmail.com"
			subject params.nombre +":"+ params.asunto
			body params.texto + "  " + params.email;
		}

		flash.message = "Email enviado. Muchas Gracias!"
		render(view:"contactenos");
	}

}
