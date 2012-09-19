package kelgal.empleos

class InformacionController
 {

    def index() { }
	
	def terminos()
	{
		
	}
	
	def contactenos()
	{
		render(view:"contactenos");
	}
	
	def handleContactenos()
	{
		sendMail
		{
			to "gustil@gmail.com"
			subject params.email +":"+ params.asunto
			body params.texto
		}

		flash.message = "Email enviado. Muchas Gracias!"
		render(view:"contactenos");
	}
}
