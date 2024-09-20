package in.cdac.tscp.security;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.cdac.tscp.SpringApplicationContext;
import in.cdac.tscp.dto.UserDto;
import in.cdac.tscp.model.request.UserLoginRequestModel;
import in.cdac.tscp.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	private final AuthenticationManager authenticationManager;
	
	
	private final UserService userService;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) 
	{
		this.authenticationManager = authenticationManager;
		this.userService = ctx.getBean(UserService.class);
	}
	
	
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException 
	{
		try {
				UserLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequestModel.class);
			
			if(userService.getUserStatus(creds.getEmail()).equals("APPROVED"))
			{
				return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
			}
			
			else 
			{
				res.addHeader("Message","pending approval");
				res.addHeader("status","failed");
				res.setStatus(403);
			}
			 return null;
		}
		
		
		catch(IOException e) 
		{
			res.addHeader("Message","login failed");
			res.setStatus(403);
			return null;
		}
		
		
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException
	{
		
		
		String userName = ((UserPrincipal) auth.getPrincipal()).getUsername();
		String token = Jwts.builder().setSubject(userName).signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();
		//String token = Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.Expiration_Time)).signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();		
		
		UserService userService = (UserService)SpringApplicationContext.getBean("userServiceimpl");
		UserDto userDto = userService.getUser(userName);
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);	
		res.addHeader("UserID", String.valueOf(userDto.getId()));
		res.addHeader("Role", String.valueOf(userDto.getRole()));
		res.addHeader("authentication", "success");
	}
	
	
}

