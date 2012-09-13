
class KahuuFilters
 {
	def filters = 
	{
	       loginCheck(controller: 'empleo|admin', action: '*', invert:true) 
		   {
	           before = 
			   {
	              if (!session.admin) 
				  {
	                  redirect(controller:"admin", action: 'login');
	                  return false;
	               }
	           }
		   }
	}
}
