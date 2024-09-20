package in.cdac.tscp.security;




import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import in.cdac.tscp.repositories.UserRepository;
import in.cdac.tscp.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	private final UserRepository userRepository;
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bcryptPasswordEncoder, UserRepository userRepository) {
		this.userDetailsService = userDetailsService;
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
		this.userRepository = userRepository;
	}
	
   @Override
	protected void configure(HttpSecurity  http) throws Exception
   {
	   http
	   .cors().and()
	   .csrf().disable().authorizeRequests().
	   antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
	   .permitAll()
	   .antMatchers(HttpMethod.PUT, "/admin/**").hasAnyRole("HOD", "ADMIN")
	   .antMatchers(HttpMethod.PUT, "/adminvm/**").hasAnyRole("ADMIN","HOD").
	   anyRequest().authenticated().and().addFilter(getAuthenticationFilter()).addFilter(new AuthorizationFilter(authenticationManager(), userRepository)).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	   
   }
	
   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception 
   {
	   auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder);
   }
   
   public AuthenticationFilter getAuthenticationFilter() throws Exception 
   {
	   final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(), getApplicationContext());
	   filter.setFilterProcessesUrl("/users/login");
	   return filter;
   }
   
   @Bean
   public CorsConfigurationSource corsConfigurationSource() 
   {
	   final CorsConfiguration configuration = new CorsConfiguration();
	   
	   configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
	  // configuration.setAllowedOriginPatterns("*")
	 //  configuration.addAllowedOriginPattern("*");
	  // configuration.addAllowedOriginPattern("*");
	
	   //configuration.setAllowedOrigins(Arrays.asList("*"));
	   configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
	   configuration.setAllowCredentials(true);
	   configuration.setAllowedHeaders(Arrays.asList("*"));
	   configuration.setExposedHeaders(Arrays.asList("Authorization", "UserID","Role"));
	   final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	   source.registerCorsConfiguration("/**", configuration);
	   return source;
   }
   
}





















