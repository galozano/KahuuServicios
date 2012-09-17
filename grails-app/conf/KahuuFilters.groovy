
class KahuuFilters
 {
	def filters = 
	{
	       loginCheck(controller: 'kelgal.empleo.auto.*', action: '*') 
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
