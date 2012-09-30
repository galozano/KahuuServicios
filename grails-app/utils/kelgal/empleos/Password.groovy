package kelgal.empleos

import java.security.MessageDigest
import sun.misc.BASE64Encoder

class Password
{
	static encode = { str ->
		MessageDigest md = MessageDigest.getInstance('SHA1')
		md.update(str.getBytes('UTF-8'))
		return (new BASE64Encoder()).encode(md.digest())
	}
	
	static String createRandomPass() 
	{
		final int PASSWORD_LENGTH = 8;
		StringBuffer sb = new StringBuffer();
		
		for (int x = 0; x < PASSWORD_LENGTH; x++)
		{
		  sb.append((char)((int)(Math.random()*26)+97));
		}
		
		return sb.toString();
	}
}
