package in.cdac.tscp.security;



public class SecurityConstants 
{
	public static final long Expiration_Time = 864000000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String TOKEN_SECRET = "jf9i4jgu83nf10";
	
//	public static String getTokenSecret() 
//	{
//	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties.class");
//		return appProperties.getTokenSecret();
//	}
//	

}
