package kelgal.empleos.exceptions

import kelgal.empleos.User;


class KahuuException extends RuntimeException
{
	String message;
	Object invalido;
	
	KahuuException(String message)
	{
		super(message);
		this.message = message;
	}
	
	KahuuException(String message,Object invalido)
	{
		super(message);
		this.message = message;
		this.invalido = invalido;
	}
}
