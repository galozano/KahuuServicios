package kelgal.empleos

class UsuarioController 
{
    def index() { }
	
	def login( ) 
	{
		if (session.user) 
		{
			redirect(controller:'perfil', action:'users', id:1);
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
			redirect(action:"login");
			return;
		}		
		else if (params.password != params.confirm) 
		{
			flash.message = "Las constrase&ntilde;as no son iguales."
			redirect(action:"login")
		}
		else 
		{
			user.password = params.password.encodeAsSHA1();
			
			if (user.save(flush:true)) 
			{
				redirect(controller:'perfil', action:'index');
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
		final int PASSWORD_LENGTH = 8;
		StringBuffer sb = new StringBuffer();
		
		for (int x = 0; x < PASSWORD_LENGTH; x++)
		{
		  sb.append((char)((int)(Math.random()*26)+97));
		}
		
		User user = User.findByEmail(params.email);
		
		if(user != null)
		{
			user.password = sb.toString().encodeAsSHA1();;
			
			if(user.save())
			{
				//Send email
			}
			else
			{
				//No se pudo guardar el nuevo usuario
			}
		}
		else
		{
			//EL usuario buscado no existe
		}
		
		render(view:"olvideClave");
	}
}
