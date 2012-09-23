package kelgal.empleos

class InformacionController
 {

    def index() { }
	
	def terminos()
	{
		render(view:"terminos");
	}
	
	def privacidad()
	{
		render(view:"privacidad");
	}
	
	def contactenos()
	{
		flash.message = null;
		render(view:"contactenos");
	}
	
	def handleContactenos()
	{
		if(params.nombre == "" || params.asunto == ""  || params.texto == "" || params.email == "")
		{
			flash.message = "Uno o m&aacute;s campos vac&iacute;o."
			render(view:"contactenos");
			return;
		}
		
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
