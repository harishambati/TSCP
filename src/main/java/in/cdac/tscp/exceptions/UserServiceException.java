package in.cdac.tscp.exceptions;

public class UserServiceException extends RuntimeException
{

	private static final long serialVersionUID = -2280245451934724983L;
	
	public UserServiceException(String message) 
	{
		super(message);
	}

}
