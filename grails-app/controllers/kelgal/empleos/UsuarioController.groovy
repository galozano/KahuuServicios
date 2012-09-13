package kelgal.empleos

class UsuarioController 
{
    def index() { }
	
	def login( ) 
	{
		if (session.user) 
		{
			redirect(controller:'empleo', action:'index')
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
			if (user.password == params.password)
			{
				session.user = user;
				redirect(controller:'empleo', action:'index');
			}
			else
			{
				flash.message = "Constrasena incorrecta con el email ${params.email}";
				redirect(action: "login");
			}
		}
		else
		{
			flash.message = "Usuario ${params.email} no encontrado";
			redirect(action: "login");
		}
	}
	
	def register()
	{
		if(!session.user)
		{	
			render(view: "crearCuenta");
		}
		else
		{
			redirect(controller:'empleo', action:'index')
		}
	}

	def handleRegistration( ) 
	{
		def user = new User(nombre:params.nombre, email:params.email, password:params.password, fechaCreado: new Date());
		
		if (params.password != params.confirm) 
		{
			flash.message = "Las constrase&ntilde;as no son iguales."
			redirect(action:register)
		}
		else 
		{
			user.password = params.password.encode()
			if (user.save()) 
			{
				redirect(controller:'empleo', action:'index')
			}
			else
			{
				flash.user = user
				redirect(action:register)
			}
		}
	}
	
	def logout() 
	{
		log.info "User agent: " + request.getHeader("User-Agent")
		session.invalidate()
		redirect(action: "login")
	}
}
