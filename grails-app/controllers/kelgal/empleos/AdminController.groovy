package kelgal.empleos

class AdminController 
{

	def index() { }
	
	def login( )
	{
		if (session.admin)
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
		def user = Admin.findByLogin(params.login);
		
		if (user)
		{
			if (user.password == params.password)
			{
				session.admin = user;
				redirect(controller:'profile', action:'index');
			}
			else
			{
				flash.message = "Constrasena incorrecta con el usuario ${params.login}";
				redirect(action: "login");
			}
		}
		else
		{
			flash.message = "Usuario ${params.login} no encontrado";
			redirect(action: "login");
		}
	}

	def logout()
	{
		log.info "User agent: " + request.getHeader("User-Agent")
		session.invalidate()
		redirect(action: "login")
	}
}
