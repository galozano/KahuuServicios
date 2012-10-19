package kahuu.general.exceptions



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
