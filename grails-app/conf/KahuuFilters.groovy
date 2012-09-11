
class KahuuFilters
 {
	def filters = 
	{
	       loginCheck(controller: 'empleo|usuario', action: '*', invert:true) 
		   {
	           before = 
			   {
	              if (!session.user && !actionName.equals('login') && !actionName.equals('handleLogin')) 
				  {
	                  redirect(controller:"usuario", action: 'login');
	                  return false;
	               }
	           }
		   }
	}
}
