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
				redirect(controller:'perfil', action:'users', id:1);
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
		user.puntos = 0;
		
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
		render(view:"olvideClave");
	}
	
	def crearComentario( )
	{
		def profile = Profile.get(params.id);	
		render(view:"comentario",model:[profileInstance:profile]);
	}
	
	def handleComentario()
	{		
		Profile.withTransaction { status ->
			
			Profile profile = Profile.get(params.perfil);
			User user = session.user;
			
			Review review = new Review(author:user.nombre, titulo:params.titulo ,texto:params.comentario, rating:params.rating, profile:profile,user:user, fechaCreado:new Date());
		
			if(review.save())
			{
				profile = Profile.get(params.perfil);
				
				int total =  profile.reviews.size();
				int average = 0;
		
				if(total != 0)
				{
					average = profile.reviews.rating.sum() / profile.reviews.size();
				}
				
				profile.totalRating = average;
				
				if(profile.save())
				{
					redirect(controller:"perfil", action:"profile", id:profile.id);
				}
				else
				{
					status.setRollbackOnly();
					render(view:"comentario",model:[profileInstance:profile,comentarioInstance:review]);
				}
			}
			else
			{
				status.setRollbackOnly();
				render(view:"comentario",model:[profileInstance:profile,comentarioInstance:review]);
			}
		}
	}
}
